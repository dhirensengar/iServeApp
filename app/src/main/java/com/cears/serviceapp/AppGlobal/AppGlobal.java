package com.cears.serviceapp.AppGlobal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Created by Radhe on 5/1/2016.
 */
public class AppGlobal {


    public static final String APP_ID = "114519838647563";
    // public static final String APP_NAMESPACE = "fastticket_sujav";
    public static final String PREFS_NAME = "DfoodBuddPyrefs";


    public static ProgressDialog progressDialog;

    public static String gcmid;
    public static double latitude = 0;
    public static double longitude = 0;


    static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    // Email Pattern
    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    public final static Pattern PASSWORD_NUMBER_PATTERN = Pattern
            .compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,}$");


    // Email Pattern
    public final static Pattern PHONE_NUMBER_PATTERN = Pattern
            .compile("^[7-9][0-9]{9}$");


    public static Toast toast = null;
    public static String provider;
    public static String location;
    public static ArrayList<File> fileimglist = new ArrayList<>();
    public static String profilename;
    public static String about;
    public static String itemtype = "0";
    public static String usertype = "0";
    public static String filterLat;
    public static String filterLng;


    /**
     * Email validation
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean checkPhoneNumber(String phone) {
        return PHONE_NUMBER_PATTERN.matcher(phone).matches();
    }

    public static boolean checkPassword(String password) {
        return PASSWORD_NUMBER_PATTERN.matcher(password).matches();
    }


    /**
     * show application Loader
     *
     * @param mContext
     */
   /* public static void showAlert(final Context mContext, String msg, int status) {

        if (mContext != null) {
            alert_show = AnimationUtils.loadAnimation(mContext, R.anim.alert_show);

            alert_hide = AnimationUtils.loadAnimation(mContext, R.anim.alert_hide);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.layout_alert, null);

            LinearLayout layoutMain = (LinearLayout) v.findViewById(R.id.layoutAlertMain);
            final LinearLayout layoutAlert = (LinearLayout) v.findViewById(R.id.layoutAlert);

            TextView txtAlertTitle = (TextView) v.findViewById(R.id.txtAlertTitle);
            TextView txtAlertDesp = (TextView) v.findViewById(R.id.txtAlertDesp);
            View viewBottom = (View) v.findViewById(R.id.bottomStrip);
            txtAlertDesp.setText(msg);

            switch (status) {
                case 0:
                    txtAlertTitle.setText("Alert :|");
                    txtAlertTitle.setTextColor(mContext.getResources().getColor(R.color.orange));
                    viewBottom.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    break;
                case 1:
                    txtAlertTitle.setText("Success :)");
                    txtAlertTitle.setTextColor(mContext.getResources().getColor(R.color.green));
                    viewBottom.setBackgroundColor(mContext.getResources().getColor(R.color.green));
                    break;
                case 2:
                    txtAlertTitle.setText("Sorry :(");
                    txtAlertTitle.setTextColor(mContext.getResources().getColor(R.color.red));
                    viewBottom.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                    break;
            }

            layoutAlert.startAnimation(alert_show);
            layoutMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    hideAlert(mContext);

                }
            });

            ViewGroup parent = (ViewGroup) ((Activity) mContext).getWindow()
                    .getDecorView();

            parent.addView(v);

            isAlert = true;
        }
    }
*/
   /* public static void hideAlert(Context mContext) {
        try {

            if (isAlert) {

                isAlert = false;
                ViewGroup parent = (ViewGroup) ((Activity) mContext).getWindow()
                        .getDecorView();

                final View v = parent.findViewById(R.id.layoutAlertMain);
                if (v != null) {
                    final LinearLayout layoutAlert = (LinearLayout) v.findViewById(R.id.layoutAlert);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (v.getParent() != null)
                                ((ViewGroup) v.getParent()).removeView(v);
                        }
                    }, 700);
                    layoutAlert.startAnimation(alert_hide);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    /**
     * Display Toast
     *
     * @param context
     * @param message
     * @param status  0 - Alert
     *                1 - success
     *                2 - error
     */
    public static void showToast(Activity context, String message, int status) {
        // TODO Auto-generated method stub
        if (context != null) {

           /* LayoutInflater inflater = context.getLayoutInflater();
            View toastRoot = inflater.inflate(R.layout.layout_custom_toast, (ViewGroup) context.findViewById(R.id.custom_toast));
            TextView txt = (TextView) toastRoot.findViewById(R.id.custom_toast_message);
            txt.setText(message);

            switch (status) {
                case 0:
                    if (android.os.Build.VERSION.SDK_INT >= 16) {
                        txt.setBackground(context.getResources().getDrawable(R.drawable.toast_alert));
                    } else {
                        txt.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_alert));
                    }

                    break;
                case 1:
                    txt.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_success));
                    break;
                case 2:
                    txt.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_error));
                    break;
            }*/
           /* toast = new Toast(context);
            //toast.setView(toastRoot);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();*/
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        }
    }


    public static void showProgressDialog(Context context, String msg) {
        try {
            if (context != null) {
                Log.e("show", "dialog");
                progressDialog = ProgressDialog.show(context, "", msg, false, true);
                progressDialog.show();
                progressDialog.setCancelable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hide Progress Dialog
     *
     * @param context
     */
    public static void hideProgressDialog(Context context) {
        // progressDialog.dismiss();
        try {
            Log.e("hide", "dialog");

            progressDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Show Progress Dialog
//     *
//     * @param context
//     * @param msg
//     */
//    public static void showProgressDialog(Context context, String msg) {
//
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage(msg);
//       // progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
//
//    }
//
//    /**
//     * Hide Progress Dialog
//     *
//     * @param context
//     */
//    public static void hideProgressDialog(Context context) {
//        progressDialog.dismiss();
//    }


    public static void displayAlertDilog(Context mContext, String title,
                                         String msg) {

        new AlertDialog.Builder(mContext).setTitle(title).setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
    }

    /**
     * check Network Connection
     *
     * @param context
     * @return
     */
    public static boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


/*
    public static void thumbValSharedPrefs(Context context, List<ThumbVal> thumbval) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonthumbval = gson.toJson(thumbval);

        editor.putString("thumbval", jsonthumbval);

        editor.commit();
    }

    public static List<ThumbVal> getThumbVAL(Context context) {
        SharedPreferences settings;
        List<ThumbVal> thumbval;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains("thumbval")) {
            String jsonUsers = settings.getString("thumbval", null);
            Gson gson = new Gson();
            ThumbVal[] thumbvalItems = gson.fromJson(jsonUsers,
                    ThumbVal[].class);

            thumbval = Arrays.asList(thumbvalItems);

            thumbval = new ArrayList<ThumbVal>(thumbval);
        } else
            return null;

        return (ArrayList<ThumbVal>) thumbval;
    }
*/


    static public boolean setStringPreference(Context c, String value,
                                              String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }


    static public void clearPreferences(Context c) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }


    /**
     * get String from Preference
     *
     * @param c
     * @param key
     * @return
     */
    static public String getStringPreference(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        String value = settings.getString(key, "");
        return value;
    }

    /**
     * Store integer to Preference.
     *
     * @param c
     * @param value
     * @param key
     * @return
     */
    static public boolean setIntegerPreference(Context c, int value, String key) {

        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();

    }

    /**
     * get Integer from Preference
     *
     * @param c
     * @param key
     * @return
     */
    static public int getIntegerPreference(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        int value = settings.getInt(key, -1);
        return value;
    }

    /**
     * Store double to Preference.
     *
     * @param c
     * @param value
     * @param key
     * @return
     */
    static public boolean setFloatPreference(Context c, float value, String key) {

        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();

    }

    /**
     * get Integer from Preference
     *
     * @param c
     * @param key
     * @return
     */
    static public float getFloatPreference(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        float value = settings.getFloat(key, -1);
        return value;
    }

    /**
     * Store boolean to Preference
     *
     * @param c
     * @param value
     * @param key
     * @return
     */
    static public boolean setBooleanPreference(Context c, Boolean value,
                                               String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean from Preference
     *
     * @param c
     * @param key
     * @return
     */
    static public Boolean getBooleanPreference(Context c, String key) {

        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        Boolean value = settings.getBoolean(key, false);
        return value;
    }


    public static int getDeviceHeight(Context mContex) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContex).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm.heightPixels;
    }


    public static int getDeviceWidth(Context mContex) {

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContex).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;

    }

    /**
     * Hide Keyboard
     *
     * @param mContext
     */
    public static void hideKeyboard(Context mContext) {

        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(((Activity) mContext).getWindow()
                .getCurrentFocus().getWindowToken(), 0);

    }

    /**
     * Convert dp to PIx
     *
     * @param res
     * @param dp
     * @return
     */
    public static int dpToPx(Resources res, int dp) {

        // final float scale = res.getDisplayMetrics().density;
        // int pixels = (int) (dp * scale + 0.5f);

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());

    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    /*
     * generate Unique number
     */
    public static String generateUniqueNumber() {

        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
                .toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        String output = sb.toString();
        return output;

    }

    public static String GetUTCdatetimeAsString() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT,
                java.util.Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date());
    }

    /**
     * Checks if the application is being sent in the background (i.e behind
     * another application's Activity).
     *
     * @param context the context
     * @return <code>true</code> if another application will be above this one.
     */
    @SuppressWarnings("deprecation")
    public static boolean isApplicationSentToBackground(final Context context) {

        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    // get only date
    public String getDateString(String strdate) {

        Date date = convertStringToDate(strdate);

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd",
                java.util.Locale.getDefault());

        String str = tmp.format(date);

        return str;
    }

    // Convert String to date
    public static Date convertStringToDate(String strdate) {

        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd",
                java.util.Locale.getDefault());
        Date parsedDate = null;
        try {
            parsedDate = sourceFormat.parse(strdate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        TimeZone tz = TimeZone.getDefault();

        SimpleDateFormat destFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());

        destFormat.setTimeZone(tz);

        String destDate = destFormat.format(parsedDate);

        Date date = null;
        try {
            date = destFormat.parse(destDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;

    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getNextDayDateTime(String dateTime) {
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateTime.split("-")[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateTime.split("-")[1]) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(dateTime.split("-")[2]));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }


    public static String getDateTimeInFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getNextDayDateTimeInFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }

















  /*  public static void callFacebookLogout(Context context) {
        Session session = Session.getActiveSession();
        if (session != null) {

            if (!session.isClosed()) {
                session.closeAndClearTokenInformation();
                //clear your preferences if saved
            }
        } else {

            session = new Session(context);
            Session.setActiveSession(session);

            session.closeAndClearTokenInformation();
            //clear your preferences if saved

        }

    }*/


}
