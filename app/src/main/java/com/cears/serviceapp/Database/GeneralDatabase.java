package com.cears.serviceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cears.serviceapp.Pojo.DatabasePojo;
import com.cears.serviceapp.models.EquipmentTypeSubResponse;
import com.cears.serviceapp.models.GetAllJobSiteSubResponse;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.PartsModel;
import com.cears.serviceapp.models.PreQuestionModel;
import com.cears.serviceapp.models.QuestionSubResponse;
import com.cears.serviceapp.models.QuestionsEquipmentType;
import com.cears.serviceapp.models.SectionNameModel;
import com.cears.serviceapp.models.ServiceTypeSubResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cears.serviceapp.Database.IncompleteDatabase.*;
import static com.cears.serviceapp.Database.SubmitDatabase.TABLE_SUBMIT_DATA;

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


/*
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
*/


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


        String CREATE_TABLE_PRE_QUESTION = "CREATE TABLE IF NOT EXISTS " + TABLE_PREQUESTION + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_Q_ID + " TEXT,"
                + PRE_QUESTION + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_PRE_QUESTION);


        String CREATE_TABLE_EQUIPMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_EQUIPMENTTYPE + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_EQUIP_ID + " TEXT,"
                + PRE_EQUIPTYPE + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_EQUIPMENT);


        String CREATE_TABLE_SERVICETYPE = "CREATE TABLE IF NOT EXISTS " + TABLE_SERVICETYPE + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_SERVICE_ID + " TEXT,"
                + PRE_SERVICETYPE + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_SERVICETYPE);


        String CREATE_TABLE_SECTIONAME = "CREATE TABLE IF NOT EXISTS " + TABLE_SECTIONAME + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_SECTION_ID + " TEXT,"
                + PRE_SECTIONNAME + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_SECTIONAME);


        String CREATE_TABLE_PARTS = "CREATE TABLE IF NOT EXISTS " + TABLE_PARTS + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_P_ID + " TEXT,"
                + PRE_PARTS_ID + " TEXT,"
                + PRE_PART_MANUFACTURER + " TEXT,"
                + PRE_PART_DESCRIPTION + " TEXT,"
                + PRE_PART_UNIT_OF_MEASURE + " TEXT,"
                + PRE_PARTS_CATEGORY + " TEXT,"
                + PRE_PART_SUB_CATEGORY + " TEXT,"
                + PRE_SECTION_TYPE + " TEXT,"
                + PRE_SA_MANUFACTURERE + " TEXT,"
                + PRE_SA_MODEL + " TEXT,"
                + PRE_EQPT_TYPE + " TEXT,"
                + PRE_EQPT_MANUFACTURER + " TEXT,"
                + PRE_MANUFACTURER_MODEL + " TEXT,"
                + PRE_UNIT_OF_MEASURE + " TEXT,"
                + PRE_MANUFACTURER_NAME + " TEXT,"
                + PRE_SUB_CATEGORY_NAME + " TEXT,"
                + PRE_CATEGORY_NAME + " TEXT,"
                + PRE_SECTION_NAME + " TEXT,"
                + PRE_EQUIPMENT_TYPE + " TEXT,"
                + PRE_SA_MFGR_NAME + " TEXT,"
                + PRE_EQUIPMENT_MFGR_NAME + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_PARTS);


        String CREATE_TABLE_ALLJOB_SITE = "CREATE TABLE IF NOT EXISTS " + TABLE_ALLJOB_SITE + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + ALLJOB_SITE_ID + " TEXT,"
                + ALLJOB_SITE_NAME + " TEXT,"
                + ALLJOB_SITE_TYPE + " TEXT,"
                + ALLJOB_PRIMARY_CUSTOMER + " TEXT,"
                + ALLJOB_CONTACT_NAME + " TEXT,"
                + ALLJOB_EMAIL + " TEXT,"
                + ALLJOB_SITE_ADDRESS + " TEXT,"
                + ALLJOB_SITE_CITY + " TEXT,"
                + ALLJOB_PHONE + " TEXT,"
                + ALLJOB_FAX + " TEXT,"
                + ALLJOB_SALES_PERSON + " TEXT,"
                + ALLJOB_LAT + " TEXT,"
                + ALLJOB_LONG + " TEXT,"
                + ALLJOB_IS_DELETED + " TEXT,"
                + ALLJOB_CREATION_DATETIME + " TEXT,"
                + ALLJOB_MODIFICATION_DATETIME + " TEXT,"
                + ALLJOB_SITE_EHNS_DATE + " TEXT,"
                + ALLJOB_DELETION_DATETIME + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_ALLJOB_SITE);


        String CREATE_TABLE_ALLJOB = "CREATE TABLE IF NOT EXISTS " + TABLE_ALLJOB + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + ALLJOB_JOB_ID + " TEXT,"
                + ALLJOB_USER_ID + " TEXT,"
                + ALLJOB_EQUIPMENT_TYPE + " TEXT,"
                + ALLJOB_SERVICE_TYPE + " TEXT,"
                + ALLJOB_JOB_TICKET_NO + " TEXT,"
                + ALLJOB_EQUIPMENT_ID + " TEXT,"
                + ALLJOB_SITE_ID + " TEXT,"
                + ALLJOB_CUSTOMER_ID + " TEXT,"
                + ALLJOB_WORK_TYPE + " TEXT,"
                + ALLJOB_SCHEDULE_DATE + " TEXT,"
                + ALLJOB_START_TIME + " TEXT,"
                + ALLJOB_END_TIME + " TEXT,"
                + ALLJOB_LAT + " TEXT,"
                + ALLJOB_LONG + " TEXT,"
                + ALLJOB_REMARK + " TEXT,"
                + ALLJOB_SIGNATURE + " TEXT,"
                + ALLJOB_CONTRACT_TYPE + " TEXT,"
                + ALLJOB_CONTRACT_ID + " TEXT,"
                + ALLJOB_JOB_TYPE + " TEXT,"
                + ALLJOB_SCHEDULE_ID + " TEXT,"
                + ALLJOB_SLOTMSG + " TEXT,"
                + ALLJOB_IS_DELETED + " TEXT,"
                + ALLJOB_CREATION_DATETIME + " TEXT,"
                + ALLJOB_MODIFICATION_DATETIME + " TEXT,"
                + ALLJOB_DELETION_DATETIME + " TEXT,"
                + ALLJOB_ADMIN_ID + " TEXT,"
                + ALLJOB_ADMIN_USERNAME + " TEXT,"
                + ALLJOB_ADMIN_EMAIL_ID + " TEXT,"
                + ALLJOB_ADMIN_PASSWORD + " TEXT,"
                + ALLJOB_ADMIN_MOBILE + " TEXT,"
                + ALLJOB_ADMIN_STATUS + " TEXT,"
                + ALLJOB_ADMIN_ROLE_GROUP + " TEXT,"
                + ALLJOB_JOBSTATE + " TEXT,"
                + ALLJOB_JOB_EHNS_DATE + " TEXT,"
                + ALLJOB_ANSWER + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_ALLJOB);


        String CREATE_TABLE_QUESTIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " ("
                + COL_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_Q_ID + " TEXT,"
                + PRE_SECTION_ID + " TEXT,"
                + PRE_TYPE + " TEXT,"
                + PRE_PHOTO + " TEXT,"
                + PRE_UNIT + " TEXT,"
                + PRE_CODE + " TEXT,"
                + PRE_QUESTION + " TEXT,"
                + PRE_MAJOR + " TEXT,"
                + PRE_MINOR + " TEXT,"
                + PRE_CLEANNG + " TEXT,"
                + PRE_R1 + " TEXT,"
                + PRE_R2 + " TEXT,"
                + PRE_R3 + " TEXT,"
                + PRE_R4 + " TEXT,"
                + PRE_R5 + " TEXT,"
                + PRE_VALUE + " TEXT,"
                + PRE_IS_DELETED + " TEXT,"
                + PRE_CREATION_DATETIME + " TEXT,"
                + PRE_MODIFICATION_DATETIME + " TEXT,"
                + PRE_DELETION_DATETIME + " TEXT,"
                + PRE_EQUIPMENT_TYPES + " TEXT" + ");";
        db.execSQL(CREATE_TABLE_QUESTIONS);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERAL_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREQUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPMENTTYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICETYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTIONAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLJOB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLJOB_SITE);

        // Create tables again
        onCreate(db);
    }


    public void insertPreQuestion(PreQuestionModel preQuestionModel, String i) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRE_Q_ID, "" + preQuestionModel.getPreQid());
        values.put(PRE_QUESTION, i + " : " + preQuestionModel.getPreQuestion());

        db.insert(TABLE_PREQUESTION, null, values);

        Log.e("TAG", "iServ response: Data Added In SQL ");

        db.close(); // Closing database connection
    }


    public List<PreQuestionModel> getAllPreQuestion() {
        List<PreQuestionModel> questionlist = new ArrayList<PreQuestionModel>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PREQUESTION;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PreQuestionModel questionModel = new PreQuestionModel();
                questionModel.setPreQid(cursor.getString(1));
                questionModel.setPreQuestion(cursor.getString(2));

                Log.e("TAG", "iServ response: Question Revert " + cursor.getString(1) + " " + cursor.getString(2));

                // Adding contact to list
                questionlist.add(questionModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionlist;
    }

    public void deletePreQuestion() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_PREQUESTION, null, null);

            //        db.execSQL("delete from " + TABLE_PREQUESTION);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void insertEquipmentType(EquipmentTypeSubResponse equipmentTypeModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRE_EQUIP_ID, "" + equipmentTypeModel.getId());
        values.put(PRE_EQUIPTYPE, "" + equipmentTypeModel.getEquipmentType());

        db.insert(TABLE_EQUIPMENTTYPE, null, values);

        Log.e("TAG", "iServ response: Data Added In EquipmentType SQL ");

        db.close(); // Closing database connection
    }


    public List<EquipmentTypeSubResponse> getEquipmentype() {
        List<EquipmentTypeSubResponse> typeModelList = new ArrayList<EquipmentTypeSubResponse>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_EQUIPMENTTYPE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EquipmentTypeSubResponse equipmentTypeModel = new EquipmentTypeSubResponse();
                equipmentTypeModel.setId(cursor.getString(1));
                equipmentTypeModel.setEquipmentType(cursor.getString(2));

                Log.e("TAG", "iServ response: EquipmentType Revert " + cursor.getString(1) + " " + cursor.getString(2));

                // Adding contact to list
                typeModelList.add(equipmentTypeModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return typeModelList;
    }


    public void deleteEquipmentType() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_EQUIPMENTTYPE, null, null);

            // db.execSQL("delete from " + TABLE_EQUIPMENTTYPE);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void insertServicelist(ServiceTypeSubResponse serviceTypeModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PRE_SERVICE_ID, "" + serviceTypeModel.getId());
        values.put(PRE_SERVICETYPE, "" + serviceTypeModel.getServiceName());

        db.insert(TABLE_SERVICETYPE, null, values);

        Log.e("TAG", "iServ response: SERVICETYPE Added In SQL ");

        db.close(); // Closing database connection
    }


    public List<ServiceTypeSubResponse> getServiceType() {
        List<ServiceTypeSubResponse> serviceTypeModels = new ArrayList<ServiceTypeSubResponse>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SERVICETYPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ServiceTypeSubResponse serviceTypeModel = new ServiceTypeSubResponse();
                serviceTypeModel.setId(cursor.getString(1));
                serviceTypeModel.setServiceName(cursor.getString(2));

                Log.e("TAG", "iServ response: ServiceType Revert " + cursor.getString(1) + " " + cursor.getString(2));

                // Adding contact to list
                serviceTypeModels.add(serviceTypeModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return serviceTypeModels;
    }


    public void deleteServiceType() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_SERVICETYPE, null, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void insertParts(PartsModel partsModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRE_P_ID, "" + partsModel.getId());
        values.put(PRE_PARTS_ID, "" + partsModel.getPartId());
        values.put(PRE_PART_MANUFACTURER, "" + partsModel.getPartManufacturer());
        values.put(PRE_PART_DESCRIPTION, "" + partsModel.getPartDescription());
        values.put(PRE_PART_UNIT_OF_MEASURE, "" + partsModel.getPartUnitOfMeasure());
        values.put(PRE_PARTS_CATEGORY, "" + partsModel.getPartCategory());
        values.put(PRE_PART_SUB_CATEGORY, "" + partsModel.getPartSubCategory());
        values.put(PRE_SECTION_TYPE, "" + partsModel.getSectionType());
        values.put(PRE_SA_MANUFACTURERE, "" + partsModel.getSaManufacturer());
        values.put(PRE_SA_MODEL, "" + partsModel.getSaModel());
        values.put(PRE_EQPT_TYPE, "" + partsModel.getEqptType());
        values.put(PRE_EQPT_MANUFACTURER, "" + partsModel.getEqptManufacturer());
        values.put(PRE_MANUFACTURER_MODEL, "" + partsModel.getManufacturerModel());
        values.put(PRE_UNIT_OF_MEASURE, "" + partsModel.getUnitOfMeasure());
        values.put(PRE_MANUFACTURER_NAME, "" + partsModel.getManufacturerName());
        values.put(PRE_SUB_CATEGORY_NAME, "" + partsModel.getSubCategoryName());
        values.put(PRE_CATEGORY_NAME, "" + partsModel.getCategoryName());
        values.put(PRE_SECTION_NAME, "" + partsModel.getSectionName());
        values.put(PRE_EQUIPMENT_TYPE, "" + partsModel.getEquipmentType());
        values.put(PRE_SA_MFGR_NAME, "" + partsModel.getSaMfgrName());
        values.put(PRE_EQUIPMENT_MFGR_NAME, "" + partsModel.getEquipmentMfgrName());

        db.insert(TABLE_PARTS, null, values);

        Log.e("TAG", "iServ response: Parts Added In SQL ");

        db.close(); // Closing database connection
    }


    public List<PartsModel> getParts() {
        List<PartsModel> partsModelList = new ArrayList<PartsModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PARTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PartsModel partsModel = new PartsModel();
                partsModel.setId(cursor.getString(1));
                partsModel.setSectionName(cursor.getString(2));

                Log.e("TAG", "iServ response: parts Revert " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));

                partsModelList.add(partsModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return partsModelList;
    }


    public void deleteParts() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_PARTS, null, null);

            //      db.execSQL("delete from " + TABLE_PARTS);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void insertSectionName(SectionNameModel sectionNameModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRE_SECTION_ID, "" + sectionNameModel.getId());
        values.put(PRE_SECTIONNAME, "" + sectionNameModel.getSectionName());

        db.insert(TABLE_SECTIONAME, null, values);

        Log.e("TAG", "iServ response: SECTIONAME Added In SQL ");

        db.close(); // Closing database connection
    }


    public List<SectionNameModel> getSectionName() {
        List<SectionNameModel> sectionNameModelList = new ArrayList<SectionNameModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SECTIONAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SectionNameModel sectionNameModel = new SectionNameModel();
                sectionNameModel.setId(cursor.getString(1));
                sectionNameModel.setSectionName(cursor.getString(2));

                Log.e("TAG", "iServ response: ServiceType Revert " + cursor.getString(1) + " " + cursor.getString(2));

                // Adding contact to list
                sectionNameModelList.add(sectionNameModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return sectionNameModelList;
    }


    public void deleteSectionName() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_SECTIONAME, null, null);

            //       db.execSQL("delete from " + TABLE_SECTIONAME);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void insertAllJob_Site(GetAllJobSiteSubResponse getAllJobSiteResponse, String side_id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ALLJOB_SITE_ID, "" + getAllJobSiteResponse.getSiteId());
        values.put(ALLJOB_SITE_NAME, "" + getAllJobSiteResponse.getSiteName());
        values.put(ALLJOB_SITE_TYPE, "" + getAllJobSiteResponse.getSiteType());
        values.put(ALLJOB_PRIMARY_CUSTOMER, "" + getAllJobSiteResponse.getPrimaryCustomer());
        values.put(ALLJOB_CONTACT_NAME, "" + getAllJobSiteResponse.getContactName());
        values.put(ALLJOB_EMAIL, "" + getAllJobSiteResponse.getEmail());
        values.put(ALLJOB_SITE_ADDRESS, "" + getAllJobSiteResponse.getSiteAddress());
        values.put(ALLJOB_SITE_CITY, "" + getAllJobSiteResponse.getCity());
        values.put(ALLJOB_PHONE, "" + getAllJobSiteResponse.getPhone());
        values.put(ALLJOB_SALES_PERSON, "" + getAllJobSiteResponse.getSalesPerson());
        values.put(ALLJOB_SITE_CITY, "" + getAllJobSiteResponse.getCity());
        values.put(ALLJOB_PHONE, "" + getAllJobSiteResponse.getPhone());
        values.put(ALLJOB_LAT, "" + getAllJobSiteResponse.getLat());
        values.put(ALLJOB_LONG, "" + getAllJobSiteResponse.getLong());
        values.put(ALLJOB_IS_DELETED, "" + getAllJobSiteResponse.getIsDeleted());
        values.put(ALLJOB_CREATION_DATETIME, "" + getAllJobSiteResponse.getCreationDatetime());
        // values.put(ALLJOB_SITE_EHNS_DATE, getAllJobSiteResponse.getEhnsdate());
        values.put(ALLJOB_MODIFICATION_DATETIME, "" + getAllJobSiteResponse.getModificationDatetime());

        String selectQuery = "SELECT * FROM " + TABLE_ALLJOB_SITE + " WHERE " + ALLJOB_SITE_ID + "=" + side_id + "";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            db.update(TABLE_ALLJOB_SITE, values, ALLJOB_SITE_ID + "=?", new String[]{getAllJobSiteResponse.getSiteId()});
            Log.e("TAG", "iServ Uptade: All Job Data Added In SQL ");
        } else {
            db.insert(TABLE_ALLJOB_SITE, null, values);

            Log.e("TAG", "iServ Insert: All Job Data Added In SQL ");
        }
        db.close();


     /*   db.insert(TABLE_ALLJOB_SITE, null, values);
        Log.e("TAG", "iServ response: All Job Site Data Added In SQL ");
        db.close(); // Closing database connection*/
    }

    String equimnentStr;

    public void insertAllJob(GetAllJob_Jobs getAllJob_jobs, String job_id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ALLJOB_JOB_ID, "" + getAllJob_jobs.getJobId());
        values.put(ALLJOB_USER_ID, "" + getAllJob_jobs.getUserId());

        Log.e("TAG", "iServ   getEquipmentType" + getAllJob_jobs.getEquipmentType());
        if (getAllJob_jobs.getEquipmentType() != null) {
            equimnentStr = new Gson().toJson(getAllJob_jobs.getEquipmentType());
            values.put(ALLJOB_EQUIPMENT_TYPE, equimnentStr);
        }

        Log.e("TAG", "iServ Insert equimnentStr" + equimnentStr);

        //   values.put(ALLJOB_EQUIPMENT_TYPE, "" + getAllJob_jobs.getEquipmentType());
        values.put(ALLJOB_SERVICE_TYPE, "" + getAllJob_jobs.getServiceType());
        values.put(ALLJOB_JOB_TICKET_NO, "" + getAllJob_jobs.getJobTicketNo());
        values.put(ALLJOB_EQUIPMENT_ID, "" + getAllJob_jobs.getEquipmentId());
        values.put(ALLJOB_SITE_ID, "" + getAllJob_jobs.getSiteId());
        values.put(ALLJOB_CUSTOMER_ID, "" + getAllJob_jobs.getCustomerId());
        values.put(ALLJOB_WORK_TYPE, "" + getAllJob_jobs.getWorkType());
        values.put(ALLJOB_SCHEDULE_DATE, "" + getAllJob_jobs.getScheduleDate());
        values.put(ALLJOB_START_TIME, "" + getAllJob_jobs.getStartTime());
        values.put(ALLJOB_END_TIME, "" + getAllJob_jobs.getEndTime());
        values.put(ALLJOB_LAT, "" + getAllJob_jobs.getLat());
        values.put(ALLJOB_LONG, "" + getAllJob_jobs.getLong());
        values.put(ALLJOB_REMARK, "" + getAllJob_jobs.getRemark());
        values.put(ALLJOB_SIGNATURE, "" + getAllJob_jobs.getSignature());
        values.put(ALLJOB_CONTRACT_TYPE, "" + getAllJob_jobs.getContractType());
        values.put(ALLJOB_CONTRACT_ID, "" + getAllJob_jobs.getContractId());
        values.put(ALLJOB_JOB_TYPE, "" + getAllJob_jobs.getJobType());
        values.put(ALLJOB_SCHEDULE_ID, "" + getAllJob_jobs.getScheduleId());
        values.put(ALLJOB_SLOTMSG, "" + getAllJob_jobs.getSlotmsg());
        values.put(ALLJOB_IS_DELETED, "" + getAllJob_jobs.getIsDeleted());
        values.put(ALLJOB_CREATION_DATETIME, "" + getAllJob_jobs.getCreationDatetime());
        values.put(ALLJOB_MODIFICATION_DATETIME, "" + getAllJob_jobs.getModificationDatetime());
        values.put(ALLJOB_DELETION_DATETIME, "" + getAllJob_jobs.getDeletionDatetime());

        values.put(ALLJOB_ADMIN_ID, "" + getAllJob_jobs.getAdminId());
        values.put(ALLJOB_ADMIN_USERNAME, "" + getAllJob_jobs.getUsername());
        values.put(ALLJOB_ADMIN_EMAIL_ID, "" + getAllJob_jobs.getEmailId());
        values.put(ALLJOB_ADMIN_PASSWORD, "" + getAllJob_jobs.getPassword());
        values.put(ALLJOB_ADMIN_MOBILE, "" + getAllJob_jobs.getMobile());
        values.put(ALLJOB_ADMIN_STATUS, "" + getAllJob_jobs.getStatus());
        values.put(ALLJOB_ADMIN_ROLE_GROUP, "" + getAllJob_jobs.getRoleGroup());

        //     values.put(ALLJOB_JOBSTATE, "" + getAllJob_jobs.getAll_jobs_state());
        values.put(ALLJOB_JOB_EHNS_DATE, "" + getAllJob_jobs.getAll_jobs_date());
        values.put(ALLJOB_ANSWER, getAllJob_jobs.getAll_job_ans());

        String selectQuery = "SELECT * FROM " + TABLE_ALLJOB + " WHERE " + ALLJOB_JOB_ID + "=" + job_id + "";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            db.update(TABLE_ALLJOB, values, ALLJOB_JOB_ID + "=?", new String[]{getAllJob_jobs.getJobId()});
            Log.e("TAG", "iServ Uptade: All Job Data Added In SQL ");
        } else {
            db.insert(TABLE_ALLJOB, null, values);
            Log.e("TAG", "iServ Insert: All Job Data Added In SQL ");
        }
        db.close();

   /*     Log.e("TAG", "iServ response: All Job Data Added In SQL ");
        closeCursor(cursor, db);
        db.insert(TABLE_ALLJOB, null, values);*/

    }


    public List<GetAllJobSiteSubResponse> getAllJob_Site() {
        List<GetAllJobSiteSubResponse> allJobSiteList = new ArrayList<GetAllJobSiteSubResponse>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ALLJOB_SITE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GetAllJobSiteSubResponse jobSubResponse = new GetAllJobSiteSubResponse();

                jobSubResponse.setSiteId(cursor.getString(1));
                jobSubResponse.setSiteName(cursor.getString(2));
         /*       if (cursor.getString(3) != null) {
                    GetAllJobEquipmentType jobEquipmentType = new Gson().fromJson(cursor.getString(3), GetAllJobEquipmentType.class);
                    jobSubResponse.setEquipmentType(jobEquipmentType);
                }*/
                jobSubResponse.setSiteType(cursor.getString(3));
                jobSubResponse.setPrimaryCustomer(cursor.getString(4));
                jobSubResponse.setContactName(cursor.getString(5));
                jobSubResponse.setEmail(cursor.getString(6));
                jobSubResponse.setSiteAddress(cursor.getString(7));
                jobSubResponse.setCity(cursor.getString(8));
                jobSubResponse.setPhone(cursor.getString(9));
                jobSubResponse.setFax(cursor.getString(10));
                jobSubResponse.setSalesPerson(cursor.getString(11));
                jobSubResponse.setLat(cursor.getString(12));
                jobSubResponse.setLong(cursor.getString(13));
                jobSubResponse.setIsDeleted(cursor.getString(14));
                jobSubResponse.setCreationDatetime(cursor.getString(15));
                jobSubResponse.setModificationDatetime(cursor.getString(16));
                jobSubResponse.setEhnsdate(cursor.getString(17));
                jobSubResponse.setDeletionDatetime(cursor.getString(18));

             /*   if (cursor.getString(25) != null) {
                    GetAllJobEquipment jobEquipment = new Gson().fromJson(cursor.getString(24), GetAllJobEquipment.class);
                    jobSubResponse.setEquipment(jobEquipment);
                }*/

             /*   Log.e("TAG", "iServ response: AllJoab Revert "
                        + "PRE_ALLJOB user ID" + cursor.getString(1) + "PRE_ALLJOB_EQUIPMENT_TYPE "
                        + cursor.getString(2) + " GetAllEquipmenType.class "
                        + cursor.getString(3) + " GetAllJobSite.class "
                        + cursor.getString(22) + "GetAllJobCustomer.class "
                        + cursor.getString(23) + "GetAllJobTechnician.class "
                        + cursor.getString(24) + "Equiment class "
                        + cursor.getString(25));*/

                // Adding contact to list
                allJobSiteList.add(jobSubResponse);
            } while (cursor.moveToNext());
        }

        // return contact list
        return allJobSiteList;
    }

    public GetAllJobSiteSubResponse getAllJob_Site_BySite(String side_id) {
        String selectQuery = "SELECT * FROM " + TABLE_ALLJOB_SITE + " WHERE " + ALLJOB_SITE_ID + "='" + side_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GetAllJobSiteSubResponse jobSubResponse = new GetAllJobSiteSubResponse();
                jobSubResponse.setSiteId(cursor.getString(1));
                jobSubResponse.setSiteName(cursor.getString(2));
                jobSubResponse.setSiteType(cursor.getString(3));
                jobSubResponse.setPrimaryCustomer(cursor.getString(4));
                jobSubResponse.setContactName(cursor.getString(5));
                jobSubResponse.setEmail(cursor.getString(6));
                jobSubResponse.setSiteAddress(cursor.getString(7));
                jobSubResponse.setCity(cursor.getString(8));
                jobSubResponse.setPhone(cursor.getString(9));
                jobSubResponse.setFax(cursor.getString(10));
                jobSubResponse.setSalesPerson(cursor.getString(11));
                jobSubResponse.setLat(cursor.getString(12));
                jobSubResponse.setLong(cursor.getString(13));
                jobSubResponse.setIsDeleted(cursor.getString(14));
                jobSubResponse.setCreationDatetime(cursor.getString(15));
                jobSubResponse.setModificationDatetime(cursor.getString(16));
                jobSubResponse.setEhnsdate(cursor.getString(17));
                jobSubResponse.setDeletionDatetime(cursor.getString(18));
                Log.e("TAG", "iServ response:  Added In SQL ");

                return jobSubResponse;
            } while (cursor.moveToNext());

        }

        return null;
    }


    public List<GetAllJob_Jobs> getAllJob(String jobType, int type) {
        List<GetAllJob_Jobs> allJobSubResponseList = new ArrayList<GetAllJob_Jobs>();
        String save = "save";
        String submit = "submit";
        String selectQuery = "";
        if (type == 0) {
            selectQuery = "SELECT * FROM " + TABLE_ALLJOB + " WHERE " + ALLJOB_JOB_TYPE + "='" + jobType + "' AND " + ALLJOB_JOBSTATE + " IS NULL";
        } else if (type == 1) {
            selectQuery = "SELECT * FROM " + TABLE_ALLJOB + " WHERE " + ALLJOB_JOBSTATE + "='" + save + "' AND " + ALLJOB_JOB_TYPE + "='" + jobType + "'";
        } else if (type == 2) {
            selectQuery = "SELECT * FROM " + TABLE_ALLJOB + " WHERE " + ALLJOB_JOBSTATE + "='" + submit + "' AND " + ALLJOB_JOB_TYPE + "='" + jobType + "'";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                GetAllJob_Jobs allJob_jobses = new GetAllJob_Jobs();

                allJob_jobses.setJobId(cursor.getString(1));
                allJob_jobses.setUserId(cursor.getString(2));

                if (cursor.getString(3) != null) {
                    EquipmentTypeSubResponse jobEquipmentType = new Gson().fromJson(cursor.getString(3), EquipmentTypeSubResponse.class);
                    allJob_jobses.setEquipmentType(jobEquipmentType);
                }

                //   allJob_jobses.setEquipmentType(cursor.getString(3));
                allJob_jobses.setServiceType(cursor.getString(4));
                allJob_jobses.setJobTicketNo(cursor.getString(5));
                allJob_jobses.setEquipmentId(cursor.getString(6));
                allJob_jobses.setSiteId(cursor.getString(7));
                allJob_jobses.setCustomerId(cursor.getString(8));
                allJob_jobses.setWorkType(cursor.getString(9));
                allJob_jobses.setScheduleDate(cursor.getString(10));
                allJob_jobses.setStartTime(cursor.getString(11));
                allJob_jobses.setEndTime(cursor.getString(12));
                allJob_jobses.setLat(cursor.getString(13));
                allJob_jobses.setLong(cursor.getString(14));
                allJob_jobses.setRemark(cursor.getString(15));
                allJob_jobses.setSignature(cursor.getString(16));
                allJob_jobses.setContractType(cursor.getString(17));
                allJob_jobses.setContractId(cursor.getString(18));
                allJob_jobses.setJobType(cursor.getString(19));
                allJob_jobses.setScheduleId(cursor.getString(20));
                allJob_jobses.setSlotmsg(cursor.getString(21));
                allJob_jobses.setIsDeleted(cursor.getString(22));
                allJob_jobses.setCreationDatetime(cursor.getString(23));
                allJob_jobses.setModificationDatetime(cursor.getString(24));
                allJob_jobses.setDeletionDatetime(cursor.getString(25));

                allJob_jobses.setAdminId(cursor.getString(26));
                allJob_jobses.setUsername(cursor.getString(27));
                allJob_jobses.setEmailId(cursor.getString(28));
                allJob_jobses.setPassword(cursor.getString(29));
                allJob_jobses.setMobile(cursor.getString(30));
                allJob_jobses.setStatus(cursor.getString(31));
                allJob_jobses.setRoleGroup(cursor.getString(32));

                allJob_jobses.setAll_jobs_state(cursor.getString(33));
                allJob_jobses.setAll_jobs_date(cursor.getString(34));
                allJob_jobses.setAll_job_ans(cursor.getString(35));

          /*      if (cursor.getString(28) != null) {
                    if (cursor.getString(28) != null) {
                        List<PreQuestionModel> preQuestionModels = new Gson().fromJson(cursor.getString(28), new TypeToken<List<PreQuestionModel>>() {
                        }.getType());
                        allJob_jobses.setQuestionanslist((ArrayList<PreQuestionModel>) preQuestionModels);
                    }
                }*/

                allJobSubResponseList.add(allJob_jobses);

                Log.e("TAG", "iServ All Job by Save : Retrive In SQL ");

            } while (cursor.moveToNext());
        }

        return allJobSubResponseList;
    }


    public List<GetAllJob_Jobs> getAllJob() {
        List<GetAllJob_Jobs> allJobSubResponseList = new ArrayList<GetAllJob_Jobs>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ALLJOB;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                GetAllJob_Jobs allJob_jobses = new GetAllJob_Jobs();

                allJob_jobses.setJobId(cursor.getString(1));
                allJob_jobses.setUserId(cursor.getString(2));
                String str = cursor.getString(cursor.getColumnIndex(ALLJOB_EQUIPMENT_TYPE));
                if (str != null) {
                    Log.e("EquipmentTypeResponse", "=  " + str);
                    EquipmentTypeSubResponse jobEquipmentType = new Gson().fromJson(cursor.getString(3), EquipmentTypeSubResponse.class);
                    allJob_jobses.setEquipmentType(jobEquipmentType);
                }
                //   allJob_jobses.setEquipmentType(cursor.getString(3));
                allJob_jobses.setServiceType(cursor.getString(4));
                allJob_jobses.setJobTicketNo(cursor.getString(5));
                allJob_jobses.setEquipmentId(cursor.getString(6));
                allJob_jobses.setSiteId(cursor.getString(7));
                allJob_jobses.setCustomerId(cursor.getString(8));
                allJob_jobses.setWorkType(cursor.getString(9));
                allJob_jobses.setScheduleDate(cursor.getString(10));
                allJob_jobses.setStartTime(cursor.getString(11));
                allJob_jobses.setEndTime(cursor.getString(12));
                allJob_jobses.setLat(cursor.getString(13));
                allJob_jobses.setLong(cursor.getString(14));
                allJob_jobses.setRemark(cursor.getString(15));
                allJob_jobses.setSignature(cursor.getString(16));
                allJob_jobses.setContractType(cursor.getString(17));
                allJob_jobses.setContractId(cursor.getString(18));
                allJob_jobses.setJobType(cursor.getString(19));
                allJob_jobses.setScheduleId(cursor.getString(20));
                allJob_jobses.setSlotmsg(cursor.getString(21));
                allJob_jobses.setIsDeleted(cursor.getString(22));
                allJob_jobses.setCreationDatetime(cursor.getString(23));
                allJob_jobses.setModificationDatetime(cursor.getString(24));
                allJob_jobses.setDeletionDatetime(cursor.getString(25));
                allJob_jobses.setAdminId(cursor.getString(26));
                allJob_jobses.setUsername(cursor.getString(27));
                allJob_jobses.setEmailId(cursor.getString(28));
                allJob_jobses.setPassword(cursor.getString(29));
                allJob_jobses.setMobile(cursor.getString(30));
                allJob_jobses.setStatus(cursor.getString(31));
                allJob_jobses.setRoleGroup(cursor.getString(32));

                allJob_jobses.setAll_jobs_state(cursor.getString(33));
                allJob_jobses.setAll_jobs_date(cursor.getString(34));
                allJob_jobses.setAll_job_ans(cursor.getString(35));

            /*    if (cursor.getString(28) != null) {
                    if (cursor.getString(28) != null) {
                        List<PreQuestionModel> preQuestionModels = new Gson().fromJson(cursor.getString(28), new TypeToken<List<PreQuestionModel>>() {
                        }.getType());
                        allJob_jobses.setQuestionanslist((ArrayList<PreQuestionModel>) preQuestionModels);
                    }
                }
*/
                allJobSubResponseList.add(allJob_jobses);

                Log.e("TAG", "iServ All Job : Retrive In SQL ");

            } while (cursor.moveToNext());
        }


        // return  list
        return allJobSubResponseList;
    }


    public GetAllJob_Jobs getAllJob_ByJobID(String job_id) {
        // Select All Query
        String selectQuery = "";
        selectQuery = "SELECT * FROM " + TABLE_ALLJOB + " WHERE " + ALLJOB_JOB_ID + "='" + job_id + "' AND " + ALLJOB_ANSWER + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                GetAllJob_Jobs allJob_jobses = new GetAllJob_Jobs();

                allJob_jobses.setJobId(cursor.getString(1));
                allJob_jobses.setUserId(cursor.getString(2));

                if (cursor.getString(3) != null) {
                    EquipmentTypeSubResponse jobEquipmentType = new Gson().fromJson(cursor.getString(3), EquipmentTypeSubResponse.class);
                    allJob_jobses.setEquipmentType(jobEquipmentType);
                }
                //   allJob_jobses.setEquipmentType(cursor.getString(3));
                allJob_jobses.setServiceType(cursor.getString(4));
                allJob_jobses.setJobTicketNo(cursor.getString(5));
                allJob_jobses.setEquipmentId(cursor.getString(6));
                allJob_jobses.setSiteId(cursor.getString(7));
                allJob_jobses.setCustomerId(cursor.getString(8));
                allJob_jobses.setWorkType(cursor.getString(9));
                allJob_jobses.setScheduleDate(cursor.getString(10));
                allJob_jobses.setStartTime(cursor.getString(11));
                allJob_jobses.setEndTime(cursor.getString(12));
                allJob_jobses.setLat(cursor.getString(13));
                allJob_jobses.setLong(cursor.getString(14));
                allJob_jobses.setRemark(cursor.getString(15));
                allJob_jobses.setSignature(cursor.getString(16));
                allJob_jobses.setContractType(cursor.getString(17));
                allJob_jobses.setContractId(cursor.getString(18));
                allJob_jobses.setJobType(cursor.getString(19));
                allJob_jobses.setScheduleId(cursor.getString(20));
                allJob_jobses.setSlotmsg(cursor.getString(21));
                allJob_jobses.setIsDeleted(cursor.getString(22));
                allJob_jobses.setCreationDatetime(cursor.getString(23));
                allJob_jobses.setModificationDatetime(cursor.getString(24));
                allJob_jobses.setDeletionDatetime(cursor.getString(25));
                allJob_jobses.setAdminId(cursor.getString(26));
                allJob_jobses.setUsername(cursor.getString(27));
                allJob_jobses.setEmailId(cursor.getString(28));
                allJob_jobses.setPassword(cursor.getString(29));
                allJob_jobses.setMobile(cursor.getString(30));
                allJob_jobses.setStatus(cursor.getString(31));
                allJob_jobses.setRoleGroup(cursor.getString(32));
                allJob_jobses.setAll_jobs_state(cursor.getString(33));
                allJob_jobses.setAll_jobs_date(cursor.getString(34));

                if (cursor.getString(35) != null) {
                    if (cursor.getString(35) != null) {
                        List<QuestionSubResponse> preQuestionModels = new Gson().fromJson(cursor.getString(35), new TypeToken<List<QuestionSubResponse>>() {
                        }.getType());
                        allJob_jobses.setQuestionanslist((ArrayList<QuestionSubResponse>) preQuestionModels);
                    }
                }

                Log.e("TAG", "iServ All Job By Id : Retrive In SQL ");

                return allJob_jobses;
            } while (cursor.moveToNext());
        }
        // return  list
        return null;
    }

    public void deleteAllJob() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_ALLJOB, null, null);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeCursor(Cursor cursor, SQLiteDatabase sqLiteDatabase) {
        cursor.close();
        sqLiteDatabase.close();
    }

    public void deleteAllJob_Site() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_ALLJOB_SITE, null, null);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertQuestions(QuestionSubResponse questionSubResponse) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRE_Q_ID, "" + questionSubResponse.getQuestionId());
        values.put(PRE_SECTION_ID, "" + questionSubResponse.getSectionId());
        values.put(PRE_TYPE, "" + questionSubResponse.getType());
        values.put(PRE_PHOTO, "" + questionSubResponse.getPhoto());
        values.put(PRE_UNIT, "" + questionSubResponse.getUnit());
        values.put(PRE_CODE, "" + questionSubResponse.getCode());
        values.put(PRE_QUESTION, "" + questionSubResponse.getQuestion());
        values.put(PRE_MAJOR, "" + questionSubResponse.getMajor());
        values.put(PRE_MINOR, "" + questionSubResponse.getMinor());
        values.put(PRE_CLEANNG, "" + questionSubResponse.getCleaning());
        values.put(PRE_R1, "" + questionSubResponse.getR1());
        values.put(PRE_R2, "" + questionSubResponse.getR2());
        values.put(PRE_R3, "" + questionSubResponse.getR3());
        values.put(PRE_R4, "" + questionSubResponse.getR4());
        values.put(PRE_R5, "" + questionSubResponse.getR5());

        Log.e("TAG", "questionSubResponse.getR1() " + questionSubResponse.getQuestion());

        values.put(PRE_VALUE, "" + questionSubResponse.getValue());
        values.put(PRE_IS_DELETED, "" + questionSubResponse.getIsDeleted());
        values.put(PRE_CREATION_DATETIME, "" + questionSubResponse.getCreationDatetime());
        values.put(PRE_MODIFICATION_DATETIME, "" + questionSubResponse.getModificationDatetime());
        values.put(PRE_DELETION_DATETIME, "" + questionSubResponse.getDeletionDatetime());

        if (questionSubResponse.getEquipmentTypes() != null) {
            String equimnentStr = new Gson().toJson(questionSubResponse.getEquipmentTypes());
            values.put(PRE_EQUIPMENT_TYPES, equimnentStr);
        }

        db.insert(TABLE_QUESTION, null, values);

        Log.e("TAG", "iServ response: Inner Questions Added In SQL ");

        db.close(); // Closing database connection
    }


    public ArrayList<QuestionSubResponse> getQuestions() {
        ArrayList<QuestionSubResponse> questionlist = new ArrayList<QuestionSubResponse>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuestionSubResponse questionSubResponse = new QuestionSubResponse();
                questionSubResponse.setQuestionId(cursor.getString(1));
                questionSubResponse.setSectionId(cursor.getString(2));
                questionSubResponse.setType(cursor.getString(3));
                questionSubResponse.setPhoto(cursor.getString(4));
                questionSubResponse.setUnit(cursor.getString(5));
                questionSubResponse.setCode(cursor.getString(6));
                questionSubResponse.setQuestion(cursor.getString(7));
                questionSubResponse.setMajor(cursor.getString(8));
                questionSubResponse.setMinor(cursor.getString(9));
                questionSubResponse.setCleaning(cursor.getString(10));
                questionSubResponse.setR1(cursor.getString(11));
                questionSubResponse.setR2(cursor.getString(12));
                questionSubResponse.setR3(cursor.getString(13));
                questionSubResponse.setR4(cursor.getString(14));
                questionSubResponse.setR5(cursor.getString(15));
                questionSubResponse.setValue(cursor.getString(16));
                questionSubResponse.setIsDeleted(cursor.getString(17));
                questionSubResponse.setCreationDatetime(cursor.getString(18));
                questionSubResponse.setModificationDatetime(cursor.getString(19));
                questionSubResponse.setDeletionDatetime(cursor.getString(20));

                if (cursor.getString(21) != null) {
                    if (cursor.getString(21) != null) {
                        List<QuestionsEquipmentType> jobEquipment = new Gson().fromJson(cursor.getString(21), new TypeToken<List<QuestionsEquipmentType>>() {
                        }.getType());
                        questionSubResponse.setEquipmentTypes((ArrayList<QuestionsEquipmentType>) jobEquipment);
                    }
                }

                Log.e("TAG", "iServ response: Inner Questions Revert " + "At Zero String" + cursor.getString(0) + cursor.getString(1) + " " + cursor.getString(6) + " "
                        + cursor.getString(7) + " " + cursor.getString(11) + " " + cursor.getString(12) + " " + cursor.getString(13)
                        + " " + cursor.getString(14) + " " + cursor.getString(15));

                questionlist.add(questionSubResponse);
            } while (cursor.moveToNext());
        }

        // return contact list
        return questionlist;
    }


    public void deleteQuestions() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_QUESTION, null, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }











/*

    public List<GetAllJobSiteSubResponse> getSelectedData(String selectedlist) {
        List<GetAllJobSiteSubResponse> allJobSubResponseList = new ArrayList<GetAllJobSiteSubResponse>();

        String query = "SELECT * FROM " + TABLE_ALLJOB + "WHERE " + ALLJOB_JOB_TYPE + "='" + selectedlist+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return allJobSubResponseList;
    }
*/







/*    public void addSubmittedData(SubmiitedDataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_JOB_TICKET_NUMBER, "" + dataModel.getJobTicketNumber());
        values.put(COLUMN_EQUIPMENT_TYPE, "" + dataModel.getEquipmentType());
        values.put(COLUMN_SERVICE_TYPE, "" + dataModel.getServiceType());
        values.put(COLUMN_SIGNATURE, "" + dataModel.getSignature());
        values.put(COLUMN_EQUIP_NUMBER, "" + dataModel.getEquipNumber());
        values.put(COLUMN_TECH_NAME, "" + dataModel.getTechName());
        values.put(COLUMN_CUSTOMER_NAME, "" + dataModel.getCustomerName());
        values.put(COLUMN_SITE_NAME, "" + dataModel.getSiteName());
        values.put(COLUMN_PRE_ANSWER, "" + dataModel.getPreAnswer());


        values.put(COLUMN_SURVEY_START_TIME, "" + dataModel.getSurveyStartTime());
        values.put(COLUMN_GEO_LAT, "" + dataModel.getGeoLat());
        values.put(COLUMN_GEO_LONG, "" + dataModel.getGeoLang());
        values.put(COLUMN_IS_SUBMITTED_CLICKED, "" + dataModel.getSubmitted());

        values.put(COLUMN_DATE, "" + dataModel.getDateString());
        db.insert(TABLE_GENERAL_DATA, null, values);
        db.close(); // Closing database connection
    }*/



 /*   public String[] getPreQuestionfromDB() {

        String selectQuery = "SELECT  * FROM " + TABLE_PREQUESTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] data = null;

        if (cursor.moveToFirst()) {
            do {

                cursor.getString(0);
                cursor.getString(1);

                // get the data into array, or class variable
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }*/


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

    public void updateSiteDate(String site_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        values.put(ALLJOB_SITE_ID, "" + site_id);
        values.put(ALLJOB_SITE_EHNS_DATE, "" + dateFormat.format(date));

        long result = db.update(TABLE_ALLJOB_SITE, values, ALLJOB_SITE_ID + "=?", new String[]{site_id});
        Log.e("LocalDatabase", "size : " + dateFormat.format(date) + ": update count=" + result);
        db.close();
    }

    public void updateJobtype(String job_id, String saveORsubmit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ALLJOB_JOBSTATE, "" + saveORsubmit);

        long result = db.update(TABLE_ALLJOB, values, ALLJOB_JOB_ID + "=?", new String[]{job_id});
        Log.e("LocalDatabase", "size : updateJobtype update count=" + result);
        db.close();

    }


    public void updateAns(String job_id, String QuestAnswer_Str) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ALLJOB_ANSWER, "" + QuestAnswer_Str);
        Log.e("Inner updateAns List ", "" + QuestAnswer_Str + "job_id " + job_id);
        long result = db.update(TABLE_ALLJOB, values, ALLJOB_JOB_ID + "=?", new String[]{job_id});

        Log.e("LocalDatabase", "QuestAnswer_Str update count= " + result);
        db.close();

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