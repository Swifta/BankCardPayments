package com.ng.mats.psa.mt.flutterwave.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.ng.mats.psa.mt.flutterwave.data.FlutterWaveRequest;
import com.ng.mats.psa.mt.flutterwave.data.FlutterWaveResponse;

public class RestClient {

	private static final Log logger = LogFactory.getLog(RestClient.class);

	public static void main(String[] args) throws JSONException,
			InvalidKeyException, NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException,
			NullPointerException {

		logger.info("Setting FlutterWave Request");

		FlutterWaveRequest flutterWaveRequest = new FlutterWaveRequest();

		flutterWaveRequest.setAmount("500");
		flutterWaveRequest.setCurrency("NGN");
		// flutterWaveRequest.setCardno("5840 4061 8755 3286");
		flutterWaveRequest.setCardno("5377 2836 4507 7450");
		flutterWaveRequest.setCvv("373");
		flutterWaveRequest.setExpirymonth("06");
		flutterWaveRequest.setExpiryyear("19");
		flutterWaveRequest.setNarration("Payment for goods x and y");
		flutterWaveRequest.setPin("1111");
		flutterWaveRequest.setCustid("pkigozi@swifta.com");

		FlutterWaveResponse response = null;
		response = FlutterWaveClient.oneTimeCardPayment(flutterWaveRequest);

		if (response.getFwresponsetoken() == null
				|| response.getFwresponsetoken().equals("null")) {
			System.err.println("No recurrent token");

		}
		System.out.println("Token: " + response.getFwresponsetoken());

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setAmount("100");
		flutterWaveRequest.setCurrency("NGN");
		flutterWaveRequest.setNarration("Payment of service L");
		flutterWaveRequest.setCustid("pkigozi@swifta.com");
		flutterWaveRequest.setChargetoken(response.getFwresponsetoken());

		response = FlutterWaveClient.recurrentCardPayment(flutterWaveRequest);

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setFwotp("1232");
		flutterWaveRequest.setFwotptransactionidentifier("R/TST/FLW00290368");

		response = FlutterWaveClient.validateCardPayment(flutterWaveRequest);

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setFwAccountNumber("5455 8444 3730 6780");
		flutterWaveRequest.setAmount("1000");
		flutterWaveRequest.setFwCreditAccountNumber("5377 2836 4507 7450");
		flutterWaveRequest.setFwTrxref("433422");
		flutterWaveRequest.setNarration("Payment of x in advancements");

		response = FlutterWaveClient.fundTransfer(flutterWaveRequest);

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setAmount("100");
		flutterWaveRequest.setFwTrxref("xjd33iiiuek");
		flutterWaveRequest.setFwotp("1231");
		flutterWaveRequest.setFwAccountToken("8uiudiuuiggg");
		response = FlutterWaveClient.validateFundTransfer(flutterWaveRequest);

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setFwAccountNumber("5455 8444 3730 6780");

		response = FlutterWaveClient.recurrentFundTransfer(flutterWaveRequest);

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setFwAccountNumber("5455 8444 3730 6780");
		flutterWaveRequest.setFwotp("1212");
		flutterWaveRequest.setFwreference("xxxjkkjkei");
		flutterWaveRequest.setFwbillingamount("100");
		flutterWaveRequest.setNarration("Validate payment x of y");

		response = FlutterWaveClient
				.validateRecurrentFundTransfer(flutterWaveRequest);

		flutterWaveRequest = new FlutterWaveRequest();
		flutterWaveRequest.setFwAccountToken("jsjkkskj");
		flutterWaveRequest.setCurrency("NGN");
		flutterWaveRequest.setFwbillingamount("100");
		flutterWaveRequest.setNarration("Validate payment x of y");

		response = FlutterWaveClient.recurrentCharge(flutterWaveRequest);

	}

	public static String connectToFlutterWave(String url,
			JSONObject jsonObject, FlutterWaveRequest flutterWaveRequest)
			throws Exception {

		String response;

		String epr = flutterWaveRequest.getApiBaseUrl().concat(url);
		System.out.println("Using EPR: " + epr);

		response = sendPostRequest(epr, jsonObject.toString());

		System.out.println("Response: " + response);

		return response;

	}

	public static String sendPostRequest(String requestUrl, String payload) {

		StringBuffer jsonString = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			writer.write(payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return jsonString.toString();
	}

}
