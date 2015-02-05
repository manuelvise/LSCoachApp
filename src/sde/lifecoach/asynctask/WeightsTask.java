package sde.lifecoach.asynctask;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.lifecoachapp.R;
import com.google.gson.Gson;

import sde.lifecoach.activity.AuthenticationActivity;
import sde.lifecoach.adapter.WeightsAdapter;
import sde.lifecoach.model.HealthMeasureHistory;
import sde.lifecoach.model.HealthMeasureHistoryList;
import sde.lifecoach.model.Person;
import sde.lifecoach.util.Urls;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WeightsTask extends AsyncTask<Void, Void, HealthMeasureHistoryList> {

	String accessToken;
	HealthMeasureHistoryList healthHistory;
	Context context;
	TextView tViewInfo;
	private ProgressBar progress;
	SharedPreferences pref;
	Activity act;
	Long idP;

	public WeightsTask(Context context, String token, Activity activity, Long idP) {
		this.context = context;
		this.accessToken = token;
		this.act = activity;
		this.idP = idP;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		// progress = ProgressDialog.show(context, "Loading...",
		// "User data", true);
		progress = (ProgressBar) act.findViewById(R.id.progressBarWeights);
		progress.setVisibility(View.VISIBLE);
		progress.animate();
		progress.setIndeterminate(true);
	}

	@Override
	protected HealthMeasureHistoryList doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(Urls.URL_DATA_SERVICE+":6901/sde/person/"+idP+"/weight/remote");

		get.addHeader("Authorization", "Bearer " + accessToken);
		get.addHeader("Accept", "*/*");

		HttpResponse response = null;
		String jsonString = null;

		try {
			response = client.execute(get);

			jsonString = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		healthHistory = gson.fromJson(jsonString, HealthMeasureHistoryList.class);

		if (response.getStatusLine().equals(401)) {

			Toast.makeText(context, "Error 401, token not valid.",
					Toast.LENGTH_LONG).show();

			Intent i = new Intent(context, AuthenticationActivity.class);
			context.startActivity(i);
		}

		return healthHistory;
	}

	@Override
	protected void onPostExecute(HealthMeasureHistoryList result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		progress.setVisibility(View.GONE);
		progress.setIndeterminate(false);
		
		ListView listWeights = (ListView) act.findViewById(R.id.listWeights);
		

		// get data from the table by the ListAdapter
		WeightsAdapter weightsAdapter = new WeightsAdapter(context, R.layout.row_list_weights, result.getHistory());

		listWeights.setAdapter(weightsAdapter);
		
	}

}
