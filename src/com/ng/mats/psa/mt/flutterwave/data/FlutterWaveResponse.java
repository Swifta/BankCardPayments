package com.ng.mats.psa.mt.flutterwave.data;

public class FlutterWaveResponse {

	private String financialtransactionid;
	private String responseCode;
	private String responseDescription;
	private String trxid;
	private boolean completeStatus;

	private String fwotptransactionidentifier;
	private String fwtransactionreference;
	private String fwresponsetoken;
	private String fwstatus;

	public String biller;

	public String getBiller() {
		return biller;
	}

	public void setBiller(String biller) {
		this.biller = biller;
	}

	public String getFinancialtransactionid() {
		return financialtransactionid;
	}

	public void setFinancialtransactionid(String financialtransactionid) {
		this.financialtransactionid = financialtransactionid;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public boolean isCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(boolean completeStatus) {
		this.completeStatus = completeStatus;
	}

	public String getTrxid() {
		return trxid;
	}

	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}

	public String getFwotptransactionidentifier() {
		return fwotptransactionidentifier;
	}

	public void setFwotptransactionidentifier(String fwotptransactionidentifier) {
		this.fwotptransactionidentifier = fwotptransactionidentifier;
	}

	public String getFwtransactionreference() {
		return fwtransactionreference;
	}

	public void setFwtransactionreference(String fwtransactionreference) {
		this.fwtransactionreference = fwtransactionreference;
	}

	public String getFwresponsetoken() {
		return fwresponsetoken;
	}

	public void setFwresponsetoken(String fwresponsetoken) {
		this.fwresponsetoken = fwresponsetoken;
	}

	public String getFwstatus() {
		return fwstatus;
	}

	public void setFwstatus(String fwstatus) {
		this.fwstatus = fwstatus;
	}

}
