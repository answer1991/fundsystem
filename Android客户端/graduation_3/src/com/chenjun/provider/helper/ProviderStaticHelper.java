package com.chenjun.provider.helper;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.chenjun.fund.model.Jjjz;
import com.chenjun.provider.metadata.FundInfoProviderMetaData;

/**
 * 这个是为Provider提供一些查询和写入的函数
 * Provider是操作本地缓存数据库的一个中间件，可以通过它查询和修改在本地缓存数据库的信息
 * 如查询、插入本地缓存数据库中基金历史净值信息
 * @author zet
 *
 */
public class ProviderStaticHelper {
	private static final String ORDER_BY = "rq asc";
	
	/**
	 * 根据基金的代码从本地缓存数据库里读取旧的基金净值信息
	 * @param activity 显示基金净值历史走势的Activity，不涉及Activity的UI，只用到Android SDK为开发者提供的managedQuery函数，可以去向Provider请求信息。
	 * @param dm 基金代码
	 * @return
	 */
	public static List<Jjjz> getJjjsListFromDB(Activity activity, String dm){
		Uri dmUri = Uri.parse("content://" + FundInfoProviderMetaData.AUTHORITY + "/" + FundInfoProviderMetaData.JJJZ_TABLE_NAME + "/" + dm);
		Cursor cursor = activity.managedQuery(dmUri, null, null, null, ORDER_BY);
		List<Jjjz> list = new LinkedList<Jjjz>();
		
		int dmIndex = cursor.getColumnIndex("dm");
		int jzIndex = cursor.getColumnIndex("jz");
		int rqIndex = cursor.getColumnIndex("rq");
		int ljjzIndex = cursor.getColumnIndex("ljjz");
		int fqjzIndex = cursor.getColumnIndex("fqjz");
		
		if(cursor.moveToFirst() == false){
			return list;
		}
		
		while(cursor.moveToNext()){
			Jjjz jjjz = new Jjjz();
			jjjz.setDm(cursor.getString(dmIndex));
			jjjz.setRq(cursor.getString(rqIndex));
			jjjz.setJz(cursor.getString(jzIndex));
			jjjz.setLjjz(cursor.getString(ljjzIndex));
			jjjz.setFqjz(cursor.getString(fqjzIndex));
			list.add(jjjz);
		}
		
		return list;
	}
	
	/**
	 * 将新的基金净值信息写入本地缓存数据库。
	 * @param context 显示基金净值历史走势的上下文（Activity），只用它提供的一个getContentResolver()函数，可以向Provider写入数据
	 * @param jjjzList 新的基金净值信息，List<Jjjz>形式。
	 * @return
	 */
	public static int writeJjjzListToDB(Context context, List<Jjjz> jjjzList){
		Uri uri = FundInfoProviderMetaData.JjjzTableMetaTable.CONTENT_URI;
		ContentResolver cr = context.getContentResolver();
		
		int insertCount = 0;
		
		for(Jjjz jjjz: jjjzList){
			ContentValues value = new ContentValues();
			value.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_DM, jjjz.getDm());
			value.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_RQ, jjjz.getRq());
			value.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_JZ, jjjz.getJz());
			value.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_LJJZ, jjjz.getLjjz());
			value.put(FundInfoProviderMetaData.JjjzTableMetaTable.JJJZ_FQJZ, jjjz.getFqjz());
			cr.insert(uri, value);
			insertCount ++;
		}
		
		System.out.println("write finish!");
		return insertCount;
	}
}
