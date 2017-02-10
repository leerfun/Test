package com.mystock.entity;

public class Data2 {
	private String symbol;
    private String name;
    private String trade;
    private String pricechange;
    private String changepercent;
    private String buy;
    private String sell;
    private String settlement;
    private String open;
    private String high;
    private String low;
    private String code;
    private String ticktime;
    
	public Data2(String name, String pricechange, String buy, String sell) {
		super();
		this.name = name;
		this.pricechange = pricechange;
		this.buy = buy;
		this.sell = sell;
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public String getPricechange() {
		return pricechange;
	}
	public void setPricechange(String pricechange) {
		this.pricechange = pricechange;
	}
	public String getChangepercent() {
		return changepercent;
	}
	public void setChangepercent(String changepercent) {
		this.changepercent = changepercent;
	}
	public String getBuy() {
		return buy;
	}
	public void setBuy(String buy) {
		this.buy = buy;
	}
	public String getSell() {
		return sell;
	}
	public void setSell(String sell) {
		this.sell = sell;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTicktime() {
		return ticktime;
	}
	public void setTicktime(String ticktime) {
		this.ticktime = ticktime;
	}
	
	@Override
	public String toString() {
		return "Data2 [name=" + name + "]";
	}
    

}
