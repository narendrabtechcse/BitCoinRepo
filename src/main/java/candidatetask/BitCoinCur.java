package candidatetask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BitCoinCur {

	final static Logger logger = Logger.getLogger(BitCoinCur.class);

	static String getCurrentPriceOfBC(String currency) {

		String currBitCoinPriceUrl = "https://api.coindesk.com/v1/bpi/currentprice/"+currency.toLowerCase()+".json";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(currBitCoinPriceUrl);
		JSONObject rateFloatObject = null;

		HttpResponse response = null;
		try {
			response = client.execute(request);

			BufferedReader parentBufferReader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			JSONParser parentParser = new JSONParser();

			Object parentObject = parentParser.parse(parentBufferReader);

			JSONArray array = new JSONArray();
			array.add(parentObject);

			JSONObject currObj = (JSONObject) array.get(0);

			JSONObject currObj1 = (JSONObject) currObj.get("bpi");

			rateFloatObject = (JSONObject) currObj1.get(currency);

			parentBufferReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rateFloatObject.get("rate_float").toString();

	}

}
