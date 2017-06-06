package com.cears.serviceapp.NewDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cears.serviceapp.models.PreQuestionModel;

/**
 * Created by Dhiren on 4/15/2017.
 */

public class NewGeneralDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static String DATABASE_NAME = "database_iserve.db";


    private static String TABLE_PREQUESTION = "TABLE_PREQUESTION";
    private static String COLUMN_ID = "COLUMN_ID";
    private static String PRE_Q_ID = "PRE_Q_ID";
    private static String PRE_QUESTION = "PRE_QUESTION";


    public NewGeneralDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_PREQUESTIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_PREQUESTION + "("
                + COLUMN_ID + " INTEGER primary key AUTOINCREMENT,"
                + PRE_Q_ID + "TEXT,"
                + PRE_QUESTION + "TEXT" + ");";

        db.execSQL(CREATE_TABLE_PREQUESTIONS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXIST " + TABLE_PREQUESTION);
        onCreate(db);
    }

  /*  public boolean tableexits() {

        SQLiteDatabase mDatabase = openOrCreateDatabase("database_i_serve.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor c = null;
        boolean tableExists = false;
*//* get cursor on it *//*
        try {
            c = mDatabase.query(TABLE_PREQUESTION, null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
    *//* fail *//*
            Log.d("TAG ", TABLE_PREQUESTION + " doesn't exist :(((");
        }

        return tableExists;
    }
    */

    public void addGeneralDataToDb(PreQuestionModel preQuestionModel) {

        //   tableexits();

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

  /*      for (int i = 0; i < preQuestionModel.getPreQid().length; i++) {
            value.put(PRE_Q_ID, "" + preQuestionModel.getPreQid());
            value.put(PRE_QUESTION, "" + preQuestionModel.getPreQuestion());
        }*/
        value.put(PRE_Q_ID, "11");
        value.put(PRE_QUESTION, "11");

        db.insert(TABLE_PREQUESTION, null, value);

        db.close(); // Closing database connection
    }

    public void deletePreQuestion() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_PREQUESTION, null, null);

            db.execSQL("delete from " + TABLE_PREQUESTION);

            //   db.execSQL("DELETE FROM " + TABLE_PREQUESTION + "'");
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
