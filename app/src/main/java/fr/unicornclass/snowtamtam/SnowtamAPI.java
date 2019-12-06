package fr.unicornclass.snowtamtam;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnowtamAPI {



    final static String regex = "-SNOWTAM-(.|\\n)*?\\.\\)";
    final static Pattern pattern = Pattern.compile(regex);

    private static SnowtamAPI api = new SnowtamAPI();

    public static SnowtamAPI getSnowtamAPI() {
        return api;
    }

    public void getSnowtam(final String oaciCode, Context context, final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(context);
        String baseURL = "http://notamweb.aviation-civile.gouv.fr/Script/IHM/Bul_Aerodrome.php?AERO_Langue=FR";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document doc = Jsoup.parse(response);
                        Elements notams = doc.select(".NOTAM-CORPS");
                        String snowtam = "";
                        for (Element e : notams){
                            Matcher matcher = pattern.matcher(response);
                            while (matcher.find()) {
                                snowtam = matcher.group(0).replace("<font class='NOTAM-CORPS'>","").replace("</font>","");
                            }
                        }
                        callback.onSuccess(snowtam);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("SnowTamTam - API",error.toString());
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new HashMap<String, String>();
                params2.put("bResultat", "true");
                params2.put("ModeAffichage", "COMPLET");

                Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

                String year = ""+now.get(Calendar.YEAR);
                int m = now.get(Calendar.MONTH)+1;
                String month = (""+m).length() == 1 ? "0"+(m) : ""+m;
                String day = (""+now.get(Calendar.DAY_OF_MONTH)).length() == 1 ? "0"+now.get(Calendar.DAY_OF_MONTH) : ""+now.get(Calendar.DAY_OF_MONTH);
                String hour = (""+now.get(Calendar.HOUR_OF_DAY)).length() == 1 ? "0"+now.get(Calendar.HOUR_OF_DAY) : ""+now.get(Calendar.HOUR_OF_DAY);
                String min = (""+now.get(Calendar.MINUTE)).length() == 1 ? "0"+now.get(Calendar.MINUTE) : ""+now.get(Calendar.MINUTE);

                params2.put("AERO_Date_DATE", year+"/"+month+"/"+day);
                params2.put("AERO_Date_HEURE", hour+":"+min);
                params2.put("AERO_Langue", "FR");
                params2.put("AERO_Duree", "12");
                params2.put("AERO_CM_REGLE", "1");
                params2.put("AERO_CM_GPS", "2");
                params2.put("AERO_CM_INFO_COMP", "1");
                params2.put("AERO_Rayon", "10");
                params2.put("AERO_Plafond", "30");
                params2.put("AERO_Tab_Aero[0]", oaciCode);
                return params2;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
