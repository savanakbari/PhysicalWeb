package org.physical_web.physicalweb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBlocal extends SQLiteOpenHelper {



        private static final String DATABASE_NAME = "tagdatabase.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TAG_DATA = "tag_data";
        public static final String ID = "id";
        public static final String LOCATION = "location";
        public static final String OFFICEHOURS = "officehours";
        public static final String NAME = "otherinfo";
        public static final String DESCRIPTION = "description";

        private static final String CREATE_TABLE ="CREATE TABLE " + TAG_DATA +"(" +ID + " INTEGER," + LOCATION + " TEXT,"
                + OFFICEHOURS + " TEXT," + NAME + " TEXT" + DESCRIPTION + "TEXT" + ")";

        public  static DBlocal instance;

        private DBlocal(Context context){
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        public static DBlocal getInstance(Context context) {
        if (instance == null) {
            instance = new DBlocal(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();
        db.beginTransaction();
        db.execSQL(CREATE_TABLE);
        addData(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db  , int i ,int j){
        db.beginTransaction();
        db.beginTransaction();
        db.execSQL("DROP TABLE IF EXISTS" + TAG_DATA);
        db.setTransactionSuccessful();
        db.endTransaction();
        onCreate(db);

    }

    public void addData(SQLiteDatabase db){
        try {
            ContentValues cv= new ContentValues();
            cv.put(ID,1);
            cv.put(LOCATION,"ERB 100");
            cv.put(OFFICEHOURS,"01:00-03:00 PM");
            cv.put(NAME,"Dr. Elmasri Office");
            cv.put(DESCRIPTION,"Graduate Advisor");
            db.insert(TAG_DATA,null,cv);

            cv.put(ID,2);
            cv.put(LOCATION,"ERB 120");
            cv.put(OFFICEHOURS,"11:00-01:00 PM");
            cv.put(NAME,"Dr. Stephen Morgan");
            cv.put(DESCRIPTION,"Teaches Distributed Systems");
            db.insert(TAG_DATA,null,cv);

            cv.put(ID,3);
            cv.put(LOCATION,"ERB 220");
            cv.put(OFFICEHOURS,"04:00-05:00 PM");
            cv.put(NAME,"Dr. Gautam Das");
            cv.put(DESCRIPTION,"Researcher");
            db.insert(TAG_DATA,null,cv);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    }





