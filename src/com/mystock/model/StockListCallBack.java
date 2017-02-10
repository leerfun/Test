package com.mystock.model;

import java.util.List;

import com.mystock.entity.Data;

public interface StockListCallBack {
	public void OnStockListLoaded(List<Data>Stocks);
}
