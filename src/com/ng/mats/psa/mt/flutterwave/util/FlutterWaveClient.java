package com.ng.mats.psa.mt.flutterwave.util;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.ng.mats.psa.mt.flutterwave.data.FlutterWaveRequest;
import com.ng.mats.psa.mt.flutterwave.data.FlutterWaveResponse;

public class FlutterWaveClient {

	public static FlutterWaveResponse oneTimeCardPayment(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "card/mvva/pay";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("amount", flutterWaveRequest.getAmount());
			jsonObject.put("currency", flutterWaveRequest.getCurrency());
			jsonObject.put("cardno", flutterWaveRequest.getCardno());
			jsonObject.put("cvv", flutterWaveRequest.getCvv());
			jsonObject.put("expirymonth", flutterWaveRequest.getExpirymonth());
			jsonObject.put("expiryyear", flutterWaveRequest.getExpiryyear());
			jsonObject.put("pin", flutterWaveRequest.getPin());
			jsonObject.put("narration", flutterWaveRequest.getNarration());
			jsonObject.put("custid", flutterWaveRequest.getCustid());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);
			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");
			flutterWaveResponse.setResponseCode(data.getString("responsecode"));

			flutterWaveResponse.setResponseDescription(data
					.getString("responsemessage"));

			if (flutterWaveResponse.getResponseCode().equals("00")
					|| flutterWaveResponse.getResponseCode().equals("02")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setFwresponsetoken(data
					.getString("responsetoken"));
			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionreference"));
			flutterWaveResponse.setFwotptransactionidentifier(data
					.getString("otptransactionidentifier"));
			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse validateCardPayment(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "card/mvva/pay/validate";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("otp", flutterWaveRequest.getFwotp());
			jsonObject.put("otptransactionidentifier",
					flutterWaveRequest.getFwotptransactionidentifier());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");
			flutterWaveResponse.setResponseCode(data.getString("responsecode"));

			flutterWaveResponse.setResponseDescription(data
					.getString("responsemessage"));

			if (flutterWaveResponse.getResponseCode().equals("00")
					|| flutterWaveResponse.getResponseCode().equals("200")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setFwresponsetoken(data
					.getString("responsetoken"));
			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionreference"));
			flutterWaveResponse.setFwotptransactionidentifier(data
					.getString("otptransactionidentifier"));
			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse recurrentCardPayment(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "card/mvva/pay";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("amount", flutterWaveRequest.getAmount());
			jsonObject.put("currency", flutterWaveRequest.getCurrency());
			jsonObject.put("chargetoken", flutterWaveRequest.getChargetoken());

			jsonObject.put("narration", flutterWaveRequest.getNarration());
			jsonObject.put("custid", flutterWaveRequest.getCustid());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");
			flutterWaveResponse.setResponseCode(data.getString("responsecode"));

			flutterWaveResponse.setResponseDescription(data
					.getString("responsemessage"));

			if (flutterWaveResponse.getResponseCode().equals("00")
					|| flutterWaveResponse.getResponseCode().equals("200")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setFwresponsetoken(data
					.getString("responsetoken"));
			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionreference"));
			flutterWaveResponse.setFwotptransactionidentifier(data
					.getString("otptransactionidentifier"));
			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse fundTransfer(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "accessbank/pay";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("accountNumber",
					flutterWaveRequest.getFwAccountNumber());
			jsonObject.put("amountToPay", flutterWaveRequest.getAmount());
			jsonObject.put("creditAccountNumber",
					flutterWaveRequest.getfwCreditAccountNumber());
			jsonObject.put("trxref", flutterWaveRequest.getFwTrxref());
			jsonObject.put("narration", flutterWaveRequest.getNarration());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");

			flutterWaveResponse.setResponseDescription(data
					.getString("responseMessage"));
			flutterWaveResponse.setCompleteStatus(true);

			flutterWaveResponse.setCompleteStatus(true);

			flutterWaveResponse.setFwresponsetoken(data
					.getString("transactionToken"));
			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionReference"));

			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse validateFundTransfer(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "accessbank/pay/validate";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());

			jsonObject.put("amountToPay", flutterWaveRequest.getAmount());

			jsonObject.put("trxref", flutterWaveRequest.getFwTrxref());
			jsonObject.put("otp", flutterWaveRequest.getFwotp());
			jsonObject.put("accountToken",
					flutterWaveRequest.getFwAccountToken());
			jsonObject.put("narration", flutterWaveRequest.getNarration());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");

			flutterWaveResponse.setResponseCode(data.getString("responseCode"));

			if (flutterWaveResponse.getResponseCode().equals("00")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setResponseDescription(data
					.getString("responseMessage"));

			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionreference"));

			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse recurrentFundTransfer(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "recurrent/account";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("accountNumber",
					flutterWaveRequest.getFwAccountNumber());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");
			flutterWaveResponse.setResponseCode(data.getString("responseCode"));

			flutterWaveResponse.setResponseDescription(data
					.getString("responseMessage"));

			if (flutterWaveResponse.getResponseCode().equals("00")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionReference"));

			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse validateRecurrentFundTransfer(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "recurrent/account/validate";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("accountNumber",
					flutterWaveRequest.getFwAccountNumber());
			jsonObject.put("otp", flutterWaveRequest.getFwotp());
			jsonObject.put("reference", flutterWaveRequest.getFwreference());
			jsonObject.put("billingamount",
					flutterWaveRequest.getFwbillingamount());
			jsonObject.put("debitnarration", flutterWaveRequest.getNarration());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));
				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");
			flutterWaveResponse.setResponseCode(data.getString("responseCode"));

			flutterWaveResponse.setResponseDescription(data
					.getString("responseMessage"));

			if (flutterWaveResponse.getResponseCode().equals("00")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionReference"));

			flutterWaveResponse.setFwresponsetoken(data
					.getString("accountToken"));

			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

	public static FlutterWaveResponse recurrentCharge(
			FlutterWaveRequest flutterWaveRequest) throws JSONException {
		String endPointUrl = "recurrent/account/charge";
		FlutterWaveResponse flutterWaveResponse = new FlutterWaveResponse();

		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("merchantid", flutterWaveRequest.getMerchantid());
			jsonObject.put("accountToken",
					flutterWaveRequest.getFwAccountToken());

			jsonObject.put("currency", flutterWaveRequest.getCurrency());
			jsonObject.put("billingamount",
					flutterWaveRequest.getFwbillingamount());
			jsonObject.put("debitnarration", flutterWaveRequest.getNarration());

			System.out.println("JSON: " + jsonObject.toString());

			String output = RestClient.connectToFlutterWave(endPointUrl,
					jsonObject, flutterWaveRequest);

			System.out.println("Output: " + output);

			if (output == null) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse
						.setResponseDescription("No response from backend service received");
				flutterWaveResponse.setCompleteStatus(false);

				return flutterWaveResponse;

			}

			JSONObject obj = new JSONObject(output);

			if (!obj.getString("status").equals("success")) {

				flutterWaveResponse.setResponseCode("100");
				flutterWaveResponse.setResponseDescription(obj
						.getString("data"));

				flutterWaveResponse.setCompleteStatus(false);

				Object data = obj.get("data");
				if (data instanceof JSONObject) {
					JSONObject d = (JSONObject) data;
					flutterWaveResponse.setResponseDescription(d
							.getString("responseMessage"));
				} else {
					flutterWaveResponse.setResponseDescription(data.toString());
				}

				return flutterWaveResponse;
			}

			JSONObject data = obj.getJSONObject("data");
			flutterWaveResponse.setResponseCode(data.getString("responseCode"));

			flutterWaveResponse.setResponseDescription(data
					.getString("responseMessage"));

			if (flutterWaveResponse.getResponseCode().equals("00")) {
				flutterWaveResponse.setCompleteStatus(true);
			} else {
				flutterWaveResponse.setCompleteStatus(false);
			}

			flutterWaveResponse.setFwtransactionreference(data
					.getString("transactionreference"));
			flutterWaveResponse.setFwstatus(obj.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();
			flutterWaveResponse.setResponseCode("100");
			flutterWaveResponse.setResponseDescription("FAILED");
			flutterWaveResponse.setCompleteStatus(false);
		}
		return flutterWaveResponse;

	}

}
