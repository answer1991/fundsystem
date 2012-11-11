package com.chenjun.sqlitehelper;

import com.chenjun.provider.metadata.FundInfoProviderMetaData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FundInfoDatabaseHelper extends SQLiteOpenHelper {
	private static final String createJjjzTableSqlStr = "CREATE TABLE "
			+ FundInfoProviderMetaData.JjjzTableMetaTable.TABLE_NAME + " ("
			+ FundInfoProviderMetaData.JjjzTableMetaTable._ID
			+ " INTEGER PRIMARY KEY,"
			+ FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_DM + " TEXT,"
			+ FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_RQ + " TEXT,"
			+ FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_JZ + " TEXT,"
			+ FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_LJJZ + " TEXT,"
			+ FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_FQJZ + " TEXT);";

	private static final String dropJjjzTableSqlStr = "DROP TABLE IF EXISTS "
			+ FundInfoProviderMetaData.JjjzTableMetaTable.TABLE_NAME;

	public FundInfoDatabaseHelper(Context context) {
		super(context, FundInfoProviderMetaData.DATABASE_NAME, null,
				FundInfoProviderMetaData.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createJjjzTableSqlStr);
		System.out.println("database onCreate method invoked!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(dropJjjzTableSqlStr);
		onCreate(db);
	}

}
