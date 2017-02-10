package com.mystock.model;

import java.util.ArrayList;
import java.util.List;


public interface HistoryCallBack {
	public void OnDateListLoaded(ArrayList<String>dates);
	public void OnPriceListLoaded(ArrayList<Double>prices);
	
}
