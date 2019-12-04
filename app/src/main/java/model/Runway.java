package model;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;


public class Runway implements Serializable {
    private String id;
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
    public Runway(String id, String clearedRunwayLength, String clearedRunwayWidth, String condition, String thickness, String frictionCoefficient, String criticalDrift, String obscuredLimelight, String nextClearing,String other) {
        this.id = id;
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
        this.id = SnowtamInfo.get("C)");
        Log.d("Runway ID",id);
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
        if(coded.isEmpty()){
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
        return condition;
    }
    public String decodeFriction(String coded){
        Log.d("Friction",coded);
        String friction ="";
        String[] parsedCondition=coded.split("/");
        for(String part:parsedCondition){
            for(int value : part.toCharArray()){
                String c = Character.toString ((char) value);
                String state=Friction.values()[Integer.parseInt(c)-1].name();
                friction += " "+state;
            }
        }
        return condition;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClearedRunwayLength() {
        return clearedRunwayLength;
    }

    public void setClearedRunwayLength(String clearedRunwayLength) {
        this.clearedRunwayLength = clearedRunwayLength;
    }

    public String getClearedRunwayWidth() {
        return clearedRunwayWidth;
    }

    public void setClearedRunwayWidth(String clearedRunwayWidth) {
        this.clearedRunwayWidth = clearedRunwayWidth;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getFrictionCoefficient() {
        return frictionCoefficient;
    }

    public void setFrictionCoefficient(String frictionCoefficient) {
        this.frictionCoefficient = frictionCoefficient;
    }

    public String getCriticalDrift() {
        return criticalDrift;
    }

    public void setCriticalDrift(String criticalDrift) {
        this.criticalDrift = criticalDrift;
    }

    public String getObscuredLimelight() {
        return obscuredLimelight;
    }

    public void setObscuredLimelight(String obscuredLimelight) {
        this.obscuredLimelight = obscuredLimelight;
    }

    public String getNextClearing() {
        return nextClearing;
    }

    public void setNextClearing(String nextClearing) {
        this.nextClearing = nextClearing;
    }
}
