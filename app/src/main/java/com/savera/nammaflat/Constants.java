package com.savera.nammaflat;

public class Constants {
    public static final String PROJECT_NAME = "SAVERA_";
    public static final String SEPARATOR = "_";


    /** Codes for onActivityResult identifications */
    public static final int REQUEST_ACCOUNT_PICKER = 1000;
    public static final int REQUEST_AUTHORIZATION = 1001;
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    public static final int REQ_SIGN_IN_REQUIRED = 1003;
    /** Request codes to dialogs*/
    public static final int REQUEST_SERVICE_REQUEST_ADD = 1004;
    public static final int REQUEST_REGISTER_USER = 1005;

    /** Parameter name to store preferred google account in shared pref */
    public static final String PREF_ACCOUNT_NAME = "accountName";

    /** 'Scopes' required to request Google permissions for our specific case */
    public final static String GPHOTOS_SCOPE
            = "oauth2:profile email https://www.googleapis.com/auth/photoslibrary.readonly";

    public static final String EXTRAS_ACTIVITY_RESULT_MODAL = "addedServiceRequest";

    /** Firebase collections and documents */

    /** Firebase users database fields*/
    public static final String COLLECTION_SAVERA_USERS = "users";
    public static final String USERS_ID = "userid";
    public static final String USERS_NAME = "name";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_PHONE = "phone";
    public static final String USERS_LEVEL = "level";

    /** Firebase Service Request database fields */
    public static final String SR_COLLECTION = "service_requests";
    public static final String SR_CATEGORY = "category";
    public static final String SR_DESCRIPTION = "description";
    public static final String SR_STATUS = "status";
    public static final String SR_TITLE = "title";
    public static final String SR_TYPE = "type";
    public static final String SR_USERID = "userid";

    /** Firebase Events database fields */
    public static final String EV_COLLECTION = "events";
    public static final String EV_EVENT_TYPE = "eventtype";
    public static final String EV_TITLE = "title";
    public static final String EV_DESCRIPTION = "description";
    public static final String EV_DATE = "eventdate";

    /** Header information required for added authorization to API calls */
    public static final String PHOTOS_URL = "https://photoslibrary.googleapis.com/v1/albums";
    public static final String PHOTOS_MEDIA_ITEMS_SEARCH_URL = "https://photoslibrary.googleapis.com/v1/mediaItems:search";
    public static final String HEADER_NAME_AUTH = "Authorization";
    public static final String HEADER_AUTH_VAL_PRFX = "Bearer ";

    public static final String PHOTOS_ID = "id";
    public static final String PHOTOS_TITLE = "title";
    public static final String PHOTOS_PRODUCTURL = "productUrl";

    //ALBUM
    public static final String ALBUMS = "albums";
    public static final String PHOTOS_ALBUMS_MEDIAITEMSCOUNT = "mediaItemsCount";
    public static final String PHOTOS_ALBUMS_COVERPHOTOBASEURL = "coverPhotoBaseUrl";
    public static final String PHOTOS_ALBUMS_COVERPHOTOMEDIAITEMID = "coverPhotoMediaItemId";
    public static final String PHOTOS_ALBUMS_NEXT_PAGE_TOKEN = "nextPageToken";

    //MEDIA Item
    public static final String MEDIA_ITEMS = "mediaItems";
    public static final String PHOTOS_MEDIA_BASEURL = "baseUrl";
    public static final String PHOTOS_MEDIA_MIMETYPE = "mimeType";
    public static final String PHOTOS_MEDIA_METADATA = "mediaMetadata";
    public static final String PHOTOS_MEDIA_FILENAME = "filename";

    //MEDIA META DATA
    public static final String MEDIA_ITEMS = "mediaItems";

}
