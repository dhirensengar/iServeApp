package com.cears.serviceapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.Database.IncompleteDatabase;
import com.cears.serviceapp.Database.LocalDatabase;
import com.cears.serviceapp.Pojo.DataPojo;
import com.cears.serviceapp.Pojo.DatabasePojo;
import com.cears.serviceapp.Pojo.IncompletePojo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by VIKAS SAHU on 21/12/16.
 */

public class GeneralHelpers {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static final CharSequence NO_INTERNET_CONNECTION_MESSAGE = "\nSorry! No Active Internet Connection.\n\nPress settings and activate your data connection and try again.\n";

    public static boolean isOnline(Activity context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable() && ni.isConnected()) {
            return true;
        } else {
            showNoInternetPopup(context);
            return false;
        }
    }

    public static boolean isOnlineWithoutExit(Activity context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable() && ni.isConnected()) {
            return true;
        } else {
            showNoInternetPopupWithoutExit(context);
            return false;
        }
    }

    public static void showNoInternetPopup(final Activity context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setMessage(NO_INTERNET_CONNECTION_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        context.finish();
                    }
                });
        builder.setNegativeButton("Settings", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                dialog.cancel();
                context.finish();
            }
        });

        context.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                builder.show();

            }
        });

    }

    public static void showNoInternetPopupWithoutExit(final Activity context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setMessage(NO_INTERNET_CONNECTION_MESSAGE)
                .setCancelable(false)
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        builder.setNegativeButton("Settings", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                dialog.cancel();
                context.finish();
            }
        });

        context.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                builder.show();

            }
        });

    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static int getCurrentFragment(Context context) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        int coin = pref.getInt("FRAGMENT_POSITION", 0);
        return coin;
    }

    public static void setCurrentFragment(Context context, int position) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("FRAGMENT_POSITION", position);
        edit.commit();


    }


    public static void showPopUp(Context context, String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();
    }

    //It is using in survey Activity
    public static String getEquipmentTypeValue(int positionOfEquipmentType, int dataPosition, ArrayList<DataPojo> data) {
        String returnValue = "0";

        switch (positionOfEquipmentType) {
            case 0:
                if (data.get(dataPosition).getIsCentrifugal().equals("1"))
                    returnValue = "1";

                break;
            case 1:
                if (data.get(dataPosition).getIsRecipAirCooled().equals("1"))
                    returnValue = "1";
                break;
            case 2:
                if (data.get(dataPosition).getIsRecipWaterCooled().equals("1"))
                    returnValue = "1";
                break;
            case 3:
                if (data.get(dataPosition).getIsScrewAirCooled().equals("1"))
                    returnValue = "1";
                break;
            case 4:
                if (data.get(dataPosition).getIsScrewWaterCooled().equals("1"))
                    returnValue = "1";
                break;
            case 5:
                if (data.get(dataPosition).getIsRTU().equals("1"))
                    returnValue = "1";
                break;
            case 6:
                if (data.get(dataPosition).getIsSplits().equals("1"))
                    returnValue = "1";
                break;
            case 7:
                if (data.get(dataPosition).getIsMiniSplits().equals("1"))
                    returnValue = "1";
                break;
            case 8:
                if (data.get(dataPosition).getIsAHU().equals("1"))
                    returnValue = "1";
                break;
            case 9:
                if (data.get(dataPosition).getIsFAHU().equals("1"))
                    returnValue = "1";
                break;
            case 10:
                if (data.get(dataPosition).getIsFCU().equals("1"))
                    returnValue = "1";
                break;
            case 11:
                if (data.get(dataPosition).getIsPump().equals("1"))
                    returnValue = "1";
                break;
        }
        return returnValue;
    }

    //It is using in survey Activity
    public static String getServiceTypeValue(int positionOfService, int dataPosition, ArrayList<DataPojo> data) {


        String returnValue = "0";
        switch (positionOfService) {
            case 0:
                if (data.get(dataPosition).getIsMajor().equals("1"))
                    returnValue = "1";
                break;

            case 1:
                if (data.get(dataPosition).getIsMinor().equals("1")) {
                    returnValue = "1";
                }
                break;
            case 2:
                if (data.get(dataPosition).getIsCleaning().equals("1")) {
                    returnValue = "1";
                }
                break;
            case 3:
                if (data.get(dataPosition).getIsCleaning().equals("1"))
                    returnValue = "1";
                break;
            case 4:
                if (data.get(dataPosition).getIsCleaning().equals("1")) {
                    returnValue = "1";
                }
                break;
        }

        return returnValue;
    }

    public static void setJobTicketNumber(
            Activity activity, String jobTicketNumber) {
        SharedPreferences.Editor editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString("JobTicketNumber", jobTicketNumber);
        editor.commit();
    }

    public static String getJobTicketNumber(Activity activity) {
        SharedPreferences prefs = activity.getPreferences(Context.MODE_PRIVATE);
        return prefs.getString("JobTicketNumber", "");
    }

    public static void setCustomerName(
            Activity activity, String equipmentNumber) {
        SharedPreferences.Editor editor = activity.getSharedPreferences("GENERAL", Context.MODE_PRIVATE).edit();
        editor.putString("CustomerName", equipmentNumber);
        editor.commit();
    }

    public static String getCustomerName(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("GENERAL", Context.MODE_PRIVATE);
        return prefs.getString("CustomerName", "");
    }

    public static void setTechnicianName(
            Activity activity, String equipmentNumber) {
        SharedPreferences.Editor editor = activity.getSharedPreferences("GENERAL", Context.MODE_PRIVATE).edit();
        editor.putString("TechnicianName", equipmentNumber);
        editor.commit();
    }

    public static String getTechnicianName(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("GENERAL", Context.MODE_PRIVATE);
        return prefs.getString("TechnicianName", "");
    }

    public static void setSiteName(
            Activity activity, String equipmentNumber) {
        SharedPreferences.Editor editor = activity.getSharedPreferences("GENERAL", Context.MODE_PRIVATE).edit();
        editor.putString("SiteName", equipmentNumber);
        editor.commit();
    }

    public static String getSiteName(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("GENERAL", Context.MODE_PRIVATE);
        return prefs.getString("SiteName", "");
    }

    public static void setLastPosition(
            Activity activity, int pos, String tokenNumber) {
        SharedPreferences.Editor editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putInt(tokenNumber, pos);
        editor.commit();
    }

    public static int getLastPosition(Activity activity, String tokenNumber) {
        SharedPreferences prefs = activity.getPreferences(Context.MODE_PRIVATE);
        return prefs.getInt(tokenNumber, 0);
    }

    public static void setIsSurveyInCompleted(Activity activity, boolean flag) {
        SharedPreferences.Editor editor = activity.getSharedPreferences("BOOL", Context.MODE_PRIVATE).edit();
        editor.putBoolean("CONSTANT_IS_INCOMPLETE", flag);
        editor.commit();

    }

    public static boolean isSurveyInCompleted(Activity activity) {

        SharedPreferences prefs = activity.getSharedPreferences("BOOL", Context.MODE_PRIVATE);

        return prefs.getBoolean("CONSTANT_IS_INCOMPLETE", false);
    }

    public static byte[] getBytesFromUri(Context context, Uri uri) throws IOException {
        InputStream iStream = context.getContentResolver().openInputStream(uri);
        try {
            return getBytes(iStream);
        } finally {
            // close the stream
            try {
                iStream.close();
            } catch (IOException ignored) { /* do nothing */ }
        }
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {

        byte[] bytesResult = null;
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        try {
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
            bytesResult = byteBuffer.toByteArray();
        } finally {
            // close the stream
            try {
                byteBuffer.close();
            } catch (IOException ignored) { /* do nothing */ }
        }
        return bytesResult;
    }


    public static ArrayList<IncompletePojo> inCompleteSave(Context context, String jobTicketNumber, String equipmentNumber, String serviceType, String equipmentType, ArrayList<DataPojo> dataListSorted) {

        ArrayList<IncompletePojo> inCompleteList = new ArrayList<IncompletePojo>();
        LocalDatabase localDB = new LocalDatabase(context);
        IncompleteDatabase dbIncomplete = new IncompleteDatabase(context);


        //add all answer questions
        for (int i = 0; i < dataListSorted.size(); i++) {
            IncompletePojo incPojo = new IncompletePojo();
            incPojo.setId("" + dataListSorted.get(i).getId());
            incPojo.setQuestionType("" + dataListSorted.get(i).getQuestionType());
            incPojo.setQuestion("" + dataListSorted.get(i).getQuestion());
            incPojo.setOptionCount(0 + dataListSorted.get(i).getOptionCount());
            incPojo.setJobTicketNumber("" + jobTicketNumber);
            incPojo.setEquipNumber("" + equipmentNumber);
            incPojo.setServiceType("" + serviceType);
            incPojo.setEquipmentType("" + equipmentType);
            incPojo.setOption1("" + dataListSorted.get(i).getOption1());
            incPojo.setOption2("" + dataListSorted.get(i).getOption2());
            incPojo.setOption3("" + dataListSorted.get(i).getOption3());
            incPojo.setOption4("" + dataListSorted.get(i).getOption4());
            incPojo.setOption5("" + dataListSorted.get(i).getOption5());
            incPojo.setGroup("" + dataListSorted.get(i).getGroup());
            incPojo.setCode("" + dataListSorted.get(i).getCode());
            incPojo.setCheckedPosition(0 + dataListSorted.get(i).getCheckedPosition());
            incPojo.setAdditionalTextFiled("" + dataListSorted.get(i).getAdditionalText());
            incPojo.setIsPhoto(dataListSorted.get(i).getIsPhoto());
            incPojo.setOptionText("" + dataListSorted.get(i).getOptionText());
            incPojo.setAnswered(dataListSorted.get(i).isAnswered());
            incPojo.setPhotoLink("" + dataListSorted.get(i).getPhotoLink());
            incPojo.setUnit("" + dataListSorted.get(i).getUnit());
            incPojo.setPartDescription("" + dataListSorted.get(i).getPartDescription());
            incPojo.setPartNumber("" + dataListSorted.get(i).getPartNumber());
            incPojo.setPartQuantity("" + dataListSorted.get(i).getPartQuantity());
            incPojo.setManPower("" + dataListSorted.get(i).getManPower());
            incPojo.setIsRepaired("" + dataListSorted.get(i).getIsRepaired());
            incPojo.setDateString("" + GeneralHelpers.currentDate(new Date()).toString());
            incPojo.setPreAnswer("" + dataListSorted.get(0).getPreAnswer());
            incPojo.setUnitFixed("" + dataListSorted.get(i).getUnitFixed());
            inCompleteList.add(incPojo);

            dbIncomplete.addInCompletedDataToDb(incPojo);
        }
        dbIncomplete.close();
        localDB.close();
        return inCompleteList;
    }


    public static ArrayList<DataPojo> setDataToSortedListForInCompletedState(Context context, String jobTicketNumber) {
        ArrayList<DataPojo> dataListSorted = new ArrayList<>();
        IncompleteDatabase db = new IncompleteDatabase(context);
        ArrayList<IncompletePojo> inCompletedDataList = db.getAllInCompletedDataByTockenNumber(jobTicketNumber);
        db.close();
        for (int i = 0; i < inCompletedDataList.size(); i++) {
            DataPojo dataPojo = new DataPojo();
            dataPojo.setQuestion("" + inCompletedDataList.get(i).getQuestion());
            dataPojo.setQuestionType("" + inCompletedDataList.get(i).getQuestionType());
            dataPojo.setId("" + inCompletedDataList.get(i).getId());
            dataPojo.setOptionText("" + inCompletedDataList.get(i).getOptionText());
            dataPojo.setOptionCount(inCompletedDataList.get(i).getOptionCount());
            dataPojo.setAnswered(inCompletedDataList.get(i).isAnswered());
            dataPojo.setCheckedPosition(inCompletedDataList.get(i).getCheckedPosition());
            dataPojo.setAnswer("" + inCompletedDataList.get(i).getAnswer());
            dataPojo.setPhotoLink("" + inCompletedDataList.get(i).getPhotoLink());
            dataPojo.setAdditionalText("" + inCompletedDataList.get(i).getAdditionalTextFiled());
            dataPojo.setCode("" + inCompletedDataList.get(i).getCode());
            dataPojo.setGroup("" + inCompletedDataList.get(i).getGroup());
            dataPojo.setOption1("" + inCompletedDataList.get(i).getOption1());
            dataPojo.setOption2("" + inCompletedDataList.get(i).getOption2());
            dataPojo.setOption3("" + inCompletedDataList.get(i).getOption3());
            dataPojo.setOption4("" + inCompletedDataList.get(i).getOption4());
            dataPojo.setOption5("" + inCompletedDataList.get(i).getOption5());
            dataPojo.setIsPhoto("" + inCompletedDataList.get(i).getIsPhoto());
            dataPojo.setPartDescription("" + inCompletedDataList.get(i).getPartDescription());
            dataPojo.setPartNumber("" + inCompletedDataList.get(i).getPartNumber());
            dataPojo.setPartQuantity("" + inCompletedDataList.get(i).getPartQuantity());
            dataPojo.setManPower("" + inCompletedDataList.get(i).getManPower());
            dataPojo.setIsRepaired("" + inCompletedDataList.get(i).getIsRepaired());
            dataPojo.setUnit("" + inCompletedDataList.get(i).getUnit());
            dataPojo.setUnitFixed("" + inCompletedDataList.get(i).getUnitFixed());
            dataPojo.setDateString("" + GeneralHelpers.currentDate(new Date()));
            dataPojo.setPreAnswer("" + inCompletedDataList.get(0).getPreAnswer());

            dataListSorted.add(dataPojo);
        }


        return dataListSorted;

    }


    public static String currentDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // get current date time with Date()
        return dateFormat.format(date).toString();
    }

    public static String getCurrentTime() {
        System.currentTimeMillis();

        return String.valueOf(System.currentTimeMillis());
    }

    public static void saveDataToGeneralDB(Activity context, String jobTicketNumber, String equipmentNumber, String equipmentType, String serviceType, String preAnswer) {

        GeneralDatabase db = new GeneralDatabase(context);
        DatabasePojo pojo = new DatabasePojo();
        pojo.setSiteName("" + GeneralHelpers.getSiteName(context));
        pojo.setCustomerName("" + GeneralHelpers.getCustomerName(context));
        pojo.setTechName("" + GeneralHelpers.getTechnicianName(context));
        pojo.setDateString("" + GeneralHelpers.currentDate(new Date()));
        pojo.setJobTicketNumber("" + jobTicketNumber);
        pojo.setEquipmentType("" + equipmentType);
        pojo.setServiceType("" + serviceType);
        pojo.setSignature("");
        pojo.setEquipNumber("" + equipmentNumber);
        pojo.setPreAnswer("" + preAnswer);
        db.addGeneralDataToDb(pojo);
        db.close();
    }


}
