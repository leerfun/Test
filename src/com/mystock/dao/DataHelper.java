package com.mystock.dao;

import java.util.ArrayList;
import java.util.List;

import com.mystock.database.MysqliteHelper;
import com.mystock.entity.UserInfo;
import com.mystock.utils.GlobalData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataHelper implements IDao{
	// 数据库名称
	private static String DB_NAME = "gid.db";
	// 数据库版本
	private static int DB_VERSION = 1;
	private SQLiteDatabase db;
	private MysqliteHelper dbHelper;

	public DataHelper(Context context) {
		dbHelper = new MysqliteHelper(context, DB_NAME, null, DB_VERSION );
		db = dbHelper.getWritableDatabase();
	}

	public void Close() {
		db.close();
		dbHelper.close();
	}

	/**
	 * 获取userInfo对象数组
	 */
	public List<UserInfo> GetUserList() {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		Cursor cursor = db.query(MysqliteHelper.TB_NAME, null, null , null, null,
				null,  null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null )) {
			UserInfo user = new UserInfo();
			user.setGidcode(cursor.getString(1));
			userList.add(user);
			Log.i("数据库", "数据库对象"+user.toString());
			cursor.moveToNext();
		}
		cursor.close();
		Log.i("w","userlist里面是"+userList.get(0).getGidcode()+userList.get(1).getGidcode());
		return userList;
	}

	//
	//    // 判断users表中的是否包含某个UserID的记录
	//    public Boolean HaveUserInfo(String UserId) {
	//         Boolean b = false;
	//         Cursor cursor = db.query(MysqliteHelper. TB_NAME, null, UserInfo.USERID
	//                  + "=?", new String[]{UserId}, null, null, null );
	//         b = cursor.moveToFirst();
	//         Log. e("HaveUserInfo", b.toString());
	//         cursor.close();
	//          return b;
	//    }
	//
	//    // 更新users表的记录，根据UserId更新用户昵称和用户图标
	//    public int UpdateUserInfo(String userName, Bitmap userIcon, String UserId) {
	//         ContentValues values = new ContentValues();
	//         values.put(UserInfo. USERNAME, userName);
	//          // BLOB类型
	//          final ByteArrayOutputStream os = new ByteArrayOutputStream();
	//          // 将Bitmap压缩成PNG编码，质量为100%存储
	//         userIcon.compress(Bitmap.CompressFormat. PNG, 100, os);
	//          // 构造SQLite的Content对象，这里也可以使用raw
	//         values.put(UserInfo. USERICON, os.toByteArray());
	//          int id = db.update(SqliteHelper. TB_NAME, values, UserInfo.USERID + "=?" , new String[]{UserId});
	//         Log. e("UpdateUserInfo2", id + "");
	//          return id;
	//    }
	//
	//    // 更新users表的记录
	//    public int UpdateUserInfo(UserInfo user) {
	//         ContentValues values = new ContentValues();
	//         values.put(UserInfo. USERID, user.getUserId());
	//         values.put(UserInfo. TOKEN, user.getToken());
	//         values.put(UserInfo. TOKENSECRET, user.getTokenSecret());
	//          int id = db.update(SqliteHelper. TB_NAME, values, UserInfo.USERID + "="
	//                  + user.getUserId(), null);
	//         Log. e("UpdateUserInfo", id + "");
	//          return id;
	//    }
	//
	// 添加users表的记录
	public Long SaveGidInfo(String gid) {
		ContentValues values = new ContentValues();
		values.put(GlobalData.GIDCODE, gid);

		Long uid = db.insert(MysqliteHelper.TB_NAME, GlobalData.GIDCODE, values);

		Log. e("SaveUserInfo", "保存字段"+gid+"到"+uid);
		return uid;
	}

//    
//    // 添加users表的记录
//    public Long SaveUserInfo(UserInfo user, byte[] icon) {
//         ContentValues values = new ContentValues();
//         values.put(UserInfo. USERID, user.getUserId());
//         values.put(UserInfo. USERNAME, user.getUserName());
//         values.put(UserInfo. TOKEN, user.getToken());
//         values.put(UserInfo. TOKENSECRET, user.getTokenSecret());
//          if(icon!= null){
//             values.put(UserInfo. USERICON, icon);
//         }
//         Long uid = db.insert(SqliteHelper. TB_NAME, UserInfo.ID, values);
//         Log. e("SaveUserInfo", uid + "");
//          return uid;
//    }
//
		// 删除users表的记录
    public int DelUserInfo(String gidCode) {
          int id = db.delete(MysqliteHelper. TB_NAME,
                  GlobalData.GIDCODE + "=?", new String[]{gidCode});
         Log. e("DelUserInfo", id + gidCode);
          return id;
    }
    
//    public static UserInfo getUserByName(String userName,List<UserInfo> userList){
//         UserInfo userInfo = null;
//          int size = userList.size();
//          for( int i=0;i<size;i++){
//               if(userName.equals(userList.get(i).getUserName())){
//                  userInfo = userList.get(i);
//                   break;
//             }
//         }
//          return userInfo;
    }    