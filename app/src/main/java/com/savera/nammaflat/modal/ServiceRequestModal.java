package com.savera.nammaflat.modal;

public class ServiceRequestModal {
    private String szReqTitle;
    private String szReqImage;
    private String szReqStatus;
    private int reqCategory;


    public ServiceRequestModal(String szReqTitle, String szReqImage, String szReqStatus, int reqCategory) {
        this.szReqTitle = szReqTitle;
        this.szReqImage = szReqImage;
        this.szReqStatus = szReqStatus;
        this.reqCategory = reqCategory;
    }

    public String getSzReqTitle() {
        return szReqTitle;
    }

    public void setSzReqTitle(String szReqTitle) {
        this.szReqTitle = szReqTitle;
    }

    public String getSzReqImage() {
        return szReqImage;
    }

    public void setSzReqImage(String szReqImage) {
        this.szReqImage = szReqImage;
    }

    public String getSzReqStatus() {
        return szReqStatus;
    }

    public void setSzReqStatus(String szReqStatus) {
        this.szReqStatus = szReqStatus;
    }

    public int getReqCategory() {
        return reqCategory;
    }

    public void setReqCategory(int reqCategory) {
        this.reqCategory = reqCategory;
    }
}
