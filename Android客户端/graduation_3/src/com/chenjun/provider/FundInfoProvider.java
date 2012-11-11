package com.chenjun.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.chenjun.provider.metadata.FundInfoProviderMetaData;
import com.chenjun.sqlitehelper.FundInfoDatabaseHelper;

public class FundInfoProvider extends ContentProvider{
	private SQLiteOpenHelper sqliteHelper;
	
	private static final int JJJZ_COLLECTION_URI_INDICATOR = 1;
	private static final int JJJZ_DM_URI_INDICATOR = 2;
	private static final int JJGK_COLLECTION_URI_INDICATOR = 3;
	private static final int JJGK_DM_URI_INDICATOR = 4;
	
	//uri比较器
	private static final UriMatcher uriMatcher;
	//初始化uriMatcher
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		uriMatcher.addURI(FundInfoProviderMetaData.AUTHORITY, FundInfoProviderMetaData.JJJZ_TABLE_NAME, JJJZ_COLLECTION_URI_INDICATOR);
		uriMatcher.addURI(FundInfoProviderMetaData.AUTHORITY, FundInfoProviderMetaData.JJJZ_TABLE_NAME + "/#", JJJZ_DM_URI_INDICATOR);
		uriMatcher.addURI(FundInfoProviderMetaData.AUTHORITY, FundInfoProviderMetaData.JJGK_TABLE_NAME, JJGK_COLLECTION_URI_INDICATOR);
		uriMatcher.addURI(FundInfoProviderMetaData.AUTHORITY, FundInfoProviderMetaData.JJGK_TABLE_NAME + "/#", JJGK_DM_URI_INDICATOR);
	}
	
	//jjjz列名信息
	private static final HashMap<String, String> jjjzProjectionMap;
	static {
		jjjzProjectionMap = new HashMap<String, String>();
		
		jjjzProjectionMap.put(FundInfoProviderMetaData.JjjzTableMetaTable._ID, FundInfoProviderMetaData.JjjzTableMetaTable._ID);
		jjjzProjectionMap.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_DM, FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_DM);
		jjjzProjectionMap.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_RQ, FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_RQ);
		jjjzProjectionMap.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_JZ, FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_JZ);
		jjjzProjectionMap.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_LJJZ, FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_LJJZ);
		jjjzProjectionMap.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_FQJZ, FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_FQJZ);
	}
	
	@Override
	public boolean onCreate() {
		sqliteHelper = new FundInfoDatabaseHelper(getContext());
		return true;
	}
	
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
		
		switch(uriMatcher.match(uri)){
		case JJJZ_COLLECTION_URI_INDICATOR:
			sqliteQueryBuilder.setTables(FundInfoProviderMetaData.JjjzTableMetaTable.TABLE_NAME);
			sqliteQueryBuilder.setProjectionMap(jjjzProjectionMap);
			break;
		case JJJZ_DM_URI_INDICATOR:
			sqliteQueryBuilder.setTables(FundInfoProviderMetaData.JjjzTableMetaTable.TABLE_NAME);
			sqliteQueryBuilder.setProjectionMap(jjjzProjectionMap);
//			System.out.println(uri.getPathSegments().get(1));
			sqliteQueryBuilder.appendWhere(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_DM + "='" + uri.getPathSegments().get(1) + "'");
			break;
		default:
			throw new IllegalArgumentException("uri: " + uri + "不合法");
		}
		
		SQLiteDatabase db = sqliteHelper.getReadableDatabase();
		
		Cursor cursor = sqliteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		
		return cursor;
	}
	
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = sqliteHelper.getWritableDatabase();
		if(JJJZ_COLLECTION_URI_INDICATOR == uriMatcher.match(uri)){
			long rowId = db.insert(FundInfoProviderMetaData.JjjzTableMetaTable.TABLE_NAME, FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_DM, values);
			if(rowId > 0){
				return ContentUris.withAppendedId(FundInfoProviderMetaData.JjjzTableMetaTable.CONTENT_URI, rowId);
			}
			throw new IllegalArgumentException("插入数据失败");
		}
		else if(JJGK_COLLECTION_URI_INDICATOR == uriMatcher.match(uri)){
			return null;
		}
		else{
			throw new IllegalArgumentException("uri: " + uri + "不合法"); 
		}
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
