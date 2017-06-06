package com.cears.serviceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.Pojo.SubmitPojo;

import java.util.ArrayList;
import java.util.Date;

import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_ADDITIONAL_TEXT;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_ANSWER;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_CUSTOMER_NAME;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_DATA_ID;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_DATE;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_EQUIPMENT_TYPE;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_EQUIP_NUMBER;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_GEO_LAT;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_GEO_LONG;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_GROUP;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_ID;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_IS_ANSWERED_NAME;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_IS_PHOTO;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_IS_REPAIRED;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_IS_SUBMITTED_CLICKED;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_JOB_TICKET_NUMBER;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_MANPOWER;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTIONS_TEXT;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_1;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_2;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_3;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_4;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_5;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_CHECKED_NUMBER_;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_OPTION_COUNT;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_PART_DESCRIPTION;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_PART_NUMBER;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_PART_QUANTITY;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_PHOTO_LINK;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_PRE_ANSWER;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_QUESTION;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_QUESTION_TYPE;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_SERVICE_TYPE;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_SIGNATURE;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_SITE_NAME;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_SURVEY_START_TIME;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_TECH_NAME;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_UNIT;
import static com.cears.serviceapp.Database.IncompleteDatabase.COLUMN_UNIT_FIXED;
import static com.cears.serviceapp.Database.IncompleteDatabase.TABLE_GENERAL_DATA;
import static com.cears.serviceapp.Database.IncompleteDatabase.TABLE_INCOMPLETE_DATA;
import static com.cears.serviceapp.Database.IncompleteDatabase.TABLE_LOCAL__DATA;


/**
 * Created by VIKAS SAHU on 23/12/16.
 */

public class SubmitDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //LocalDatabase Name
    private static String DATABASE_NAME = "database_i_service.db";

    //Table Name
    public static String TABLE_SUBMIT_DATA = "TABLE_SUBMIT_DATA";

    //Column Name


    public SubmitDatabase(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBMIT_DATA);

        // Create tables again
        onCreate(db);
    }


    public void addSubmitDataToDb(SubmitPojo databasePojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA_ID, databasePojo.getId());
        values.put(COLUMN_JOB_TICKET_NUMBER, databasePojo.getJobTicketNumber());
        values.put(COLUMN_QUESTION, databasePojo.getQuestion());
        values.put(COLUMN_EQUIPMENT_TYPE, databasePojo.getEquipmentType());
        values.put(COLUMN_SERVICE_TYPE, databasePojo.getServiceType());
        values.put(COLUMN_GROUP, databasePojo.getGroup());
        values.put(COLUMN_SIGNATURE, databasePojo.getSignatureLink());
        values.put(COLUMN_PHOTO_LINK, databasePojo.getPhotoLink());
        values.put(COLUMN_EQUIP_NUMBER, databasePojo.getEquipNumber());
        values.put(COLUMN_TECH_NAME, databasePojo.getName());
        values.put(COLUMN_ANSWER, databasePojo.getAnswer());
        values.put(COLUMN_PRE_ANSWER, databasePojo.getPreAnswer());
        values.put(COLUMN_UNIT, databasePojo.getUnit());
        values.put(COLUMN_DATE, GeneralHelpers.currentDate(new Date()));

        values.put(COLUMN_PART_DESCRIPTION, "" + databasePojo.getPartDescription());
        values.put(COLUMN_PART_NUMBER, "" + databasePojo.getPartNumber());
        values.put(COLUMN_PART_QUANTITY, "" + databasePojo.getPartQuantity());
        values.put(COLUMN_MANPOWER, "" + databasePojo.getManPower());
        values.put(COLUMN_IS_REPAIRED, "" + databasePojo.getIsRepaired());

        db.insert(TABLE_SUBMIT_DATA, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<SubmitPojo> getAllSubmittedData() {

        String selectQuery = "SELECT  * FROM " + TABLE_SUBMIT_DATA;
        ArrayList<SubmitPojo> dataList = new ArrayList<SubmitPojo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SubmitPojo pojo = new SubmitPojo();
                pojo.setId("" + cursor.getString(1));
                pojo.setJobTicketNumber("" + cursor.getString(2));
                pojo.setQuestion("" + cursor.getString(3));
                pojo.setEquipmentType("" + cursor.getString(4));
                pojo.setServiceType("" + cursor.getString(5));
                pojo.setGroup("" + cursor.getString(6));
                pojo.setSignatureLink("" + cursor.getString(7));
                pojo.setPhotoLink("" + cursor.getString(8));
                pojo.setAnswer("" + cursor.getString(9));
                pojo.setEquipNumber("" + cursor.getString(10));
                pojo.setPreAnswer("" + cursor.getString(11));
                pojo.setUnit("" + cursor.getString(12));
                pojo.setDateString("" + cursor.getString(13));
                pojo.setName("" + cursor.getString(14));

                pojo.setPartDescription("" + cursor.getString(15));
                pojo.setPartNumber("" + cursor.getString(16));
                pojo.setPartQuantity("" + cursor.getString(17));
                pojo.setManPower("" + cursor.getString(18));
                pojo.setIsRepaired("" + cursor.getString(19));

                dataList.add(pojo);
            } while (cursor.moveToNext());
        }
        db.close();
        return dataList;

    }

}