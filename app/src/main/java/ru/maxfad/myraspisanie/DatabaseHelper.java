package ru.maxfad.myraspisanie;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DB_NAME = "rasp1.sqlite";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "myrasp";
    private static String DB_PATH;

    public static final String COLUMN_ID="_id";
    public static final String COLUMN_WEAK = "weak";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_UROKID = "urok_id";
    public static final String COLUMN_UROK = "urok";




    public SQLiteDatabase database;
    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH = "/data/data/ru.maxfad.myraspisanie/databases/";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {

    }

    public void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH + DB_NAME);
            if (!file.exists()) {
                this.getReadableDatabase();
                myInput = myContext.getAssets().open(DB_NAME);

                String outFileName = DB_PATH + DB_NAME;
                myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){

        }
    }


    public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }
}
