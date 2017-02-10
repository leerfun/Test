package com.mystock.entity;

public class Gopicture {
	  private String minurl;
	    private String dayurl;
	    private String weekurl;
	    private String monthurl;
	    
	    public Gopicture() {
	    	
	    }
	    
	    
	    
		public Gopicture(String minurl, String dayurl, String weekurl, String monthurl) {
			super();
			this.minurl = minurl;
			this.dayurl = dayurl;
			this.weekurl = weekurl;
			this.monthurl = monthurl;
		}



		public String getMinurl() {
			return minurl;
		}
		public void setMinurl(String minurl) {
			this.minurl = minurl;
		}
		public String getDayurl() {
			return dayurl;
		}
		public void setDayurl(String dayurl) {
			this.dayurl = dayurl;
		}
		public String getWeekurl() {
			return weekurl;
		}
		public void setWeekurl(String weekurl) {
			this.weekurl = weekurl;
		}
		public String getMonthurl() {
			return monthurl;
		}
		public void setMonthurl(String monthurl) {
			this.monthurl = monthurl;
		}
	    
}
