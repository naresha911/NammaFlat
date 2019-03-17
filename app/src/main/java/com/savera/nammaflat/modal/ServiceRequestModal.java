package com.savera.nammaflat.modal;

public class ServiceRequestModal {
    private String ReqTitle;
    private String ReqDescription;
    private String ReqImage;
    private int StatusID;
    private int UserId;
    private int ReqCategory;


    public ServiceRequestModal(String reqTitle, String reqDescription, String reqImage, int statusID, int userId, int reqCategory) {
        ReqTitle = reqTitle;
        ReqDescription = reqDescription;
        ReqImage = reqImage;
        StatusID = statusID;
        UserId = userId;
        ReqCategory = reqCategory;
    }

    public String getReqTitle() {
        return ReqTitle;
    }

    public void setReqTitle(String reqTitle) {
        ReqTitle = reqTitle;
    }

    public String getReqDescription() {
        return ReqDescription;
    }

    public void setReqDescription(String reqDescription) {
        ReqDescription = reqDescription;
    }

    public String getReqImage() {
        return ReqImage;
    }

    public void setReqImage(String reqImage) {
        ReqImage = reqImage;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getReqCategory() {
        return ReqCategory;
    }

    public void setReqCategory(int reqCategory) {
        ReqCategory = reqCategory;
    }
}
