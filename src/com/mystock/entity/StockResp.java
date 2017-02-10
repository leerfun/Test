package com.mystock.entity;

import java.util.List;

public class StockResp {
	private String resultcode;
	private String reason;
	private List<Result>result;
	private int error_code;
	
	
	public StockResp(String resultcode, String reason, List<Result> result, int error_code) {
		
		this.resultcode = resultcode;
		this.reason = reason;
		this.result = result;
		this.error_code = error_code;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<Result> getResult() {
		return result;
	}
	public void setResult(List<Result> result) {
		this.result = result;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	@Override
	public String toString() {
		
		return super.toString();
	}
}
