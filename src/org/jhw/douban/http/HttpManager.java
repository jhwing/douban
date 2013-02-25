package org.jhw.douban.http;

import java.io.IOException;

import org.jhw.douban.constants.Configs;
import org.jhw.douban.http.HttpHelper.HttpException;

import com.google.gson.Gson;

public class HttpManager {
	
	
	private static final int DEF_TIMEOUT_MILLIS = 1000;
	
	private static final HttpManager INSTANCE = new HttpManager();
	private HttpHelper mHttpHelper;
	
	private HttpManager() {
		mHttpHelper = createHttpHelper();
		setDefCommonHTTPOptions();
	}

	public static HttpManager getInstance() {
		return INSTANCE;
	}
	
    private HttpHelper createHttpHelper() {
        return new JavaNetHttpHelper(
                new JavaNetHttpHelper.PassThroughRewriter(),
                Configs.USER_AGENT);
    }
	private void setDefCommonHTTPOptions() {
		mHttpHelper.setConnectTimeout(DEF_TIMEOUT_MILLIS);
	}

	public <T> T getResponseInJson(String url, Class<T> responseType) throws HttpException, IOException {
		String responseData = mHttpHelper.get(url, null);
		return parseAs(responseData,responseType);
	}
	
	private <T> T parseAs(String responseData, Class<T> responseType) {
		Gson gson = new Gson();
		return gson.fromJson(responseData, responseType);
	}
	
	
}
