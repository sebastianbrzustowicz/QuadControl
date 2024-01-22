package org.sebastianbrzustowicz.model;

public class OperationalData {

    private static OperationalData instance;

    private String vehicleId;
    private boolean camTrig;
    private boolean camTog;
    private int camPitch;
    private boolean clamp;

    private OperationalData() {
        this.vehicleId = "0aba81df-2af9-448a-b057-3488a45048f1";
        this.camTrig = false;
        this.camTog = false;
        this.camPitch = 0;
        this.clamp = false;
    }

    public static OperationalData getInstance() {
        if (instance == null) {
            instance = new OperationalData();
        }
        return instance;
    }

    public void saveOperationalValues(boolean camTrig, boolean camTog, int camPitch, boolean clamp) {
        this.camTrig = camTrig;
        this.camTog = camTog;
        this.camPitch = camPitch;
        this.clamp = clamp;
    }

    public String getVehicleId() {
        return vehicleId;
    }
    public boolean isCamTrig() {
        return camTrig;
    }
    public boolean isCamTog() {
        return camTog;
    }
    public int getCamPitch() {
        return camPitch;
    }
    public boolean isClamp() {
        return clamp;
    }
}
