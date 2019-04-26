package com.savera.nammaflat;

public class Constants {
    public static final String PROJECT_NAME = "SAVERA_";
    public static final String SEPARATOR = "_";


    /** Codes for onActivityResult identifications */
    public static final int REQUEST_ACCOUNT_PICKER = 1000;
    public static final int REQUEST_AUTHORIZATION = 1001;
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    public static final int REQ_SIGN_IN_REQUIRED = 1003;

    /** Parameter name to store preferred google account in shared pref */
    public static final String PREF_ACCOUNT_NAME = "accountName";

    /** 'Scopes' required to request Google permissions for our specific case */
    public final static String GPHOTOS_SCOPE
            = "oauth2:profile email https://www.googleapis.com/auth/drive.photos.readonly";

    /** Firebase collections and documents */

    /** Firebase users database fields*/
    public static final String COLLECTION_SAVERA_USERS = "users";
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






}
