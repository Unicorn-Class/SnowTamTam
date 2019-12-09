package fr.unicornclass.snowtamtam.model;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;

import fr.unicornclass.snowtamtam.R;


public class Runway implements Serializable {

    private String id;
    private String date;
    private String clearedRunwayLength;
    private String clearedRunwayWidth;
    private String condition;
    private String thickness;
    private String frictionCoefficient;
    private String criticalDrift;
    private String obscuredLimelight;
    private String nextClearing;
    private String other;

    /**
     * @param id the identifier of the runway
     * @param date date of emission
     * @param clearedRunwayLength the length of clean runway (null in case of all cleaned runway)
     * @param clearedRunwayWidth the width of clean runway (null in case of all cleaned runway)
     * @param condition the condition in all the runway
     * @param thickness the snow thickness
     * @param frictionCoefficient the friction coefficient
     * @param criticalDrift
     * @param obscuredLimelight
     * @param nextClearing the next part which will be cleaned
     * @param other clear information about the SNOWTAM
     */
    public Runway(String id, String date, String clearedRunwayLength, String clearedRunwayWidth, String condition, String thickness, String frictionCoefficient, String criticalDrift, String obscuredLimelight, String nextClearing,String other) {
        this.id = id;
        this.date = date;
        this.clearedRunwayLength = clearedRunwayLength;
        this.clearedRunwayWidth = clearedRunwayWidth;
        this.condition = condition;
        this.thickness = thickness;
        this.frictionCoefficient = frictionCoefficient;
        this.criticalDrift = criticalDrift;
        this.obscuredLimelight = obscuredLimelight;
        this.nextClearing = nextClearing;
        this.other=other;
    }

    public Runway(HashMap<String,String> SnowtamInfo) {
        this.date = SnowtamInfo.get("B)");
        this.id = SnowtamInfo.get("C)");
        this.clearedRunwayLength = SnowtamInfo.get("D)");
        this.clearedRunwayWidth = SnowtamInfo.get("E)");
        this.condition = decodeCondition(SnowtamInfo.get("F)"));
        this.thickness = SnowtamInfo.get("G)");
        this.frictionCoefficient = decodeFriction(SnowtamInfo.get("H)"));
        this.criticalDrift = SnowtamInfo.get("J)");
        this.obscuredLimelight = SnowtamInfo.get("K)");
        this.nextClearing = SnowtamInfo.get("L)");
        this.other=SnowtamInfo.get("T)");

    }
    public String decodeCondition(String coded){
        if(coded == null || coded.isEmpty()){
            return "NIL";
        }
        String condition ="";
        String[] parsedCondition=coded.split("/");
        for(String part:parsedCondition){
            if (part.equals("NIL")){
                String state=Condition.values()[0].name();
                condition += " "+state;
            }
            else{
                for(int value : part.toCharArray()){
                    String c = Character.toString ((char) value);
                    String state=Condition.values()[Integer.parseInt(c)].name();
                    condition += " "+state;
                }
            }
        }
        return condition.substring(1);
    }
    public String decodeFriction(String coded){
        if(coded == null || coded.isEmpty()){
            return "NIL";
        }
        String friction ="";
        String[] parsedCondition=coded.split("/");
        for(String part:parsedCondition){
            for(int value : part.toCharArray()){
                String c = Character.toString ((char) value);
                String state=Friction.values()[Integer.parseInt(c)-1].name();
                friction += " "+state;
            }
        }
        return friction.substring(1);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public String getThickness() {
        return thickness;
    }

    public String getFrictionCoefficient() {
        return frictionCoefficient;
    }

    public String getDate(Context c) {
        String humanReadableDate = date.substring(2,4)+" ";
        String month = date.substring(0,2);
        switch (month) {
            case "01":
                humanReadableDate += c.getResources().getString(R.string.month_January);
                break;
            case "02":
                humanReadableDate += c.getResources().getString(R.string.month_February);
                break;
            case "03":
                humanReadableDate += c.getResources().getString(R.string.month_March);
                break;
            case "04":
                humanReadableDate += c.getResources().getString(R.string.month_April);
                break;
            case "05":
                humanReadableDate += c.getResources().getString(R.string.month_May);
                break;
            case "06":
                humanReadableDate += c.getResources().getString(R.string.month_June);
                break;
            case "07":
                humanReadableDate += c.getResources().getString(R.string.month_July);
                break;
            case "08":
                humanReadableDate += c.getResources().getString(R.string.month_August);
                break;
            case "09":
                humanReadableDate += c.getResources().getString(R.string.month_September);
                break;
            case "10":
                humanReadableDate += c.getResources().getString(R.string.month_October);
                break;
            case "11":
                humanReadableDate += c.getResources().getString(R.string.month_November);
                break;
            case "12":
                humanReadableDate += c.getResources().getString(R.string.month_December);
                break;
        }
        humanReadableDate+=" "+c.getResources().getString(R.string.date_At)+" "+date.substring(4,6)+"h"+date.substring(6);
        return humanReadableDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
