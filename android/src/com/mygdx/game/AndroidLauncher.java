package com.mygdx.game;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	String idm;
	String namem;
	String levelm;
	String experiencem;
	String spellsm;
	String healthm;
	String manam;
	String modattack;
	String moddef;
	String modcontrol;
	private static final String PREFS_FILE = "Account";
	private static final String PREF_NAME = "isDB";
	SharedPreferences settings;
	Drop mgg = new Drop();
	private String coinm;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);



		DataBaseHelper dataBaseHelper = new DataBaseHelper(AndroidLauncher.this);
		SQLiteDatabase db = getBaseContext().openOrCreateDatabase("My_DB.db", MODE_PRIVATE, null);
		//db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, level INTEGER, experience INTEGER, spells, health INTEGER, mana INTEGER, modattack INTEGER, moddef INTEGER, modcontrol INTEGER)");
//		db.execSQL("DROP TABLE IF EXISTS Persons");
		if (!settings.getString(PREF_NAME,"false").equals("true")){
			dataBaseHelper.onCreate(db);
			ContentValues cv = new ContentValues();//хранит все данные для передачи в бд

			cv.put("name", "hero");
			cv.put("level", 1);
			cv.put("experience", 0);
			cv.put("spells", "20301010101");
			cv.put("health", 10);
			cv.put("mana", 50);
			cv.put("modattack",0);
			cv.put("moddef", 0);
			cv.put("modcontrol", 0);
			db.insert("Persons", null, cv);

//			ContentValues ecv = new ContentValues();//хранит все данные для передачи в бд
//
//			ecv.put("name", "enemy");
//			ecv.put("level", 1);
//			ecv.put("experience", 1);
//			ecv.put("spells", "80901010101");
//			ecv.put("health", 15);
//			ecv.put("mana", 10);
//			ecv.put("modattack",0);
//			ecv.put("moddef", 0);
//			ecv.put("modcontrol", 0);
//			db.insert("Persons", null, ecv);

			SharedPreferences.Editor prefEditor = settings.edit();
			prefEditor.putString(PREF_NAME, "true");
			prefEditor.apply();
		}

		//dataBaseHelper.addInfo("player", 1, 0, 1234, 100, 100);
		//dataBaseHelper.addInfo("player", 1, 0, "20301060101", 100, 100, 0, 0, 0);

		Cursor query = db.rawQuery("SELECT * FROM Persons WHERE name = \"hero\";", null);
		while (query.moveToNext()) {
			//db.execSQL("UPDATE Persons SET spells = \"20301010101\" WHERE name = \"hero\";");
//			spellsm = query.getString(4);
//			mgg.setSpellswhat(spellsm);
			coinm = query.getString(3);
			mgg.setCoin(Integer.parseInt(coinm));
			healthm = query.getString(5);
			mgg.setMaxhealth(Integer.parseInt(healthm));
			manam = query.getString(6);
			mgg.setMaxmana(Integer.parseInt(manam));
			modattack = query.getString(7);
			mgg.setModattack(Integer.parseInt(modattack));
			moddef = query.getString(8);
			mgg.setModdef(Integer.parseInt(moddef));
			modcontrol = query.getString(9);
			mgg.setModcontrol(Integer.parseInt(modcontrol));

		}
		query.close();

//		Cursor enemyquery = db.rawQuery("SELECT * FROM Persons WHERE id = 2;", null);
//		while (enemyquery.moveToNext()) {
//			spellsm = enemyquery.getString(4);
//			mgg.enemysetSpellswhat(spellsm);
//			healthm = enemyquery.getString(5);
//			mgg.enemysetMaxhealth(Integer.parseInt(healthm));
//			manam = enemyquery.getString(6);
//			mgg.enemysetMaxmana(Integer.parseInt(manam));
//			modattack = enemyquery.getString(7);
//			mgg.enemysetModattack(Integer.parseInt(modattack));
//			moddef = enemyquery.getString(8);
//			mgg.enemysetModdef(Integer.parseInt(moddef));
//			modcontrol = enemyquery.getString(9);
//			mgg.enemysetModcontrol(Integer.parseInt(modcontrol));
//
//		}
//		enemyquery.close();
		db.close();

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true){
//					try {
//
//
//
//						DataBaseHelper dataBaseHelper = new DataBaseHelper(AndroidLauncher.this);
//						SQLiteDatabase db = getBaseContext().openOrCreateDatabase("My_DB.db", MODE_PRIVATE, null);
//						//db.execSQL("DROP TABLE IF EXISTS Persons");
//						//dataBaseHelper.onCreate(db);
//						//dataBaseHelper.addInfo("player", 1, 0, 1234, 100, 100);
//						String[] newparams =  mgg.changedb();
//						db.execSQL("UPDATE Persons SET health = " +newparams[0]+" WHERE id = 1;");
//						db.execSQL("UPDATE Persons SET mana = " +newparams[1]+" WHERE id = 1;");
//						db.execSQL("UPDATE Persons SET spells = "+newparams[2]+" WHERE id = 1;");
//						db.execSQL("UPDATE Persons SET modattack = " +newparams[3]+" WHERE id = 1;");
//						db.execSQL("UPDATE Persons SET moddef = " +newparams[4]+" WHERE id = 1;");
//						db.execSQL("UPDATE Persons SET modcontrol = " +newparams[5]+" WHERE id = 1;");
////						String[] enemynewparams =  mgg.enemychangedb();
////						db.execSQL("UPDATE Persons SET health = " +enemynewparams[0]+" WHERE id = 2;");
////						db.execSQL("UPDATE Persons SET mana = " +enemynewparams[1]+" WHERE id = 2;");
////						db.execSQL("UPDATE Persons SET spells = "+enemynewparams[2]+" WHERE id = 2;");
////						db.execSQL("UPDATE Persons SET modattack = " +enemynewparams[3]+" WHERE id = 2;");
////						db.execSQL("UPDATE Persons SET moddef = " +enemynewparams[4]+" WHERE id = 2;");
////						db.execSQL("UPDATE Persons SET modcontrol = " +enemynewparams[5]+" WHERE id = 2;");
//						Cursor query = db.rawQuery("SELECT * FROM Persons;", null);
//						while (query.moveToNext()) {
////							spellsm = query.getString(4);
////							mgg.setSpellswhat(spellsm);
//							healthm = query.getString(5);
//							mgg.setMaxhealth(Integer.parseInt(healthm));
//							manam = query.getString(6);
//							mgg.setMaxmana(Integer.parseInt(manam));
//							modattack = query.getString(7);
//							mgg.setModattack(Integer.parseInt(modattack));
//							moddef = query.getString(8);
//							mgg.setModdef(Integer.parseInt(moddef));
//							modcontrol = query.getString(9);
//							mgg.setModcontrol(Integer.parseInt(modcontrol));
//
//						}
//						query.close();
//
////						Cursor enemyquery = db.rawQuery("SELECT * FROM Persons WHERE id = 2;", null);
////						while (enemyquery.moveToNext()) {
////							spellsm = enemyquery.getString(4);
////							mgg.enemysetSpellswhat(spellsm);
////							healthm = enemyquery.getString(5);
////							mgg.enemysetMaxhealth(Integer.parseInt(healthm));
////							manam = enemyquery.getString(6);
////							mgg.enemysetMaxmana(Integer.parseInt(manam));
////							modattack = enemyquery.getString(7);
////							mgg.enemysetModattack(Integer.parseInt(modattack));
////							moddef = enemyquery.getString(8);
////							mgg.enemysetModdef(Integer.parseInt(moddef));
////							modcontrol = enemyquery.getString(9);
////							mgg.enemysetModcontrol(Integer.parseInt(modcontrol));
////
////						}
////						enemyquery.close();
//						db.close();
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						throw new RuntimeException(e);
//					}
//				}
//
//			}
//		}).start();

		initialize(mgg, config);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		DataBaseHelper dataBaseHelper = new DataBaseHelper(AndroidLauncher.this);
		SQLiteDatabase db = getBaseContext().openOrCreateDatabase("My_DB.db", MODE_PRIVATE, null);
		Cursor query = db.rawQuery("SELECT * FROM Persons WHERE name = \"hero\";", null);
		while (query.moveToNext()) {
			db.execSQL("UPDATE Persons SET spells = \"20301010101\" WHERE name = \"hero\";");
//			spellsm = query.getString(4);
//			mgg.setSpellswhat(spellsm);
			healthm = query.getString(5);
			mgg.setMaxhealth(Integer.parseInt(healthm));
			manam = query.getString(6);
			mgg.setMaxmana(Integer.parseInt(manam));
			modattack = query.getString(7);
			mgg.setModattack(Integer.parseInt(modattack));
			moddef = query.getString(8);
			mgg.setModdef(Integer.parseInt(moddef));
			modcontrol = query.getString(9);
			mgg.setModcontrol(Integer.parseInt(modcontrol));

		}
		query.close();
		String[] newparams =  mgg.changedb();
		db.execSQL("UPDATE Persons SET health = " +newparams[0]+" WHERE id = 1;");
		db.execSQL("UPDATE Persons SET mana = " +newparams[1]+" WHERE id = 1;");
		db.execSQL("UPDATE Persons SET spells = "+newparams[2]+" WHERE id = 1;");
		db.execSQL("UPDATE Persons SET modattack = " +newparams[3]+" WHERE id = 1;");
		db.execSQL("UPDATE Persons SET moddef = " +newparams[4]+" WHERE id = 1;");
		db.execSQL("UPDATE Persons SET modcontrol = " +newparams[5]+" WHERE id = 1;");
		db.execSQL("UPDATE Persons SET experience = " +newparams[6]+" WHERE id = 1;");
	}
}
