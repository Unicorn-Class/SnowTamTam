package model;

class Runway {
    String id;
    String clearedRunwayLength;
    String clearedRunwayWidth;
    String condition;
    String thickness;
    String frictionCoefficient;
    String criticalDrift;
    String obscuredLimelight;
    String nextClearing;

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
     */
    public Runway(String id, String clearedRunwayLength, String clearedRunwayWidth, String condition, String thickness, String frictionCoefficient, String criticalDrift, String obscuredLimelight, String nextClearing) {
        this.id = id;
        this.clearedRunwayLength = clearedRunwayLength;
        this.clearedRunwayWidth = clearedRunwayWidth;
        this.condition = condition;
        this.thickness = thickness;
        this.frictionCoefficient = frictionCoefficient;
        this.criticalDrift = criticalDrift;
        this.obscuredLimelight = obscuredLimelight;
        this.nextClearing = nextClearing;
    }

    public Runway(String codedRunway) {
        /*TODO parse coded Runway to extract information*/
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
