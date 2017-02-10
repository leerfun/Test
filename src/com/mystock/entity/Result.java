package com.mystock.entity;



public class Result {
	private Data data;
	private Dapandata dapandata;
	private Gopicture gopicture;



	public Result() {

	}



	public Result(com.mystock.entity.Data data, com.mystock.entity.Dapandata dapandata,
			com.mystock.entity.Gopicture gopicture) {
		super();
		this.data = data;
		this.dapandata = dapandata;
		this.gopicture = gopicture;
	}



	public Data getData() {
		return data;
	}



	public void setData(Data data) {
		this.data = data;
	}



	public Dapandata getDapandata() {
		return dapandata;
	}



	public void setDapandata(Dapandata dapandata) {
		this.dapandata = dapandata;
	}



	public Gopicture getGopicture() {
		return gopicture;
	}



	public void setGopicture(Gopicture gopicture) {
		this.gopicture = gopicture;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
