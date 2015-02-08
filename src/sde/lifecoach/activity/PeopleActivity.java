package sde.lifecoach.activity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import sde.lifecoach.asynctask.InfoUserTask;
import sde.lifecoach.asynctask.PeopleTask;
import sde.lifecoach.model.Person;

import com.example.lifecoachapp.R;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PeopleActivity extends Activity {

	private String token;
	private TextView textViewInfo;

	private Person personInfo;

	private Boolean threadFinished = false;
	private SharedPreferences pref;
	private ProgressBar progress;
	private Button buttonWeights;
	private Button buttonActivities;
	private Button buttonGoals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_people);

		progress = (ProgressBar) findViewById(R.id.progressBarWeights);
		progress.setVisibility(View.GONE);
		new PeopleTask(getApplicationContext(),token, progress,PeopleActivity.this).execute();
		
//		ListView listPeople = (ListView)findViewById(R.id.listPeople);
//		
//		listPeople.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				
//				
//			}
//		});
//		
	}

//	private void getInfoUser(final String accessToken) {
//
//		Handler handler = new Handler();
//		Runnable run;
//		Thread networkThread = new Thread(run = new Runnable() {
//
//			@Override
//			public void run() {
//
//				try {
//					HttpClient client = new DefaultHttpClient();
//					HttpGet get = new HttpGet(
//							"http://10.20.214.122:6901/sde/person/sync");
//
//					get.addHeader("Authorization", "Bearer " + accessToken);
//					get.addHeader("Accept", "*/*");
//
//					HttpResponse response = client.execute(get);
//
//					String jsonString = EntityUtils.toString(response
//							.getEntity());
//
//					Gson gson = new Gson();
//					personInfo = gson.fromJson(jsonString, Person.class);
//
//					if (response.getStatusLine().equals(401)) {
//
//						displayToast("Error 401, token not valid.");
//
//						Intent i = new Intent(getApplicationContext(),
//								AuthenticationActivity.class);
//						startActivity(i);
//					}
//
//					threadFinished = true;
//
//				} catch (Exception e) {
//					displayToast("Exception occured:(");
//					e.printStackTrace();
//				}
//
//			}
//
//		});
//
//		networkThread.start();
//
////		Thread threadUi = new Thread(new Runnable() {
////
////			@Override
////			public void run() {
////
////				try {
////
////					while (threadFinished == false) {
////					}
////
////				} catch (Exception e) {
////					displayToast("Exception occured:(");
////					e.printStackTrace();
////				}
////
////			}
////
////		});
////
////		threadUi.start();
//
//	}

	private void displayToast(final String message) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), message,
						Toast.LENGTH_LONG).show();
			}
		});
	}

}
