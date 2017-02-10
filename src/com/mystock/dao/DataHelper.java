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
	// ���ݿ�����
	private static String DB_NAME = "gid.db";
	// ���ݿ�汾
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
	 * ��ȡuserInfo��������
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
			Log.i("���ݿ�", "���ݿ����"+user.toString());
			cursor.moveToNext();
		}
		cursor.close();
		Log.i("w","userlist������"+userList.get(0).getGidcode()+userList.get(1).getGidcode());
		return userList;
	}

	//
	//    // �ж�users���е��Ƿ����ĳ��UserID�ļ�¼
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
	//    // ����users��ļ�¼������UserId�����û��ǳƺ��û�ͼ��
	//    public int UpdateUserInfo(String userName, Bitmap userIcon, String UserId) {
	//         ContentValues values = new ContentValues();
	//         values.put(UserInfo. USERNAME, userName);
	//          // BLOB����
	//          final ByteArrayOutputStream os = new ByteArrayOutputStream();
	//          // ��Bitmapѹ����PNG���룬����Ϊ100%�洢
	//         userIcon.compress(Bitmap.CompressFormat. PNG, 100, os);
	//          // ����SQLite��Content��������Ҳ����ʹ��raw
	//         values.put(UserInfo. USERICON, os.toByteArray());
	//          int id = db.update(SqliteHelper. TB_NAME, values, UserInfo.USERID + "=?" , new String[]{UserId});
	//         Log. e("UpdateUserInfo2", id + "");
	//          return id;
	//    }
	//
	//    // ����users��ļ�¼
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
	// ���users��ļ�¼
	public Long SaveGidInfo(String gid) {
		ContentValues values = new ContentValues();
		values.put(GlobalData.GIDCODE, gid);

		Long uid = db.insert(MysqliteHelper.TB_NAME, GlobalData.GIDCODE, values);

		Log. e("SaveUserInfo", "�����ֶ�"+gid+"��"+uid);
		return uid;
	}

//    
//    // ���users��ļ�¼
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
		// ɾ��users��ļ�¼
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