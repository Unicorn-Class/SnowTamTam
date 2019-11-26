package fr.unicornclass.snowtamtam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import model.Airport;
import model.Snowtam;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private MapView mapView;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;
    private Airport airport;
    private String snowtam;

    PlaceholderFragment(Airport a) {
        airport = a;
    }

    public static PlaceholderFragment newInstance(int index, Airport a) {
        PlaceholderFragment fragment = new PlaceholderFragment(a);
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_show_airport_group, container, false);

        FloatingActionButton fab = root.findViewById(R.id.showRawSnowtamBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(root.getContext(),RawSnowtam.class);
                i.putExtra("raw",snowtam);
                startActivity(i);
            }
        });


        final TextView textView = root.findViewById(R.id.airportName);
        textView.setText(airport.getName());

        mapView = (MapView) root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.SATELLITE_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.
                    }
                });
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(airport.getLatitude(),airport.getLongitude())).zoom(13).build()), 5000);
                mapboxMap.addMarker(new MarkerOptions().position(new LatLng(airport.getLatitude(), airport.getLongitude())).title(airport.getOaciCode()));
            }
        });

        final SwipeRefreshLayout swp = root.findViewById(R.id.swiperefresh);
        swp.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swp.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    SnowtamAPI.getSnowtamAPI().getSnowtam(airport.getOaciCode(), root.getContext(), new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("SNOWTAM",result);
                            if (!result.equals("")){
                                Snowtam s = new Snowtam(result,root.getContext());
                                snowtam = result;
                                refreshSnowtam(s,root);
                            }
                            swp.setRefreshing(false);
                        }
                    });
                }
            }
        );
        swp.post(new Runnable() {
            @Override
            public void run() {
                swp.setRefreshing(true);
                SnowtamAPI.getSnowtamAPI().getSnowtam(airport.getOaciCode(), root.getContext(), new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("SNOWTAM",result);
                        if (!result.equals("")){
                            Snowtam s = new Snowtam(result,root.getContext());
                            snowtam = result;
                            refreshSnowtam(s,root);
                        }
                        swp.setRefreshing(false);
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void refreshSnowtam(Snowtam s, final View v) {
        TextView date = v.findViewById(R.id.dateHour);
        date.setText(s.getObservationDate(v.getContext()));
    }
}