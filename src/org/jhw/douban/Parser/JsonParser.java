package org.jhw.douban.Parser;

import com.google.gson.Gson;

public class JsonParser {

	public <T> T parseAs(String responseData, Class<T> responseType) {
		Gson gson = new Gson();
		return gson.fromJson(responseData, responseType);
	}
}
