package com.savera.nammaflat.modal;

import com.savera.nammaflat.Constants;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

public class ServiceRequestModal implements Serializable {
    private Long ReqType;
    private Long ReqCategory;
    private String ReqTitle;
    private String ReqDescription;
    private Long StatusID;
    private String UserId;

    public void FillData(List<String> dataStrings)
    {
        if(dataStrings.size() != 7)
            return;

        //this.srModelID = dataStrings.get(0);
        ReqTitle = dataStrings.get(1);
        ReqDescription = dataStrings.get(2);
        StatusID = Long.parseLong(dataStrings.get(4));
        UserId = dataStrings.get(5);
        ReqCategory = Long.parseLong(dataStrings.get(6));
    }

    public void FillData(Map<String,Object> dataStrings)
    {
        Object obj = dataStrings.get(Constants.SR_TYPE);
        if(obj != null)
            ReqType = (Long) obj;

        obj = dataStrings.get(Constants.SR_CATEGORY);
        if(obj != null)
            ReqCategory = (Long) obj;

        obj = dataStrings.get(Constants.SR_TITLE);
        if(obj != null)
            ReqTitle = (String) obj;

        obj = dataStrings.get(Constants.SR_DESCRIPTION);
        if(obj != null)
            ReqDescription = (String) obj;

        obj = dataStrings.get(Constants.SR_STATUS);
        if(obj != null)
            StatusID = (Long) obj;

        obj = dataStrings.get(Constants.SR_USERID);
        if(obj != null)
            UserId = (String) obj;
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

    public Long getStatusID() {
        return StatusID;
    }

    public void setStatusID(Long statusID) {
        StatusID = statusID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Long getReqCategory() {
        return ReqCategory;
    }

    public void setReqCategory(Long reqCategory) {
        ReqCategory = reqCategory;
    }
}
