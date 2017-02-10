package com.mystock.entity;

import java.io.Serializable;

public class Data implements Serializable{
	
	    private String buyFive;
	    private String buyFivePri;
	    private String buyFour;
	    private String buyFourPri;
	    private String buyOne;
	    private String buyOnePri;
	    private String buyThree;
	    private String buyThreePri;
	    private String buyTwo;
	    private String buyTwoPri;
	    private String competitivePri;
	    private String date;
	    private String gid;
	    private String increPer;
	    private String increase;
	    private String name;
	    private String nowPri;
	    private String reservePri;
	    private String sellFive;
	    private String sellFivePri;
	    private String sellFour;
	    private String sellFourPri;
	    private String sellOne;
	    private String sellOnePri;
	    private String sellThree;
	    private String sellThreePri;
	    private String sellTwo;
	    private String sellTwoPri;
	    private String time;
	    private String todayMax;
	    private String todayMin;
	    private String todayStartPri;
	    private String traAmount;
	    private String traNumber;
	    private String yestodEndPri;
	  
	    
	    
		public Data() {
			
		}
		
		
		public Data(String increPer, String increase, String name, String nowPri) {
			super();
			this.increPer = increPer;
			this.increase = increase;
			this.name = name;
			this.nowPri = nowPri;
		}


		public Data(String buyFive, String buyFivePri, String buyFour, String buyFourPri, String buyOneprivate,
				String buyOnePri, String buyThree, String buyThreePri, String buyTwo, String buyTwoPri,
				String competitivePri, String date, String gid, String increPer, String increase, String name,
				String nowPri, String reservePri, String sellFive, String sellFivePri, String sellFour,
				String sellFourPri, String sellOne, String sellOnePri, String sellThree, String sellThreePri,
				String sellTwo, String sellTwoPri, String time, String todayMax, String todayMin, String todayStartPri,
				String traAmount, String traNumber, String yestodEndPri) {
			super();
			this.buyFive = buyFive;
			this.buyFivePri = buyFivePri;
			this.buyFour = buyFour;
			this.buyFourPri = buyFourPri;
			this.buyOne = buyOne;
			this.buyOnePri = buyOnePri;
			this.buyThree = buyThree;
			this.buyThreePri = buyThreePri;
			this.buyTwo = buyTwo;
			this.buyTwoPri = buyTwoPri;
			this.competitivePri = competitivePri;
			this.date = date;
			this.gid = gid;
			this.increPer = increPer;
			this.increase = increase;
			this.name = name;
			this.nowPri = nowPri;
			this.reservePri = reservePri;
			this.sellFive = sellFive;
			this.sellFivePri = sellFivePri;
			this.sellFour = sellFour;
			this.sellFourPri = sellFourPri;
			this.sellOne = sellOne;
			this.sellOnePri = sellOnePri;
			this.sellThree = sellThree;
			this.sellThreePri = sellThreePri;
			this.sellTwo = sellTwo;
			this.sellTwoPri = sellTwoPri;
			this.time = time;
			this.todayMax = todayMax;
			this.todayMin = todayMin;
			this.todayStartPri = todayStartPri;
			this.traAmount = traAmount;
			this.traNumber = traNumber;
			this.yestodEndPri = yestodEndPri;
		}



		public String getBuyFive() {
			return buyFive;
		}
		public void setBuyFive(String buyFive) {
			this.buyFive = buyFive;
		}
		public String getBuyFivePri() {
			return buyFivePri;
		}
		public void setBuyFivePri(String buyFivePri) {
			this.buyFivePri = buyFivePri;
		}
		public String getBuyFour() {
			return buyFour;
		}
		public void setBuyFour(String buyFour) {
			this.buyFour = buyFour;
		}
		public String getBuyFourPri() {
			return buyFourPri;
		}
		public void setBuyFourPri(String buyFourPri) {
			this.buyFourPri = buyFourPri;
		}
		public String getBuyOne() {
			return buyOne;
		}
		public void setBuyOne(String buyOne) {
			this.buyOne = buyOne;
		}
		public String getBuyOnePri() {
			return buyOnePri;
		}
		public void setBuyOnePri(String buyOne) {
			this.buyOnePri = buyOne;
		}
		public String getBuyThree() {
			return buyThree;
		}
		public void setBuyThree(String buyThree) {
			this.buyThree = buyThree;
		}
		public String getBuyThreePri() {
			return buyThreePri;
		}
		public void setBuyThreePri(String buyThreePri) {
			this.buyThreePri = buyThreePri;
		}
		public String getBuyTwo() {
			return buyTwo;
		}
		public void setBuyTwo(String buyTwo) {
			this.buyTwo = buyTwo;
		}
		public String getBuyTwoPri() {
			return buyTwoPri;
		}
		public void setBuyTwoPri(String buyTwoPri) {
			this.buyTwoPri = buyTwoPri;
		}
		public String getCompetitivePri() {
			return competitivePri;
		}
		public void setCompetitivePri(String competitivePri) {
			this.competitivePri = competitivePri;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getGid() {
			return gid;
		}
		public void setGid(String gid) {
			this.gid = gid;
		}
		public String getIncrePer() {
			return increPer;
		}
		public void setIncrePer(String increPer) {
			this.increPer = increPer;
		}
		public String getIncrease() {
			return increase;
		}
		public void setIncrease(String increase) {
			this.increase = increase;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNowPri() {
			return nowPri;
		}
		public void setNowPri(String nowPri) {
			this.nowPri = nowPri;
		}
		public String getReservePri() {
			return reservePri;
		}
		public void setReservePri(String reservePri) {
			this.reservePri = reservePri;
		}
		public String getSellFive() {
			return sellFive;
		}
		public void setSellFive(String sellFive) {
			this.sellFive = sellFive;
		}
		public String getSellFivePri() {
			return sellFivePri;
		}
		public void setSellFivePri(String sellFivePri) {
			this.sellFivePri = sellFivePri;
		}
		public String getSellFour() {
			return sellFour;
		}
		public void setSellFour(String sellFour) {
			this.sellFour = sellFour;
		}
		public String getSellFourPri() {
			return sellFourPri;
		}
		public void setSellFourPri(String sellFourPri) {
			this.sellFourPri = sellFourPri;
		}
		public String getSellOne() {
			return sellOne;
		}
		public void setSellOne(String sellOne) {
			this.sellOne = sellOne;
		}
		public String getSellOnePri() {
			return sellOnePri;
		}
		public void setSellOnePri(String sellOnePri) {
			this.sellOnePri = sellOnePri;
		}
		public String getSellThree() {
			return sellThree;
		}
		public void setSellThree(String sellThree) {
			this.sellThree = sellThree;
		}
		public String getSellThreePri() {
			return sellThreePri;
		}
		public void setSellThreePri(String sellThreePri) {
			this.sellThreePri = sellThreePri;
		}
		public String getSellTwo() {
			return sellTwo;
		}
		public void setSellTwo(String sellTwo) {
			this.sellTwo = sellTwo;
		}
		public String getSellTwoPri() {
			return sellTwoPri;
		}
		public void setSellTwoPri(String sellTwoPri) {
			this.sellTwoPri = sellTwoPri;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getTodayMax() {
			return todayMax;
		}
		public void setTodayMax(String todayMax) {
			this.todayMax = todayMax;
		}
		public String getTodayMin() {
			return todayMin;
		}
		public void setTodayMin(String todayMin) {
			this.todayMin = todayMin;
		}
		public String getTodayStartPri() {
			return todayStartPri;
		}
		public void setTodayStartPri(String todayStartPri) {
			this.todayStartPri = todayStartPri;
		}
		public String getTraAmount() {
			return traAmount;
		}
		public void setTraAmount(String traAmount) {
			this.traAmount = traAmount;
		}
		public String getTraNumber() {
			return traNumber;
		}
		public void setTraNumber(String traNumber) {
			this.traNumber = traNumber;
		}
		public String getYestodEndPri() {
			return yestodEndPri;
		}
		public void setYestodEndPri(String yestodEndPri) {
			this.yestodEndPri = yestodEndPri;
		}



		@Override
		public String toString() {
			return "Stock [buyFive=" + buyFive + ", buyFivePri=" + buyFivePri + ", buyFour=" + buyFour + ", buyFourPri="
					+ buyFourPri + ", buyOneprivate=" + buyOne + ", buyOnePri=" + buyOnePri + ", buyThree="
					+ buyThree + ", buyThreePri=" + buyThreePri + ", buyTwo=" + buyTwo + ", buyTwoPri=" + buyTwoPri
					+ ", competitivePri=" + competitivePri + ", date=" + date + ", gid=" + gid + ", increPer="
					+ increPer + ", increase=" + increase + ", name=" + name + ", nowPri=" + nowPri + ", reservePri="
					+ reservePri + ", sellFive=" + sellFive + ", sellFivePri=" + sellFivePri + ", sellFour=" + sellFour
					+ ", sellFourPri=" + sellFourPri + ", sellOne=" + sellOne + ", sellOnePri=" + sellOnePri
					+ ", sellThree=" + sellThree + ", sellThreePri=" + sellThreePri + ", sellTwo=" + sellTwo
					+ ", sellTwoPri=" + sellTwoPri + ", time=" + time + ", todayMax=" + todayMax + ", todayMin="
					+ todayMin + ", todayStartPri=" + todayStartPri + ", traAmount=" + traAmount + ", traNumber="
					+ traNumber + ", yestodEndPri=" + yestodEndPri + "]";
		}
		
	




}
