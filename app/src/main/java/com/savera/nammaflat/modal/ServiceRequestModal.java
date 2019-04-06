package com.savera.nammaflat.modal;

import java.io.Serializable;
import java.util.List;

public class ServiceRequestModal implements Serializable {
    private int id;
    private String ReqTitle;
    private String ReqDescription;
    private String ReqImage;
    private int StatusID;
    private int UserId;
    private int ReqCategory;

    public void FillData(List<String> dataStrings)
    {
        if(dataStrings.size() != 7)
            return;

        this.id = Integer.parseInt(dataStrings.get(0));
        ReqTitle = dataStrings.get(1);
        ReqDescription = dataStrings.get(2);
        ReqImage = dataStrings.get(3);
        StatusID = Integer.parseInt(dataStrings.get(4));
        UserId = Integer.parseInt(dataStrings.get(5));
        ReqCategory = Integer.parseInt(dataStrings.get(6));
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
