package com.t4t.serviceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.t4t.serviceapp.Pojo.DatabasePojo;

import java.util.ArrayList;

import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_ADDITIONAL_TEXT;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_ANSWER;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_CUSTOMER_NAME;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_DATA_ID;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_DATE;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_EQUIPMENT_TYPE;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_EQUIP_NUMBER;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_GEO_LAT;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_GEO_LONG;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_GROUP;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_ID;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_IS_ANSWERED_NAME;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_IS_PHOTO;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_IS_REPAIRED;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_IS_SUBMITTED_CLICKED;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_JOB_TICKET_NUMBER;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_MANPOWER;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTIONS_TEXT;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_1;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_2;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_3;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_4;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_5;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_CHECKED_NUMBER_;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_COUNT;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_PART_DESCRIPTION;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_PART_NUMBER;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_PART_QUANTITY;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_PHOTO_LINK;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_PRE_ANSWER;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_QUESTION;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_QUESTION_TYPE;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_SERVICE_TYPE;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_SIGNATURE;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_SITE_NAME;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_SURVEY_START_TIME;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_TECH_NAME;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_UNIT;
import static com.t4t.serviceapp.Database.IncompleteDatabase.COLUMN_UNIT_FIXED;
import static com.t4t.serviceapp.Database.IncompleteDatabase.TABLE_GENERAL_DATA;
import static com.t4t.serviceapp.Database.IncompleteDatabase.TABLE_INCOMPLETE_DATA;
import static com.t4t.serviceapp.Database.IncompleteDatabase.TABLE_LOCAL__DATA;
import static com.t4t.serviceapp.Database.SubmitDatabase.TABLE_SUBMIT_DATA;

/**
 * Created by VIKAS SAHU on 23/12/16.
 */

public class GeneralDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //LocalDatabase Name
    private static String DATABASE_NAME = "database_i_service.db";


    public GeneralDatabase(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERAL_DATA);

        // Create tables again
        onCreate(db);
    }


    public void addGeneralDataToDb(DatabasePojo databasePojo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_JOB_TICKET_NUMBER, "" + databasePojo.getJobTicketNumber());
        values.put(COLUMN_EQUIPMENT_TYPE, "" + databasePojo.getEquipmentType());
        values.put(COLUMN_SERVICE_TYPE, "" + databasePojo.getServiceType());
        values.put(COLUMN_SIGNATURE, "" + databasePojo.getSignature());
        values.put(COLUMN_EQUIP_NUMBER, "" + databasePojo.getEquipNumber());
        values.put(COLUMN_TECH_NAME, "" + databasePojo.getTechName());
        values.put(COLUMN_CUSTOMER_NAME, "" + databasePojo.getCustomerName());
        values.put(COLUMN_SITE_NAME, "" + databasePojo.getSiteName());
        values.put(COLUMN_PRE_ANSWER, "" + databasePojo.getPreAnswer());


        values.put(COLUMN_SURVEY_START_TIME, "" + databasePojo.getSurveyStartTime());
        values.put(COLUMN_GEO_LAT, "" + databasePojo.getGeoLat());
        values.put(COLUMN_GEO_LONG, "" + databasePojo.getGeoLang());
        values.put(COLUMN_IS_SUBMITTED_CLICKED, "" + databasePojo.getSubmitted());

        values.put(COLUMN_DATE, "" + databasePojo.getDateString());
        db.insert(TABLE_GENERAL_DATA, null, values);
        db.close(); // Closing database connection
    }

    //updating
    public void updateGeneralData(String JonTicketNumber, String techName, String customerName, String siteName, String signatureLink, String preAnswer, String survey_start_time, String isSubmitted) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (!techName.equals("")) {
            values.put(COLUMN_TECH_NAME, techName);
        }

        if (!customerName.equals("")) {
            values.put(COLUMN_CUSTOMER_NAME, customerName);
        }
        if (!siteName.equals("")) {
            values.put(COLUMN_SITE_NAME, siteName);
        }
        if (!signatureLink.equals("")) {
            values.put(COLUMN_SIGNATURE, signatureLink);
        }
        if (!preAnswer.equals("")) {
            values.put(COLUMN_PRE_ANSWER, preAnswer);
        }

        if (!survey_start_time.equals("")) {
            values.put(COLUMN_SURVEY_START_TIME, survey_start_time);
        }

        if (!isSubmitted.equals("")) {
            values.put(COLUMN_IS_SUBMITTED_CLICKED, isSubmitted);
        }
        db.update(TABLE_GENERAL_DATA, values, COLUMN_JOB_TICKET_NUMBER + "=" + JonTicketNumber, null);
        db.close();
    }

    public void deleteDatabaseTable(String databaseTableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + databaseTableName);
    }

    public boolean deleteEntry(String TABLE_NAME, String JonTicketNumber, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, COLUMN_JOB_TICKET_NUMBER + "=" + JonTicketNumber, null) > 0;
    }

    public void deleteDatabase(String dataBaseName) {
        deleteDatabase(dataBaseName);

    }


    public ArrayList<DatabasePojo> getGeneralData(String jobTicketNumber) {

        String selectQuery = "SELECT  * FROM " + TABLE_GENERAL_DATA + " WHERE " + COLUMN_JOB_TICKET_NUMBER + " = " + jobTicketNumber;
        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DatabasePojo pojo = new DatabasePojo();
                pojo.setJobTicketNumber(cursor.getString(1));
                pojo.setEquipmentType(cursor.getString(2));
                pojo.setServiceType(cursor.getString(3));
                pojo.setSignature(cursor.getString(4));
                pojo.setEquipNumber(cursor.getString(5));
                pojo.setTechName(cursor.getString(6));
                pojo.setCustomerName(cursor.getString(7));
                pojo.setSiteName(cursor.getString(8));
                pojo.setPreAnswer(cursor.getString(9));
                pojo.setSurveyStartTime(cursor.getString(10));
                pojo.setGeoLat(cursor.getString(11));
                pojo.setGeoLang(cursor.getString(12));
                pojo.setSubmitted(cursor.getString(13));
                pojo.setDateString(cursor.getString(14));
                dataList.add(pojo);
            } while (cursor.moveToNext());
        }

        Log.e("LocalDatabase", "size : " + cursor.getCount());
        return dataList;

    }


//    public String getSiteName(String jobTicketNumber){
//        String selectQuery = "SELECT  * FROM " + TABLE_GENERAL_DATA + " WHERE " + COLUMN_JOB_TICKET_NUMBER + " = " + jobTicketNumber;
//        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        Log.e("LocalDatabase", "size : " + cursor.getCount());
//        return   cursor.getString(8);
//    }

}