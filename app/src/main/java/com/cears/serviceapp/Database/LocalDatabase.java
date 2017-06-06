package com.cears.serviceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.Pojo.DatabasePojo;

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
import static com.cears.serviceapp.Database.SubmitDatabase.TABLE_SUBMIT_DATA;

/**
 * Created by VIKAS SAHU on 23/12/16.
 */

public class LocalDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //LocalDatabase Name
    private static String DATABASE_NAME = "database_i_service.db";

    public LocalDatabase(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCAL__DATA);

        // Create tables again
        onCreate(db);
    }


    public void addSavedDataToDb(DatabasePojo databasePojo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_DATA_ID, databasePojo.getId());
        values.put(COLUMN_JOB_TICKET_NUMBER, "" + databasePojo.getJobTicketNumber());
        values.put(COLUMN_QUESTION_TYPE, "" + databasePojo.getQuestionType());
        values.put(COLUMN_QUESTION, "" + databasePojo.getQuestion());
        values.put(COLUMN_OPTIONS_TEXT, "" + databasePojo.getOptionText());//all answer will store here with comma
        values.put(COLUMN_EQUIPMENT_TYPE, "" + databasePojo.getEquipmentType());
        values.put(COLUMN_SERVICE_TYPE, "" + databasePojo.getServiceType());
        values.put(COLUMN_GROUP, "" + databasePojo.getGroup());
        values.put(COLUMN_SIGNATURE, "" + databasePojo.getSignature());
        values.put(COLUMN_IS_PHOTO, "" + databasePojo.getIsPhoto());
        values.put(COLUMN_PHOTO_LINK, "" + databasePojo.getPhotoLink());
        values.put(COLUMN_ADDITIONAL_TEXT, "" + databasePojo.getAdditionalTextFiled());
        values.put(COLUMN_EQUIP_NUMBER, "" + databasePojo.getEquipNumber());
        values.put(COLUMN_TECH_NAME, "" + databasePojo.getName());
        values.put(COLUMN_PRE_ANSWER, "" + databasePojo.getPreAnswer());
        values.put(COLUMN_UNIT, "" + databasePojo.getUnit());
        values.put(COLUMN_DATE, "" + GeneralHelpers.currentDate(new Date()));
        values.put(COLUMN_PART_DESCRIPTION, "" + databasePojo.getPartDescription());
        values.put(COLUMN_PART_NUMBER, "" + databasePojo.getPartNumber());
        values.put(COLUMN_PART_QUANTITY, "" + databasePojo.getPartQuantity());
        values.put(COLUMN_MANPOWER, "" + databasePojo.getManPower());
        values.put(COLUMN_IS_REPAIRED, "" + databasePojo.getIsRepaired());
        values.put(COLUMN_UNIT_FIXED, "" + databasePojo.getUnitFixed());

        values.put(COLUMN_OPTION_COUNT, 0 + databasePojo.getOptionCount());
        db.insert(TABLE_LOCAL__DATA, null, values);
        db.close(); // Closing database connection
    }

    //updating
    public void updateSavedData(String tableName, String JonTicketNumber, String Id, String additionalText, String photoLink, String optionText, String partNumber, String partDesc, String partQuantity, String manPower, String isRepaired) {

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

        if (!partNumber.equals("")) {
            values.put(COLUMN_PART_NUMBER, partNumber);
        }
        if (!partDesc.equals("")) {
            values.put(COLUMN_PART_DESCRIPTION, partDesc);
        }
        if (!partQuantity.equals("")) {
            values.put(COLUMN_PART_QUANTITY, partQuantity);
        }
        if (!manPower.equals("")) {
            values.put(COLUMN_MANPOWER, manPower);
        }
        if (!isRepaired.equals("")) {
            values.put(COLUMN_IS_REPAIRED, isRepaired);
        }

        if (!optionText.equals("") || !photoLink.equals("") || !additionalText.equals("") || !partNumber.equals("") || !partDesc.equals("") || !partQuantity.equals("") || !manPower.equals("") || !isRepaired.equals("")) {
            db.update(tableName, values, COLUMN_DATA_ID + " = " + Id + " and " + COLUMN_JOB_TICKET_NUMBER + "=" + JonTicketNumber, null);
        }
        db.close();
    }

    public void deleteDatabaseTable(String databaseTableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + databaseTableName);
    }

    public boolean deleteEntry(String TABLE_NAME, String JonTicketNumber, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, COLUMN_DATA_ID + "=" + id + " and " + COLUMN_JOB_TICKET_NUMBER + "=" + JonTicketNumber, null) > 0;
    }

    public ArrayList<DatabasePojo> getAllLocalData(String jobTicketNumber) {

        String selectQuery = "SELECT  * FROM " + TABLE_LOCAL__DATA + " WHERE " + COLUMN_JOB_TICKET_NUMBER + " = " + jobTicketNumber;
        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DatabasePojo pojo = new DatabasePojo();
                pojo.setId(("" + cursor.getString(1)));
                pojo.setJobTicketNumber("" + cursor.getString(2));
                pojo.setQuestionType("" + cursor.getString(3));
                pojo.setQuestion("" + cursor.getString(4));
                pojo.setOptionText("" + cursor.getString(5));
                pojo.setEquipmentType("" + cursor.getString(6));
                pojo.setServiceType("" + cursor.getString(7));
                pojo.setGroup("" + cursor.getString(8));
                pojo.setSignature("" + cursor.getString(9));
                pojo.setIsPhoto("" + cursor.getString(10));
//                pojo.setPhotoLink(""+cursor.getString(11));
                pojo.setAdditionalTextFiled("" + cursor.getString(12));
                pojo.setEquipNumber("" + cursor.getString(13));
                pojo.setName("" + cursor.getString(14));
                pojo.setPreAnswer("" + cursor.getString(15));
                pojo.setUnit("" + cursor.getString(16));
                pojo.setDateString("" + GeneralHelpers.currentDate(new Date()));

                pojo.setPartDescription("" + cursor.getString(18));
                pojo.setPartNumber("" + cursor.getString(19));
                pojo.setPartQuantity("" + cursor.getString(20));
                pojo.setManPower("" + cursor.getString(21));
                pojo.setIsRepaired("" + cursor.getString(22));
                pojo.setUnitFixed("" + cursor.getString(23));
                pojo.setOptionCount(Integer.parseInt(cursor.getString(24)));
                dataList.add(pojo);
            } while (cursor.moveToNext());
        }
        db.close();

        return dataList;

    }

    public String getImageById(String jobTicketNumber, String id) {

        String selectQuery = "SELECT  * FROM " + TABLE_LOCAL__DATA + " WHERE " + COLUMN_DATA_ID + " = " + id + " and " + COLUMN_JOB_TICKET_NUMBER + " = " + jobTicketNumber;
        String imageString = "";
        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                imageString = cursor.getString(11);
            } while (cursor.moveToNext());
        }
        db.close();

        return imageString;

    }

}