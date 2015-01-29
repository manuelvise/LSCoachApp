package sde.lifecoach.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.lifecoachapp.R;

import android.graphics.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class AuthenticationActivity extends Activity implements OnClickListener {

	private Button button;
	private WebView webView;

	private final static String CLIENT_ID = "834dce9fc49c42438f8c3d748983127a";
	private final static String CLIENT_SECRET = "8475dd2494004c30840b0bc778cee399";
	private final static String CALLBACK_URL = "https://www.google.it";
	private String authCode;
	SharedPreferences pref;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Force to login on every launch.
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		String token = pref.getString("token", "");

		if (token != "") {
			Intent i = new Intent(getApplicationContext(),
					InfoActivity.class);
			startActivity(i);
		}


		button = (Button) findViewById(R.id.button);
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	public void onClick(View v) {
		button.setVisibility(View.GONE);
		webView.setVisibility(View.VISIBLE);

		getAuthorizationCode();
	}

	private void getAuthorizationCode() {
		String authorizationUrl = "https://runkeeper.com/apps/authorize?response_type=code&client_id=%s&redirect_uri=%s";
		authorizationUrl = String.format(authorizationUrl, CLIENT_ID,
				CALLBACK_URL);

		try{
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(authorizationUrl);
		webView.setWebViewClient(new WebViewClient() {
			boolean authComplete = false;
			Intent resultIntent = new Intent();

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			String authCode;

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				String urlT = webView.getUrl();
				System.out.println("Url: "+urlT);
				if (url.contains("?code=") && authComplete != true) {
					Uri uri = Uri.parse(url);
					authCode = uri.getQueryParameter("code");
					Log.i("", "CODE : " + authCode);
					authComplete = true;
					resultIntent.putExtra("code", authCode);
					AuthenticationActivity.this.setResult(Activity.RESULT_OK,
							resultIntent);
					setResult(Activity.RESULT_CANCELED, resultIntent);
					SharedPreferences.Editor edit = pref.edit();
					edit.putString("Code", authCode);
					edit.commit();
					// new TokenGet().execute();
					getAccessToken(authCode);
					Toast.makeText(getApplicationContext(),
							"Authorization Code is: " + authCode,
							Toast.LENGTH_SHORT).show();
				} else if (url.contains("error=access_denied")) {
					Log.i("", "ACCESS_DENIED_HERE");
					resultIntent.putExtra("code", authCode);
					authComplete = true;
					setResult(Activity.RESULT_CANCELED, resultIntent);
					Toast.makeText(getApplicationContext(), "Error Occured",
							Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onReceivedHttpAuthRequest(WebView view,
					HttpAuthHandler handler, String host, String realm) {
				// TODO Auto-generated method stub
				super.onReceivedHttpAuthRequest(view, handler, host, realm);
			}
			
			@Override
			public void onLoadResource(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onLoadResource(view, url);
			}
			

			
		});

		}catch (Exception e) {
			e.printStackTrace();
		}
		// webView.setWebViewClient(new WebViewClient() {
		// @Override
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// if (url.startsWith(CALLBACK_URL)) {
		// authCode = Uri.parse(url).getQueryParameter("code");
		// System.out.println("Auth code: "+authCode);
		// webView.setVisibility(View.GONE);
		// getAccessToken(authCode);
		// return true;
		// }
		//
		// return super.shouldOverrideUrlLoading(view, url);
		// }
		// });
		//
		// webView.loadUrl(authorizationUrl);
	}

	private void getAccessToken(String authCode) {
		String accessTokenUrl = "https://runkeeper.com/apps/token?grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s&redirect_uri=%s";
		final String finalUrl = String.format(accessTokenUrl, authCode,
				CLIENT_ID, CLIENT_SECRET, CALLBACK_URL);

		Thread networkThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(finalUrl);

					HttpResponse response = client.execute(post);

					String jsonString = EntityUtils.toString(response
							.getEntity());
					final JSONObject json = new JSONObject(jsonString);

					String accessToken = json.getString("access_token");
					
					SharedPreferences.Editor editor = pref.edit();
					editor.putString("token", accessToken);
					editor.commit();
					
					Intent i = new Intent(AuthenticationActivity.this, InfoActivity.class);
					startActivity(i);
					//getTotalDistance(accessToken);

				} catch (Exception e) {
					displayToast("Exception occured:(");
					e.printStackTrace();
					resetUi();
				}

			}
		});

		networkThread.start();
	}

	private void getTotalDistance(String accessToken) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet("http://api.runkeeper.com/records");

			get.addHeader("Authorization", "Bearer " + accessToken);
			get.addHeader("Accept", "*/*");

			HttpResponse response = client.execute(get);

			String jsonString = EntityUtils.toString(response.getEntity());
			JSONArray jsonArray = new JSONArray(jsonString);
			findTotalWalkingDistance(jsonArray);

		} catch (Exception e) {
			displayToast("Exception occured:(");
			e.printStackTrace();
			resetUi();
		}
	}

	private void findTotalWalkingDistance(JSONArray arrayOfRecords) {
		try {
			// Each record has activity_type and array of statistics. Traverse
			// to activity_type = Walking
			for (int ii = 0; ii < arrayOfRecords.length(); ii++) {
				JSONObject statObject = (JSONObject) arrayOfRecords.get(ii);
				if ("Walking".equalsIgnoreCase(statObject
						.getString("activity_type"))) {
					// Each activity_type has array of stats, navigate to
					// "Overall" statistic to find the total distance walked.
					JSONArray walkingStats = statObject.getJSONArray("stats");
					for (int jj = 0; jj < walkingStats.length(); jj++) {
						JSONObject iWalkingStat = (JSONObject) walkingStats
								.get(jj);
						if ("Overall".equalsIgnoreCase(iWalkingStat
								.getString("stat_type"))) {
							long totalWalkingDistanceMeters = iWalkingStat
									.getLong("value");
							double totalWalkingDistanceMiles = totalWalkingDistanceMeters * 0.00062137;
							displayTotalWalkingDistance(totalWalkingDistanceMiles);
							return;
						}
					}
				}
			}
			displayToast("Something went wrong!!!");
		} catch (JSONException e) {
			displayToast("Exception occured:(");
			e.printStackTrace();
			resetUi();
		}
	}

	private void resetUi() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				button.setVisibility(View.VISIBLE);
				webView.setVisibility(View.GONE);
			}
		});
	}

	private void displayTotalWalkingDistance(double totalWalkingDistanceMiles) {
		final String milesWalkedMessage = (totalWalkingDistanceMiles < 1) ? "0 miles?, You get no respect, Start walking already!!!"
				: String.format("Cool, You have walked %.2f miles so far.",
						totalWalkingDistanceMiles);

		displayToast(milesWalkedMessage);
		resetUi();
	}

	private void displayToast(final String message) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), message,
						Toast.LENGTH_LONG).show();
			}
		});
	}

	// private class TokenGet extends AsyncTask<String, String, JSONObject> {
	// private ProgressDialog pDialog;
	// String Code;
	// @Override
	// protected void onPreExecute() {
	// super.onPreExecute();
	// pDialog = new ProgressDialog(MainActivity.this);
	// pDialog.setMessage("Contacting Google ...");
	// pDialog.setIndeterminate(false);
	// pDialog.setCancelable(true);
	// Code = pref.getString("Code", "");
	// pDialog.show();
	// }
	// @Override
	// protected JSONObject doInBackground(String... args) {
	// GetAccessToken jParser = new GetAccessToken();
	// JSONObject json =
	// jParser.gettoken(TOKEN_URL,Code,CLIENT_ID,CLIENT_SECRET,REDIRECT_URI,GRANT_TYPE);
	// return json;
	// }
	// @Override
	// protected void onPostExecute(JSONObject json) {
	// pDialog.dismiss();
	// if (json != null){
	// try {
	// String tok = json.getString("access_token");
	// String expire = json.getString("expires_in");
	// String refresh = json.getString("refresh_token");
	// Log.d("Token Access", tok);
	// Log.d("Expire", expire);
	// Log.d("Refresh", refresh);
	// auth.setText("Authenticated");
	// Access.setText("Access Token:"+tok+"nExpires:"+expire+"nRefresh Token:"+refresh);
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }else{
	// Toast.makeText(getApplicationContext(), "Network Error",
	// Toast.LENGTH_SHORT).show();
	// pDialog.dismiss();
	// }
	// }
	// }
	//
}