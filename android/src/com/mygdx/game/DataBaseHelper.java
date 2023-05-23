package com.mygdx.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final Context context;

    //основные характеристики бд
    private static final String DB_NAME = "My_DB.db";
    private static final int DB_VS = 1;

    //основные атрибуты таблицы в бд
    private static final String TABLE_NAME = "Persons";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LEVEL = "level";
    private static final String COLUMN_EXPERIENCE = "experience";
    private static final String COLUMN_SPELLS = "spells";
    private static final String COLUMN_HEALTH = "health";
    private static final String COLUMN_MANA = "mana";
    private static final String COLUMN_MODATTACK = "modattack";
    private static final String COLUMN_MODDEF = "moddef";
    private static final String COLUMN_MODCONTROL = "modcontrol";



    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VS);
        this.context = context;
    }


    //вызывается при первом создании бд
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LEVEL + " TEXT, " +
                COLUMN_EXPERIENCE + " TEXT, " +
                COLUMN_SPELLS + " TEXT, " +
                COLUMN_HEALTH + " TEXT, " +
                COLUMN_MANA + " TEXT, " +
                COLUMN_MODATTACK + " TEXT, " +
                COLUMN_MODDEF + " TEXT, " +
                COLUMN_MODCONTROL + " TEXT);";
        //для выполнения запросы к базе данных. В метод execSQL() передаем SQL-запрос
        db.execSQL(query);
//        addInfo("player", 1, 0, "20301010101", 100, 100, 0, 0, 0);
//        addInfo("firstgud", 1, 0, "80901010101", 100, 100, 0, 0, 0);
        //addInfo("player", 1, 0, "20301010101", 10, 100, 0, 0, 0);
       // addInfo("firstgud", 1, 0, "80901010101", 10, 100, 0, 0, 0);
        //personsis = false;

    }

    //вызывается при обновлении бд
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);//удаляем только если таблица уже есть в бд
        onCreate(db);
    }

    public void addInfo(String name, int level, int experience, String spells, int health, int mana, int modattack, int moddef, int modcontrol) {
        SQLiteDatabase db = this.getWritableDatabase();//получаем доступ к записи в бд
        ContentValues cv = new ContentValues();//хранит все данные для передачи в бд

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LEVEL, level);
        cv.put(COLUMN_EXPERIENCE, experience);
        cv.put(COLUMN_SPELLS, spells);
        cv.put(COLUMN_HEALTH, health);
        cv.put(COLUMN_MANA, mana);
        cv.put(COLUMN_MODATTACK,modattack);
        cv.put(COLUMN_MODDEF, moddef);
        cv.put(COLUMN_MODCONTROL, modcontrol);

        long res = db.insert(TABLE_NAME, null, cv);
        if (res == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();

        }
    }
}
