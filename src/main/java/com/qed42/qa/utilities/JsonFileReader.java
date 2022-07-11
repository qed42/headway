package com.qed42.qa.utilities;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * JSONFileReader class reads a JSON file
 * 
 * @author QED42
 *
 */
public class JsonFileReader {

	/**
	 * Reads JSON file and returns JSONArray
	 * 
	 * @param fileName
	 * @return JSONObject
	 * @throws Exception
	 */

	public JSONObject readJson(String filename) throws Exception {
		JSONParser jsonParser = new JSONParser();
		FileReader filereader = new FileReader((filename));

		JSONObject obj = (JSONObject) jsonParser.parse(filereader);
		return obj;
	}

	/**
	 * Reads JSON file and returns JSONArray with specific sets of JSON key
	 * 
	 * @param filename
	 * @param JSONkey
	 * @return
	 * @throws Exception
	 */
	public JSONArray readJson(String filename, String JSONkey) throws Exception {
		JSONParser jsonParser = new JSONParser();
		FileReader filereader = new FileReader((filename));

		JSONObject obj = (JSONObject) jsonParser.parse(filereader);
		JSONArray arr = (JSONArray) obj.get(JSONkey);
		return arr;
	}

}
