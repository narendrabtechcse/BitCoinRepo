package candidatetask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BitCoinRates {

	final static Logger logger = Logger.getLogger(BitCoinRates.class);

	static List<Object> get30DaysBackPrices(String currency, String currentDate, String prev30Days) {

		String bitCoinPricesInGivenDatesCurrency = "https://api.coindesk.com/v1/bpi/historical/close.json?start=" + prev30Days + "&end="
				+ currentDate + "&currency=" + currency.toLowerCase();
		
		System.out.println(" bitCoinPricesInGivenDatesCurrency" + bitCoinPricesInGivenDatesCurrency);

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(bitCoinPricesInGivenDatesCurrency);
		List<Object> list = null;

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

			currObj1.entrySet();

			Iterator it = currObj1.entrySet().iterator();

			list = new ArrayList<Object>();

			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				
				list.add(pair.getValue());
				it.remove(); // avoids a ConcurrentModificationException
			}

			parentBufferReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;

	}

}
