package com.wbcsd.fileGenerator.api;

/**
 * The Class BlockCategarizationImpl.
 * @author Aashish
 * @version $Id: $
 */
public class SurfaceWaterDataImpl {

    /** The state. */
    private State state;

    /** The district. */
    private String district;

    private String obsAreaName;

    private LatLong latLong;

    /** The net g water available. */
    private String faecalColiform;

    private String totalColiform;

    private String dissOxy;

    private String bod;

    private String nitriteNitrate;

    private String conductivity;

    private String pH;

    private String sar;

    private String cod;

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the faecalColiform
     */
    public String getFaecalColiform() {
        return faecalColiform;
    }

    /**
     * @param faecalColiform the faecalColiform to set
     */
    public void setFaecalColiform(String faecalColiform) {
        this.faecalColiform = faecalColiform;
    }

    /**
     * @return the totalColiform
     */
    public String getTotalColiform() {
        return totalColiform;
    }

    /**
     * @param totalColiform the totalColiform to set
     */
    public void setTotalColiform(String totalColiform) {
        this.totalColiform = totalColiform;
    }

    /**
     * @return the dissOxy
     */
    public String getDissOxy() {
        return dissOxy;
    }

    /**
     * @param dissOxy the dissOxy to set
     */
    public void setDissOxy(String dissOxy) {
        this.dissOxy = dissOxy;
    }

    /**
     * @return the bod
     */
    public String getBod() {
        return bod;
    }

    /**
     * @param bod the bod to set
     */
    public void setBod(String bod) {
        this.bod = bod;
    }

    /**
     * @return the nitriteNitrate
     */
    public String getNitriteNitrate() {
        return nitriteNitrate;
    }

    /**
     * @param nitriteNitrate the nitriteNitrate to set
     */
    public void setNitriteNitrate(String nitriteNitrate) {
        this.nitriteNitrate = nitriteNitrate;
    }

    /**
     * @return the conductivity
     */
    public String getConductivity() {
        return conductivity;
    }

    /**
     * @param conductivity the conductivity to set
     */
    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    /**
     * @return the pH
     */
    public String getpH() {
        return pH;
    }

    /**
     * @param pH the pH to set
     */
    public void setpH(String pH) {
        this.pH = pH;
    }

    /**
     * @return the sar
     */
    public String getSar() {
        return sar;
    }

    /**
     * @param sar the sar to set
     */
    public void setSar(String sar) {
        this.sar = sar;
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return the obsAreaName
     */
    public String getObsAreaName() {
        return obsAreaName;
    }

    /**
     * @param obsAreaName the obsAreaName to set
     */
    public void setObsAreaName(String obsAreaName) {
        this.obsAreaName = obsAreaName;
    }

    /**
     * @return the latLong
     */
    public LatLong getLatLong() {
        return latLong;
    }

    /**
     * @param latLong the latLong to set
     */
    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

}
