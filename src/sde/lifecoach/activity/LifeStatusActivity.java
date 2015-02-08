package sde.lifecoach.activity;

import sde.lifecoach.adapter.LifeStatusAdapter;
import sde.lifecoach.adapter.PeopleAdapter;
import sde.lifecoach.asynctask.GoalsTask;
import sde.lifecoach.asynctask.WeightsTask;
import sde.lifecoach.model.Person;

import com.example.lifecoachapp.R;
import com.example.lifecoachapp.R.id;
import com.example.lifecoachapp.R.layout;
import com.example.lifecoachapp.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class LifeStatusActivity extends Activity {

	private SharedPreferences pref;
	private Person personSelected;
	private ListView listLifeStatus;
	private TextView tvPerson;
	private Button buttonAddGoal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifestatus);

		personSelected = (Person) getIntent().getSerializableExtra("person");

		tvPerson = (TextView) findViewById(R.id.tvPerson);
		tvPerson.setText(tvPerson.getText() + personSelected.getName());

		listLifeStatus = (ListView) findViewById(R.id.listLifestatus);
		// get data from the table by the ListAdapter
		LifeStatusAdapter lsAdapter = new LifeStatusAdapter(
				getApplicationContext(), R.layout.row_list_lifestatus,
				personSelected.getLifeStatus(), personSelected.getGoals());

		listLifeStatus.setAdapter(lsAdapter);
		lsAdapter.notifyDataSetChanged();
		
		
		buttonAddGoal = (Button)findViewById(R.id.buttonAddGoal);
		
		buttonAddGoal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						AddGoalActivity.class);
				i.putExtra("person", personSelected);
				
				startActivity(i);	
			}
		});
		

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
