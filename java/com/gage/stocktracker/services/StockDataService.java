package com.gage.stocktracker.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class StockDataService {
	public static String companyName;
	public static String companyTicker;
	public static String tradeDay;
	public static double openPrice;
	public static double closePrice;
	public static double highPrice;
	public static double lowPrice;
	
	public static void fetchStockData(String stock) throws IOException, InterruptedException {
		
		// fetch JSON data via HTTP request
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request =HttpRequest.newBuilder()
				.uri(URI.create("https://cloud.iexapis.com/stable/stock/" + stock + "/book?token=pk_7d16af48d942497a92a72c3afa0630b5"))
				.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		// parse JSON data
		try {
			JSONObject object = new JSONObject(httpResponse.body());
			companyName = object.getJSONObject("quote").getString("companyName");
			companyTicker = object.getJSONObject("quote").getString("symbol");
			tradeDay = object.getJSONObject("quote").getString("latestTime");
			openPrice = object.getJSONObject("quote").getDouble("open");
			closePrice = object.getJSONObject("quote").getDouble("close");
			highPrice = object.getJSONObject("quote").getDouble("high");
			lowPrice = object.getJSONObject("quote").getDouble("low");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

	
