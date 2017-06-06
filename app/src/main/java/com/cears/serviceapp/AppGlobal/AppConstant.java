package com.cears.serviceapp.AppGlobal;

import java.util.ArrayList;

/**
 * Created by Radhe on 5/6/2016.
 */
public class AppConstant {

    public static final String PREF_ISLOGIN = "isLogin";

    public static final String PREF_LOGIN_NAVIGATE = "login";

    public static final String LOCATION_PARAMETRE_LATITUDE = "latitude";
    public static final String LOCATION_PARAMETRE_LONGITUDE = "longitude";
    public static final String CHECK_Activity = "check";
    public static String PLACE_IMAGE = "place_image";
    public static String PLACE_LOCATION = "map_location";

    public final static String EHS_SELECTED = "";

    public static String PREF_USERFULLNAM = "fullname";
    public static String PREF_STATUS = "";
    public static String PREF_FULLADDRESS = "fulladdress";
    public static final String DeviceId = "";
    public static String PREF_TOKEN = "token";
    public static final String PREF_ADMINID = "userid";
    public static final String PREF_SECRET_LOG_ID = "useridvn ghkj";

    public static final String PREF_USEREMAIL = "email";
    public static final String PREF_USERNAME = "username";
    public static String PREF_PASSWORD = "passsword";
    public static String PREF_FNAME = "fname";
    public static String PREF_LNAME = "lname";
    public static String PLAN_ID = "plan_id";
    public static String PREF_MOBILE = "mobile";
    public static String PREF_ABOUT_SEFroleLF = "about_your_self";
    public static String PREF_GENDER = "gender";
    public static String PREF_BIRTHDATE = "birthdate";
    public static String PREF_CELEBRITY = "celebrity";
    public static String PREF_COUNTRY = "country";
    public static String PREF_CITY = "city";
    public static String PPRGRESS_DIALOG_MSG = "Loading...";
    public static final String PREF_PROFILEPIC = "profilepic";
    public static String PREF_IMAGE_LIMIT = "image_limit";

    public static String PLACE_HOLDER_IMAGE = "placeholderimage";

    public static final int SELECT_GALLERY = 100;
    public static final int SELECT_CAMERA = 200;

/*    public static List<ThumbVal> thumbvalList=new ArrayList<>();
    public static List<ThumbData> thumbDataList=new ArrayList<>();
    public static List<SwitchActUserAccountData> multiactuserdata=new ArrayList<>();*/


    public static final int CARD_NUMBER_TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
    public static final int CARD_NUMBER_TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
    public static final int CARD_NUMBER_DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
    public static final int CARD_NUMBER_DIVIDER_POSITION = CARD_NUMBER_DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
    public static final char CARD_NUMBER_DIVIDER = '-';

    public static final int CARD_DATE_TOTAL_SYMBOLS = 5; // size of pattern MM/YY
    public static final int CARD_DATE_TOTAL_DIGITS = 4; // max numbers of digits in pattern: MM + YY
    public static final int CARD_DATE_DIVIDER_MODULO = 3; // means divider position is every 3rd symbol beginning with 1
    public static final int CARD_DATE_DIVIDER_POSITION = CARD_DATE_DIVIDER_MODULO - 1; // means divider position is every 2nd symbol beginning with 0
    public static final char CARD_DATE_DIVIDER = '/';

    public static final int CARD_CVC_TOTAL_SYMBOLS = 3;


    public static final String PREF_ROLEGROUP = "role_group";
    public static final String PREF_NEWSLETTER = "news_letter";
    public static final String PREF_LONGITUDE = "longitude";
    public static final String PREF_LATITUDE = "latitude";


    public static final String PREF_NOT_OTHER = "notification_other";
    public static final String PREF_FREE_NOT_DURATION = "free_notification_duration";
    public static final String PREF_OTHER_NOT_DURATION = "other_notification_duration";
    public static final String PREF_LOCATION_RANGE = "location_range";
    public static final String PREF_NOT_FREE = "notification_free";
    public static ArrayList<String> itemids = new ArrayList<>();
   /* public  static ArrayList<ByLocMainFragmentData> arbyLocMainfragmentdata=new ArrayList<ByLocMainFragmentData>();*/
  /*  public  static ArrayList<ProfileDonationData> arprofiledonationdata=new ArrayList<ProfileDonationData>();
    public  static ArrayList<ProfileCollecteditemsData> arprofilecollecteditemsdata=new ArrayList<ProfileCollecteditemsData>();
*/


    //  public  static ArrayList<ByLocMainFragmentData> arbyLocMainfragmentdata=new ArrayList<ByLocMainFragmentData>();
//    public  static ArrayList<ProfileDonationData> arprofiledonationdata=new ArrayList<ProfileDonationData>();
//    public  static ArrayList<ProfileCollecteditemsData> arprofilecollecteditemsdata=new ArrayList<ProfileCollecteditemsData>();

    // public static ArrayList<ByLocMainFragmentData> arbyLocMainfragmentdata;
}
