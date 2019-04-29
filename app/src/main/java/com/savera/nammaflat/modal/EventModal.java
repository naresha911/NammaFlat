package com.savera.nammaflat.modal;

import com.google.firebase.Timestamp;
import com.savera.nammaflat.Constants;

import java.util.Date;
import java.util.Map;

public class EventModal extends FBModal{
    private Long   eventtype;
    private String title;
    private String description;
    private Timestamp eventdate;

    @Override
    public void FillData(Map<String, Object> modalData) {
        Object obj = modalData.get(Constants.EV_EVENT_TYPE);
        if(obj != null)
            eventtype = (Long) obj;

        obj = modalData.get(Constants.EV_TITLE);
        if(obj != null)
            title = (String) obj;

        obj = modalData.get(Constants.EV_DESCRIPTION);
        if(obj != null)
            description = (String) obj;

        obj = modalData.get(Constants.EV_DATE);
        if(obj != null)
            eventdate = (Timestamp) obj;
    }

    public Long getEventtype() {
        return eventtype;
    }

    public void setEventtype(Long eventtype) {
        this.eventtype = eventtype;
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

    public Timestamp getEventdate() {
        return eventdate;
    }

    public void setEventdate(Timestamp eventdate) {
        this.eventdate = eventdate;
    }
}
