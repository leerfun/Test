package com.mystock.utils;


import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mystock.entity.Dapandata;
import com.mystock.entity.Data;

import android.util.Log;

public class JSONParser {

	public static Data parseData(JSONArray ary) throws Exception{
		JSONObject obj1=new JSONObject(ary.getString(0));
		Log.i("obj",obj1.toString());
		Log.i("data",obj1.getString("data"));
		JSONObject obj=new JSONObject(obj1.getString("data"));
		Data data=new Data();
		//data.setBuyFive(obj.getString("buyFive"));
		//data.setBuyFivePri(obj.getString("buyFivePri"));
		data.setBuyFour(obj.getString("buyFour"));
		data.setBuyFourPri(obj.getString("buyFourPri"));
		data.setBuyOne(obj.getString("buyOne"));
		data.setBuyOnePri(obj.getString("buyOnePri"));
		data.setBuyThree(obj.getString("buyThree"));
		data.setBuyThreePri(obj.getString("buyThreePri"));
		data.setBuyTwo(obj.getString("buyTwo"));
		data.setBuyTwoPri(obj.getString("buyTwoPri"));
		data.setCompetitivePri(obj.getString("competitivePri"));
		data.setDate(obj.getString("date"));
		data.setGid(obj.getString("gid"));
		data.setIncrePer(obj.getString("increPer"));
		data.setIncrease(obj.getString("increase"));
		data.setName(obj.getString("name"));
		data.setNowPri(obj.getString("nowPri"));
		data.setReservePri(obj.getString("reservePri"));
		data.setSellFive(obj.getString("sellFive"));
		data.setBuyFivePri(obj.getString("sellFivePri"));
		data.setSellFour(obj.getString("sellFour"));
		data.setSellFourPri(obj.getString("sellFourPri"));
		data.setSellOne(obj.getString("sellOne"));
		data.setSellOnePri(obj.getString("sellOnePri"));
		data.setSellThree(obj.getString("sellThree"));
		data.setSellThreePri(obj.getString("sellThreePri"));
		data.setSellTwo(obj.getString("sellTwo"));
		data.setSellTwoPri(obj.getString("sellTwoPri"));
		data.setTime(obj.getString("time"));
		data.setTodayMax(obj.getString("todayMax"));
		data.setTodayMin(obj.getString("todayMin"));
		data.setTodayStartPri(obj.getString("todayStartPri"));
		data.setTraAmount(obj.getString("traAmount"));
		data.setTraNumber(obj.getString("traNumber"));
		data.setYestodEndPri(obj.getString("yestodEndPri"));
		

		return data;
	}

	public static String[] parsePicUrl(JSONArray ary)throws Exception{
		JSONObject obj=new JSONObject(ary.getString(0));
		JSONObject obj2=new JSONObject(obj.getString("gopicture"));
		Log.i("Json", "obj"+obj2.toString());
		String min=obj2.getString("minurl");
		String day=obj2.getString("dayurl");
		String week=obj2.getString("weekurl");
		String mon=obj2.getString("monthurl");
		String[]urls={min,day,week,mon};
		Log.i("parse", "PicUrl"+urls[2]);
		return urls;
		
		
	}
}
