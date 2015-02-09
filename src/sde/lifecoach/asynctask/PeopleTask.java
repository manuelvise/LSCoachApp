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
import com.google.gson.reflect.TypeToken;

import sde.lifecoach.activity.AuthenticationActivity;
import sde.lifecoach.activity.LifeStatusActivity;
import sde.lifecoach.activity.PeopleActivity;
import sde.lifecoach.adapter.PeopleAdapter;
import sde.lifecoach.adapter.WeightsAdapter;
import sde.lifecoach.model.HealthMeasureHistory;
import sde.lifecoach.model.Person;
import sde.lifecoach.util.Urls;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PeopleTask extends AsyncTask<Void, Void, List<Person>> {

	String accessToken;
	List<Person> people;
	Context context;
	TextView tViewInfo;
	private ProgressBar progress;
	SharedPreferences pref;
	private Activity act;

	public PeopleTask(Context context, String token, ProgressBar progress, Activity act) {
		this.context = context;
		this.accessToken = token;
		this.progress = progress;
		this.act = act;
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
		pref = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	@Override
	protected List<Person> doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(Urls.URL_DATA_SERVICE+":6901/sde/person/business");

		get.addHeader("Authorization", "Bearer " + accessToken);
		get.addHeader("Accept", "application/json");

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
		people = gson.fromJson(jsonString, new TypeToken<List<Person>>(){}.getType());
		if (response.getStatusLine().equals(401)) {

			Toast.makeText(context, "Error 401, token not valid.",
					Toast.LENGTH_LONG).show();

			Intent i = new Intent(context, AuthenticationActivity.class);
			context.startActivity(i);
		}

		return people;
	}

	@Override
	protected void onPostExecute(final List<Person> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		progress.setVisibility(View.GONE);
		progress.setIndeterminate(false);
		
		if(result==null){
			Toast.makeText(act, "No people saved",Toast.LENGTH_SHORT).show();;
			return;
		}
		
		ListView listPeople = (ListView) act.findViewById(R.id.listPeople);
		
		// get data from the table by the ListAdapter
		PeopleAdapter peopleAdapter = new PeopleAdapter(context, R.layout.row_list_people, result);

		listPeople.setAdapter(peopleAdapter);
		
		
		listPeople.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent i = new Intent(context,
						LifeStatusActivity.class);
				i.putExtra("person", result.get(position));
				act.startActivity(i);
				
			}
		});
		
	}

}
