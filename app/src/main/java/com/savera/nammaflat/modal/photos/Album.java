package com.savera.nammaflat.modal.photos;

import java.io.Serializable;

public class Album implements Serializable {
    public String id;
    public String title;
    public String productUrl;
    public boolean isWriteable;
    public long mediaItemsCount;
    public String coverPhotoBaseUrl;
    public String coverPhotoMediaItemId;
}
