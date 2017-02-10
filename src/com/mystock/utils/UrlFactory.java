package com.mystock.utils;

public class UrlFactory {

	public static String getStockInfoUrl(String stockGid) {
		String url="http://web.juhe.cn:8080/finance/stock/hs?gid=sh"+stockGid+"&key=2f28ecace171de728951e527efae2e19";
		return url;
	}

}
