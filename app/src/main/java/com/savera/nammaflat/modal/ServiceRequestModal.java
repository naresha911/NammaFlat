package com.savera.nammaflat.modal;

import com.savera.nammaflat.Constants;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ServiceRequestModal implements Serializable {
    private Long type;
    private Long category;
    private String title;
    private String description;
    private Long status;
    private String userid;

    public void FillData(List<String> dataStrings)
    {
        if(dataStrings.size() != 7)
            return;

        //this.srModelID = dataStrings.get(0);
        title = dataStrings.get(1);
        description = dataStrings.get(2);
        status = Long.parseLong(dataStrings.get(4));
        userid = dataStrings.get(5);
        category = Long.parseLong(dataStrings.get(6));
    }

    public void FillData(Map<String,Object> dataStrings)
    {
        Object obj = dataStrings.get(Constants.SR_TYPE);
        if(obj != null)
            type = (Long) obj;

        obj = dataStrings.get(Constants.SR_CATEGORY);
        if(obj != null)
            category = (Long) obj;

        obj = dataStrings.get(Constants.SR_TITLE);
        if(obj != null)
            title = (String) obj;

        obj = dataStrings.get(Constants.SR_DESCRIPTION);
        if(obj != null)
            description = (String) obj;

        obj = dataStrings.get(Constants.SR_STATUS);
        if(obj != null)
            status = (Long) obj;

        obj = dataStrings.get(Constants.SR_USERID);
        if(obj != null)
            userid = (String) obj;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String user_id) {
        this.userid = user_id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
