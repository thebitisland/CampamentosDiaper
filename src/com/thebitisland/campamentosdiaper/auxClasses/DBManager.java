package com.thebitisland.campamentosdiaper.auxClasses;

import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.preference.PreferenceManager;
import android.util.FloatMath;
import android.util.Log;

public class DBManager {
	/**
	 * Fields
	 */
	// ---------- Database params and declarations ----------
	// DB name and tables
	private static final String DATABASE_NAME = "CampsDB.db";
	private static final String DATABASE_USER_TABLE = "userTable";
	private static final int DATABASE_VERSION = 1;
	// Class declaration
	private SQLiteDatabase ourDB;
	private DbHelper ourHelper;
	private final Context ourContext;

	// ---------- Table field declarations ----------
	// Table 1 - Users (Stores all USEFUL users and their details)
	public static final String KEY_U_RACEID = "_id";
	public static final String KEY_U_FNAME = "first_name";
	public static final String KEY_U_LNAME = "last_name";
	public static final String KEY_U_BORN = "date_of_birth";
	public static final String KEY_U_EMAIL = "email";
	public static final String KEY_U_PHONE = "phone";


	/**
	 * DbHelper class, creates a simple way to add and remove data from the
	 * database.
	 */
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		/**
		 * onCreate functions creates both tables.
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			//db.execSQL("DROP TABLE IF EXISTS " + DATABASE_SESSION_TABLE);
			db.execSQL("CREATE TABLE " + DATABASE_USER_TABLE + " ("
					+ KEY_U_RACEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_U_FNAME + " VARCHAR," 
					+ KEY_U_LNAME + " VARCHAR, " 
					+ KEY_U_BORN + " INTEGER," 
					+ KEY_U_EMAIL + " VARCHAR," 
					+ KEY_U_PHONE + " VARCHAR"
					+ ");");

		}

		/**
		 * OnUpgrade function, set to delete old tables on upgrade.
		 * To be modified further.
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_USER_TABLE);
			onCreate(db);
		}
	}

	/**
	 * Basic constructor.
	 * @param c: Context to be initialized
	 */
	public DBManager(Context c) {
		ourContext = c;
	}

	/**
	 * Open function. Initializes the Database based on the context init'd
	 * in the constructor.
	 * @return The initialized DB class
	 * @throws SQLException
	 */
	public DBManager open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDB = ourHelper.getWritableDatabase();
		return this;
	}

	/**
	 * Close function. No further comment necessary.
	 */
	public void close() {
		ourHelper.close();
	}

}
