package com.myproject.testyourself.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FBConnection {

	public static final String FB_APP_ID = "472869032891039";
	public static final String FB_APP_SECRET = "ef6957ee6b7448653ad04c14b4061e25";
	public static final String REDIRECT_URI = "http://localhost:8080/TESTYourself/login";

	static String accessToken = "";

	public String getFBAuthUrl() {
		accessToken = "";
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + FBConnection.FB_APP_ID
					+ "&redirect_uri=" + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&scope=public_profile,email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(fbLoginUrl);
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + FBConnection.FB_APP_ID
					+ "&redirect_uri=" + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8") + "&client_secret="
					+ FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook " + e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: " + accessToken);
			}
		}
		System.out.println(accessToken);
		return accessToken;
	}
}
