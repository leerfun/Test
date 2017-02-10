package com.mystock.database;

import java.util.List;

import com.mystock.entity.UserInfo;
import com.mystock.utils.GlobalData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MysqliteHelper extends SQLiteOpenHelper{
	//用来保存gidcode的表名
	public static final String TB_NAME= "users";
	public MysqliteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	//创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("w", "Mysqhelper执行");
		Cursor cursor;  
		try {  

			String sql = "select count (*) from sqlite_master where type ='table' and name ='users'" ;  
			cursor = db.rawQuery(sql, null); 
			Log.i("w", "db.rawQery="+cursor);
			if (cursor.moveToNext()) {  
				int count = cursor.getInt(0);  
				if (count > 0) {  
					Log.i("数据库", "数据库中表存在");
				}else{
					//			String database_create="create table  IF NOT EXISTS users( _id integer primary key autoincrement, " +   
					//	            "name text not null);";
					db.execSQL("CREATE TABLE IF NOT EXISTS "+
							TB_NAME+" ("+
							GlobalData.ID+" INTEGER PRIMARY KEY, "+
							GlobalData.GIDCODE+ " NOT NULL"+
							");"
							);
					Log. e("Database" ,"onCreate" );
					//					String sql = "select count(gidcode) from users";
					//					cursor = db.rawQuery(sql, null);
					//					Log.i("cursor", "cursor="+cursor);
					//cursor.moveToNext();
					//int count = cursor.getInt(0);
					//			if(!cursor.moveToFirst()){
					ContentValues values = new ContentValues();
					String[] gids={"&type=0","&type=1"};
					Log.i("w", gids.toString());
					for (int i = 0; i < gids.length; i++) {
						values.put(GlobalData.GIDCODE,gids[i]);			      
						db.insert(MysqliteHelper.TB_NAME, GlobalData.ID, values);
						Log.i("Database" ,"gids"+gids[i]+" was added" );
					}

					//}
					//}  


				}
			}
		} catch (Exception e) {  
			Log.e("数据库", "数据库表oncreate出错");
		}  


	}
	//更新表
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL( "DROP TABLE IF EXISTS " + TB_NAME );
		onCreate(db);
		Log. e("Database" ,"onUpgrade" );
	}
	//更新列
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn){
		try{
			db.execSQL( "ALTER TABLE " +
					TB_NAME + " CHANGE " +
					oldColumn + " "+ newColumn +
					" " + typeColumn
					);
		} catch(Exception e){
			e.printStackTrace();
		}
	}



}


