package sde.lifecoach.activity;

import sde.lifecoach.asynctask.WeightsTask;

import com.example.lifecoachapp.R;
import com.example.lifecoachapp.R.id;
import com.example.lifecoachapp.R.layout;
import com.example.lifecoachapp.R.menu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class WeightsActivity extends Activity {

	private SharedPreferences pref;
	private String token;
	private Long personId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weights);
		
		pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		token = pref.getString("token", "");
		
		personId = pref.getLong("personId", 0);
		
		new WeightsTask(getApplicationContext(), token, this, personId).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weights, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
