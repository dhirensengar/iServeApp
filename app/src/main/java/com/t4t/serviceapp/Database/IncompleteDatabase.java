package com.t4t.serviceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.Pojo.IncompletePojo;

import java.util.ArrayList;
import java.util.Date;

import static com.t4t.serviceapp.Database.SubmitDatabase.TABLE_SUBMIT_DATA;

/**
 * Created by VIKAS SAHU on 23/12/16.
 */

public class IncompleteDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //LocalDatabase Name
    private static String DATABASE_NAME = "database_i_service.db";

    //Table Name
    public static String TABLE_INCOMPLETE_DATA = "TABLE_INCOMPLETE_DATA";
    //Table Name
    public static String TABLE_LOCAL__DATA = "TABLE_LOCAL_DATA";
    //Table Name
    public static String TABLE_GENERAL_DATA = "TABLE_GENERAL_DATA";

    //Column Name
    public static String COLUMN_ID = "_ID";

    public static String COLUMN_DATA_ID = "DATA_ID";

    public static String COLUMN_QUESTION_TYPE = "QUESTION_TYPE";

    public static String COLUMN_QUESTION = "QUESTION";

    public static String COLUMN_OPTION_COUNT = "OPTION_COUNT";


    public static String COLUMN_OPTIONS_TEXT = "OPTIONS_TEXT";

    public static String COLUMN_EQUIPMENT_TYPE = "EQUIPMENT_TYPE";

    public static String COLUMN_SERVICE_TYPE = "SERVICE_TYPE";

    public static String COLUMN_GROUP = "GROUP_NAME";

    public static String COLUMN_SIGNATURE = "SIGNATURE";

    public static String COLUMN_IS_PHOTO = "IS_PHOTO";

    public static String COLUMN_PHOTO_LINK = "PHOTO_LINK";

    public static String COLUMN_ADDITIONAL_TEXT = "ADDITION_TEXT";

    public static String COLUMN_JOB_TICKET_NUMBER = "JOB_TICKET_NUMBER";

    public static String COLUMN_EQUIP_NUMBER = "EQUIP_NUMBER";

    public static String COLUMN_TECH_NAME = "TECH_NAME";

    public static String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";

    public static String COLUMN_SITE_NAME = "SITE_NAME";

    public static String COLUMN_PRE_ANSWER = "PRE_ANSWER";

    public static String COLUMN_OPTION_1 = "OPTION_1";

    public static String COLUMN_OPTION_2 = "OPTION_2";

    public static String COLUMN_OPTION_3 = "OPTION_3";

    public static String COLUMN_OPTION_4 = "OPTION_4";

    public static String COLUMN_OPTION_5 = "OPTION_5";

    public static String COLUMN_IS_ANSWERED_NAME = "IS_ANSWERED";

    public static String COLUMN_OPTION_CHECKED_NUMBER_ = "OPTION_CHECKED";

    public static String COLUMN_LAST_COUNT = "LAST_COUNT";

    public static String COLUMN_ANSWER = "ADDITION_ANSWER";

    public static String COLUMN_SURVEY_START_TIME = "SURVEY_START_TIME";

    public static String COLUMN_SURVEY_END_TIME = "SURVEY_END_TIME";

    public static String COLUMN_PART_DESCRIPTION = "PART_DESCRIPTION";

    public static String COLUMN_PART_NUMBER = "PART_NUMBER";

    public static String COLUMN_PART_QUANTITY = "PART_QUANTITY";

    public static String COLUMN_MANPOWER = "PART_MANPOWER";

    public static String COLUMN_IS_REPAIRED = "IS_REPAIRED";

    public static String COLUMN_GEO_LAT = "GEO_LAT";

    public static String COLUMN_GEO_LONG = "GEO_LONG";

    public static String COLUMN_UNIT = "UNIT";

    public static String COLUMN_UNIT_FIXED = "UNIT_FIXED";

    public static String COLUMN_DATE = "DATE";

    public static String COLUMN_IS_SUBMITTED_CLICKED = "IS_SUBMITTED_CLICKED";


    public IncompleteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SAVED_DATA = "CREATE TABLE IF NOT EXISTS " + TABLE_LOCAL__DATA + " ("
                + COLUMN_ID + " INTEGER primary key AUTOINCREMENT,"
                + COLUMN_DATA_ID + " TEXT,"
                + COLUMN_JOB_TICKET_NUMBER + " TEXT,"
                + COLUMN_QUESTION_TYPE + " TEXT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_OPTIONS_TEXT + " TEXT,"
                + COLUMN_EQUIPMENT_TYPE + " TEXT,"
                + COLUMN_SERVICE_TYPE + " TEXT,"
                + COLUMN_GROUP + " TEXT,"
                + COLUMN_SIGNATURE + " TEXT,"
                + COLUMN_IS_PHOTO + " TEXT,"
                + COLUMN_PHOTO_LINK + " TEXT,"
                + COLUMN_ADDITIONAL_TEXT + " TEXT,"
                + COLUMN_EQUIP_NUMBER + " TEXT,"
                + COLUMN_TECH_NAME + " TEXT,"
                + COLUMN_PRE_ANSWER + " TEXT,"
                + COLUMN_UNIT + " TEXT,"
                + COLUMN_DATE + " TEXT,"

                + COLUMN_PART_DESCRIPTION + " TEXT,"
                + COLUMN_PART_NUMBER + " TEXT,"
                + COLUMN_PART_QUANTITY + " TEXT,"
                + COLUMN_MANPOWER + " TEXT,"
                + COLUMN_IS_REPAIRED + " TEXT,"
                + COLUMN_UNIT_FIXED + " TEXT,"

                + COLUMN_OPTION_COUNT + " INT" + ");";

        db.execSQL(CREATE_TABLE_SAVED_DATA);

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SUBMIT_DATA + " ("
                + COLUMN_ID + " INTEGER primary key AUTOINCREMENT,"
                + COLUMN_DATA_ID + " TEXT,"
                + COLUMN_JOB_TICKET_NUMBER + " TEXT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_EQUIPMENT_TYPE + " TEXT,"
                + COLUMN_SERVICE_TYPE + " TEXT,"
                + COLUMN_GROUP + " TEXT,"
                + COLUMN_SIGNATURE + " TEXT,"
                + COLUMN_PHOTO_LINK + " TEXT,"
                + COLUMN_ANSWER + " TEXT,"
                + COLUMN_EQUIP_NUMBER + " TEXT,"
                + COLUMN_PRE_ANSWER + " TEXT,"
                + COLUMN_UNIT + " TEXT,"
                + COLUMN_DATE + " TEXT,"

                + COLUMN_PART_DESCRIPTION + " TEXT,"
                + COLUMN_PART_NUMBER + " TEXT,"
                + COLUMN_PART_QUANTITY + " TEXT,"
                + COLUMN_MANPOWER + " TEXT,"
                + COLUMN_IS_REPAIRED + " TEXT,"

                + COLUMN_TECH_NAME + " TEXT" + ");";
        db.execSQL(CREATE_TABLE);


        String CREATE_TABLE_INCOMPLETED_DATA = "CREATE TABLE IF NOT EXISTS " + TABLE_INCOMPLETE_DATA + " ("
                + COLUMN_ID + " INTEGER primary key AUTOINCREMENT,"
                + COLUMN_DATA_ID + " TEXT,"
                + COLUMN_JOB_TICKET_NUMBER + " TEXT,"
                + COLUMN_QUESTION_TYPE + " TEXT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_OPTIONS_TEXT + " TEXT,"
                + COLUMN_EQUIPMENT_TYPE + " TEXT,"
                + COLUMN_SERVICE_TYPE + " TEXT,"
                + COLUMN_GROUP + " TEXT,"
                + COLUMN_SIGNATURE + " TEXT,"
                + COLUMN_OPTION_1 + " TEXT,"
                + COLUMN_OPTION_2 + " TEXT,"
                + COLUMN_OPTION_3 + " TEXT,"
                + COLUMN_OPTION_4 + " TEXT,"
                + COLUMN_OPTION_5 + " TEXT,"
                + COLUMN_IS_ANSWERED_NAME + " TEXT,"
                + COLUMN_OPTION_CHECKED_NUMBER_ + " TEXT,"
                + COLUMN_ANSWER + " TEXT,"
                + COLUMN_IS_PHOTO + " TEXT,"
                + COLUMN_PHOTO_LINK + " TEXT,"
                + COLUMN_ADDITIONAL_TEXT + " TEXT,"
                + COLUMN_EQUIP_NUMBER + " TEXT,"
                + COLUMN_TECH_NAME + " TEXT,"
                + COLUMN_PRE_ANSWER + " TEXT,"
                + COLUMN_UNIT + " TEXT,"
                + COLUMN_DATE + " TEXT,"


                + COLUMN_PART_DESCRIPTION + " TEXT,"
                + COLUMN_PART_NUMBER + " TEXT,"
                + COLUMN_PART_QUANTITY + " TEXT,"
                + COLUMN_MANPOWER + " TEXT,"
                + COLUMN_IS_REPAIRED + " TEXT,"
                + COLUMN_UNIT_FIXED + " TEXT,"
                + COLUMN_OPTION_COUNT + " INT" + ");";

        db.execSQL(CREATE_TABLE_INCOMPLETED_DATA);

        String CREATE_TABLE_GENERAL_DATA = "CREATE TABLE IF NOT EXISTS " + TABLE_GENERAL_DATA + " ("
                + COLUMN_ID + " INTEGER primary key AUTOINCREMENT,"
                + COLUMN_JOB_TICKET_NUMBER + " TEXT,"
                + COLUMN_EQUIPMENT_TYPE + " TEXT,"
                + COLUMN_SERVICE_TYPE + " TEXT,"
                + COLUMN_SIGNATURE + " TEXT,"
                + COLUMN_EQUIP_NUMBER + " TEXT,"
                + COLUMN_TECH_NAME + " TEXT,"
                + COLUMN_CUSTOMER_NAME + " TEXT,"
                + COLUMN_SITE_NAME + " TEXT,"
                + COLUMN_PRE_ANSWER + " TEXT,"

                + COLUMN_SURVEY_START_TIME + " TEXT,"
                + COLUMN_GEO_LAT + " TEXT,"
                + COLUMN_GEO_LONG + " TEXT,"
                + COLUMN_IS_SUBMITTED_CLICKED + " TEXT,"
                + COLUMN_DATE + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_GENERAL_DATA);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOMPLETE_DATA);

        // Create tables again
        onCreate(db);
    }

    public void addInCompletedDataToDb(IncompletePojo databasePojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATA_ID, databasePojo.getId());
        values.put(COLUMN_JOB_TICKET_NUMBER, databasePojo.getJobTicketNumber());
        values.put(COLUMN_QUESTION_TYPE, databasePojo.getQuestionType());
        values.put(COLUMN_QUESTION, databasePojo.getQuestion());
        values.put(COLUMN_OPTIONS_TEXT, databasePojo.getOptionText());//all answer will store here with comma
        values.put(COLUMN_EQUIPMENT_TYPE, databasePojo.getEquipmentType());
        values.put(COLUMN_SERVICE_TYPE, databasePojo.getServiceType());
        values.put(COLUMN_GROUP, databasePojo.getGroup());
        values.put(COLUMN_SIGNATURE, databasePojo.getSignatureLink());
        values.put(COLUMN_OPTION_1, databasePojo.getOption1());
        values.put(COLUMN_OPTION_2, databasePojo.getOption2());
        values.put(COLUMN_OPTION_3, databasePojo.getOption3());
        values.put(COLUMN_OPTION_4, databasePojo.getOption4());
        values.put(COLUMN_OPTION_5, databasePojo.getOption5());
        values.put(COLUMN_IS_ANSWERED_NAME, databasePojo.isAnswered());
        values.put(COLUMN_OPTION_CHECKED_NUMBER_, databasePojo.getCheckedPosition());
        values.put(COLUMN_ANSWER, databasePojo.getAnswer());
        values.put(COLUMN_IS_PHOTO, databasePojo.getIsPhoto());
        values.put(COLUMN_PHOTO_LINK, databasePojo.getPhotoLink());
        values.put(COLUMN_ADDITIONAL_TEXT, databasePojo.getAdditionalTextFiled());
        values.put(COLUMN_EQUIP_NUMBER, databasePojo.getEquipNumber());
        values.put(COLUMN_TECH_NAME, databasePojo.getName());
        values.put(COLUMN_PRE_ANSWER, databasePojo.getPreAnswer());
        values.put(COLUMN_OPTION_COUNT, 0 + databasePojo.getOptionCount());
        values.put(COLUMN_UNIT, "" + databasePojo.getUnit());
        values.put(COLUMN_DATE, "" + GeneralHelpers.currentDate(new Date()));

        values.put(COLUMN_PART_DESCRIPTION, "" + databasePojo.getPartDescription());
        values.put(COLUMN_PART_NUMBER, "" + databasePojo.getPartNumber());
        values.put(COLUMN_PART_QUANTITY, "" + databasePojo.getPartQuantity());
        values.put(COLUMN_MANPOWER, "" + databasePojo.getManPower());
        values.put(COLUMN_IS_REPAIRED, "" + databasePojo.getIsRepaired());
        values.put(COLUMN_UNIT_FIXED, "" + databasePojo.getUnitFixed());
        db.insert(TABLE_INCOMPLETE_DATA, null, values);
        db.close(); // Closing database connection
    }

    //updating
    public void updateSavedData(String tableName, String JonTicketNumber, String Id, String additionalText, String photoLink, String optionText) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (!additionalText.equals("")) {
            values.put(COLUMN_ADDITIONAL_TEXT, additionalText);
        }
        if (!photoLink.equals("")) {
            values.put(COLUMN_PHOTO_LINK, photoLink);
        }
        if (!optionText.equals("")) {
            values.put(COLUMN_OPTIONS_TEXT, optionText);
        }

        db.update(tableName, values, COLUMN_DATA_ID + " = " + Id + " and " + COLUMN_JOB_TICKET_NUMBER + "=" + JonTicketNumber, null);
        db.close();
    }

    public ArrayList<IncompletePojo> getAllInCompletedDataByTockenNumber(String jobTicketNumber) {

        String selectQuery = "SELECT  * FROM " + TABLE_INCOMPLETE_DATA + " WHERE " + COLUMN_JOB_TICKET_NUMBER + " = " + jobTicketNumber;
        ArrayList<IncompletePojo> dataList = new ArrayList<IncompletePojo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {


                IncompletePojo pojo = new IncompletePojo();
                pojo.setId("" + (cursor.getString(1)));
                pojo.setJobTicketNumber("" + cursor.getString(2));
                pojo.setQuestionType("" + cursor.getString(3));
                pojo.setQuestion("" + cursor.getString(4));
                pojo.setOptionText("" + cursor.getString(5));
                pojo.setEquipmentType("" + cursor.getString(6));
                pojo.setServiceType("" + cursor.getString(7));
                pojo.setGroup("" + cursor.getString(8));
                pojo.setSignatureLink("" + cursor.getString(9));
                pojo.setOption1("" + cursor.getString(10));
                pojo.setOption2("" + cursor.getString(11));
                pojo.setOption3("" + cursor.getString(12));
                pojo.setOption4("" + cursor.getString(13));
                pojo.setOption5("" + cursor.getString(14));
                pojo.setAnswered(cursor.getInt(15) > 0);
                pojo.setCheckedPosition(0 + cursor.getInt(16));
                pojo.setAnswer("" + cursor.getString(17));
                pojo.setIsPhoto("" + cursor.getString(18));
                pojo.setPhotoLink("" + cursor.getString(19));
                pojo.setAdditionalTextFiled("" + cursor.getString(20));
                pojo.setEquipNumber("" + cursor.getString(21));
                pojo.setName("" + cursor.getString(22));
                pojo.setPreAnswer("" + cursor.getString(23));
                pojo.setUnit(cursor.getString(24));
                pojo.setDateString(cursor.getString(25));


                pojo.setPartDescription("" + cursor.getString(26));
                pojo.setPartNumber("" + cursor.getString(27));
                pojo.setPartQuantity("" + cursor.getString(28));
                pojo.setManPower("" + cursor.getString(29));
                pojo.setIsRepaired("" + cursor.getString(30));
                pojo.setUnitFixed("" + cursor.getString(31));
                pojo.setOptionCount(Integer.parseInt(cursor.getString(32)));
                dataList.add(pojo);
            } while (cursor.moveToNext());
        }
        db.close();
        return dataList;

    }

    public ArrayList<IncompletePojo> getAllInCompletedData() {

        String selectQuery = "SELECT  * FROM " + TABLE_INCOMPLETE_DATA;
        ArrayList<IncompletePojo> dataList = new ArrayList<IncompletePojo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                IncompletePojo pojo = new IncompletePojo();
                pojo.setId("" + (cursor.getString(1)));
                pojo.setJobTicketNumber("" + cursor.getString(2));
                pojo.setQuestionType("" + cursor.getString(3));
                pojo.setQuestion("" + cursor.getString(4));
                pojo.setOptionText("" + cursor.getString(5));
                pojo.setEquipmentType("" + cursor.getString(6));
                pojo.setServiceType("" + cursor.getString(7));
                pojo.setGroup("" + cursor.getString(8));
                pojo.setSignatureLink("" + cursor.getString(9));
                pojo.setOption1("" + cursor.getString(10));
                pojo.setOption2("" + cursor.getString(11));
                pojo.setOption3("" + cursor.getString(12));
                pojo.setOption4("" + cursor.getString(13));
                pojo.setOption5("" + cursor.getString(14));
                pojo.setAnswered(cursor.getInt(15) > 0);
                pojo.setCheckedPosition(0 + cursor.getInt(16));
                pojo.setAnswer("" + cursor.getString(17));
                pojo.setPhotoLink("" + cursor.getString(18));
                pojo.setIsPhoto("" + cursor.getString(19));
                pojo.setAdditionalTextFiled("" + cursor.getString(20));

                pojo.setEquipNumber("" + cursor.getString(21));
                pojo.setName("" + cursor.getString(22));
                pojo.setPreAnswer("" + cursor.getString(23));
                pojo.setUnit("" + cursor.getString(24));
                pojo.setDateString(cursor.getString(25));

                pojo.setPartDescription("" + cursor.getString(26));
                pojo.setPartNumber("" + cursor.getString(27));
                pojo.setPartQuantity("" + cursor.getString(28));
                pojo.setManPower("" + cursor.getString(29));
                pojo.setIsRepaired("" + cursor.getString(30));
                pojo.setUnitFixed("" + cursor.getString(31));
                pojo.setOptionCount(Integer.parseInt(cursor.getString(32)));
                dataList.add(pojo);
            } while (cursor.moveToNext());

//            Log.e("Incomplete DB", "cursor size : " + cursor.getCount());
        }
        db.close();
        return dataList;

    }

    public boolean deleteInCompleteEntry(String JonTicketNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_INCOMPLETE_DATA, COLUMN_JOB_TICKET_NUMBER + " = " + JonTicketNumber, null) > 0;
    }

    public boolean isJobAlreadyExisting(String equipmentNumber
            , String ticketNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "Select * from " + TABLE_INCOMPLETE_DATA + " where " + COLUMN_JOB_TICKET_NUMBER + " = " + ticketNumber + " AND " +
                COLUMN_EQUIP_NUMBER + " = " + equipmentNumber;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        Log.e("Count", "getCount() : " + cursor.getCount());
        cursor.close();
        return true;
    }


}