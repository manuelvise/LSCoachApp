package sde.lifecoach.asynctask;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.lifecoachapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sde.lifecoach.activity.AuthenticationActivity;
import sde.lifecoach.adapter.WeightsAdapter;
import sde.lifecoach.model.Goal;
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

public class AddGoalTask extends AsyncTask<Void, Void, Goal> {

	String accessToken;
	private Goal goalSaved;
	Context context;
	TextView tViewInfo;
	private ProgressBar progress;
	SharedPreferences pref;
	Activity act;
	private Goal newGoal;

	public AddGoalTask(Context context, Activity activity, Goal newGoal) {
		this.context = context;
		this.act = activity;
		this.newGoal = newGoal;
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
	protected Goal doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Urls.URL_DATA_SERVICE+":6901/sde/person/"+newGoal.getPerson().getIdPerson()+"/goals/"+newGoal.getMeasureDefinition().getMeasureName()+"?value="+newGoal.getValue()+"&deadline="+newGoal.getDeadline());

		post.addHeader("Authorization", "Bearer " + accessToken);
		post.addHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		

		HttpResponse response = null;
		String jsonString = null;

		try {
			response = client.execute(post);

			jsonString = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		goalSaved = gson.fromJson(jsonString, Goal.class);

		if (response.getStatusLine().equals(401)) {

			Toast.makeText(context, "Error 401, token not valid.",
					Toast.LENGTH_LONG).show();

			Intent i = new Intent(context, AuthenticationActivity.class);
			context.startActivity(i);
		}

		return goalSaved;
	}

	@Override
	protected void onPostExecute(Goal result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		progress.setVisibility(View.GONE);
		progress.setIndeterminate(false);
			
if(result!=null){
		Toast.makeText(context,"New goal saved!", 
                Toast.LENGTH_SHORT).show();
}else{
	Toast.makeText(context,"There is an error!", 
            Toast.LENGTH_SHORT).show();
}
	}

}
