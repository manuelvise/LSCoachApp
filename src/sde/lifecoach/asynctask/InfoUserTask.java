package sde.lifecoach.asynctask;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.lifecoachapp.R;
import com.google.gson.Gson;

import sde.lifecoach.activity.AuthenticationActivity;
import sde.lifecoach.model.Person;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoUserTask extends AsyncTask<Void, Void, Person> {

	String accessToken;
	Person personInfo;
	Context context;
	TextView tViewInfo;
	private ProgressBar progress;

	public InfoUserTask(Context context, String token, TextView tViewInfo, ProgressBar progress) {
		this.context = context;
		this.accessToken = token;
		this.tViewInfo = tViewInfo;
		this.progress = progress;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		// progress = ProgressDialog.show(context, "Loading...",
		// "User data", true);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.info_layout, null);
		progress.setVisibility(View.VISIBLE);
		progress.animate();
		progress.setIndeterminate(true);
	}

	@Override
	protected Person doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://10.20.214.122:6901/sde/person/sync");

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
		personInfo = gson.fromJson(jsonString, Person.class);

		if (response.getStatusLine().equals(401)) {

			Toast.makeText(context, "Error 401, token not valid.",
					Toast.LENGTH_LONG).show();

			Intent i = new Intent(context, AuthenticationActivity.class);
			context.startActivity(i);
		}

		return personInfo;
	}

	@Override
	protected void onPostExecute(Person result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		progress.setVisibility(View.GONE);
		progress.setIndeterminate(false);
		
		tViewInfo.setText(personInfo.getName());
	}

}
