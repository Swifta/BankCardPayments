package com.ng.mats.psa.mt.flutterwave.data;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ng.mats.psa.mt.flutterwave.util.TripleDES;

public class FlutterWaveRequest {

	private String apiKey;
	private String apiBaseUrl;
	private String merchantid;
	private String amount;
	private String currency;
	private String cardno;
	private String cvv;
	private String expirymonth;
	private String expiryyear;
	private String pin;
	private String narration;
	private String custid;
	private String chargetoken;

	private String fwAccountNumber;
	private String fwCreditAccountNumber;
	private String fwTrxref;

	private String fwotp;
	private String fwotptransactionidentifier;

	private String fwAccountToken;

	private String fwreference;
	private String fwbillingamount;
	private String fwdebitnarration;

	private TripleDES digest;

	private static final Logger logger = Logger
			.getLogger(FlutterWavePropertyValues.class.getName());

	public FlutterWaveRequest() {
		FlutterWavePropertyValues fPV = new FlutterWavePropertyValues();
		setPropertyValues(fPV.getPropertyValues());
		setDigest(new TripleDES(getApiKey()));

	}

	private void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	private void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public String getMerchantid() {
		return merchantid;
	}

	private void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.amount = digest.harden(amount);
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.currency = digest.harden(currency);
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.cardno = digest.harden(cardno);
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.cvv = digest.harden(cvv);
	}

	public String getExpirymonth() {
		return expirymonth;
	}

	public void setExpirymonth(String expirymonth) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.expirymonth = digest.harden(expirymonth);
	}

	public String getExpiryyear() {
		return expiryyear;
	}

	public void setExpiryyear(String expiryyear) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.expiryyear = digest.harden(expiryyear);
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.pin = digest.harden(pin);
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.narration = digest.harden(narration);
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.custid = digest.harden(custid);
	}

	public TripleDES getDigest() {
		return digest;
	}

	private void setDigest(TripleDES digest) {
		this.digest = digest;
	}

	private void setPropertyValues(Properties prop) {
		if (prop == null)
			return;

		String parameterType = prop.getProperty("settings-type");
		logger.info("Platform Type: " + parameterType);

		setApiKey(prop.getProperty("api_key_" + parameterType));
		setMerchantid(prop.getProperty("api_merchant_key_" + parameterType));
		setApiBaseUrl(prop.getProperty("base_url_" + parameterType));
		System.out.println("Base url: " + getApiBaseUrl());
	}

	public String getChargetoken() {
		return chargetoken;
	}

	public void setChargetoken(String chargetoken) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.chargetoken = digest.harden(chargetoken);
	}

	public String getFwotp() {
		return fwotp;
	}

	public void setFwotp(String fwotp) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.fwotp = digest.harden(fwotp);
	}

	public String getFwotptransactionidentifier() {
		return fwotptransactionidentifier;
	}

	public void setFwotptransactionidentifier(String fwotptransactionidentifier)
			throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {
		this.fwotptransactionidentifier = digest
				.harden(fwotptransactionidentifier);
	}

	public void setFwAccountNumber(String fwAccountNumber)
			throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {
		this.fwAccountNumber = digest.harden(fwAccountNumber);
	}

	public String getfwCreditAccountNumber() {
		return fwCreditAccountNumber;
	}

	public void setFwCreditAccountNumber(String fwCreditAccountNumber)
			throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {
		this.fwCreditAccountNumber = digest.harden(fwCreditAccountNumber);
	}

	public String getFwTrxref() {
		return fwTrxref;
	}

	public void setFwTrxref(String fwTrxref) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.fwTrxref = digest.harden(fwTrxref);
	}

	public String getFwAccountToken() {
		return fwAccountToken;
	}

	public void setFwAccountToken(String fwAccountToken)
			throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {
		this.fwAccountToken = digest.harden(fwAccountToken);
	}

	public String getFwAccountNumber() {
		return fwAccountNumber;
	}

	public String getFwCreditAccountNumber() {
		return fwCreditAccountNumber;
	}

	public String getFwreference() {
		return fwreference;
	}

	public void setFwreference(String fwreference) throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NullPointerException {
		this.fwreference = digest.harden(fwreference);
	}

	public String getFwbillingamount() {
		return fwbillingamount;
	}

	public void setFwbillingamount(String fwbillingamount)
			throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {
		this.fwbillingamount = digest.harden(fwbillingamount);
	}

	public String getFwdebitnarration() {
		return fwdebitnarration;
	}

	public void setFwdebitnarration(String fwdebitnarration)
			throws InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {
		this.fwdebitnarration = digest.harden(fwdebitnarration);
	}

}
