package sde.lifecoach.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Text;

import sde.lifecoach.asynctask.AddGoalTask;
import sde.lifecoach.asynctask.GoalsTask;
import sde.lifecoach.asynctask.WeightsTask;
import sde.lifecoach.model.Goal;
import sde.lifecoach.model.LifeStatus;
import sde.lifecoach.model.Person;

import com.example.lifecoachapp.R;
import com.example.lifecoachapp.R.id;
import com.example.lifecoachapp.R.layout;
import com.example.lifecoachapp.R.menu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class AddGoalActivity extends Activity {

	private SharedPreferences pref;
	private String token;
	private Long personId;
	private Person personSelected;

	private EditText editTextValue;
	private DatePicker datePickerDeadline;
	private Button buttonSaveGoal;
	private Spinner spinnerType;
	private ProgressBar progressBarWeights;
	private TextView personTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_goal);

		personSelected = (Person) getIntent().getSerializableExtra("person");
		
		personTv = (TextView)findViewById(R.id.personTv);
		personTv.setText(personSelected.getName());

		progressBarWeights = (ProgressBar)findViewById(R.id.progressBarWeights);
		progressBarWeights.setVisibility(View.GONE);
		editTextValue = (EditText) findViewById(R.id.editTextValue);
		datePickerDeadline = (DatePicker) findViewById(R.id.datePickerDeadline);
		buttonSaveGoal = (Button) findViewById(R.id.buttonSaveGoal);
		spinnerType = (Spinner) findViewById(R.id.spinnerType);

		List<String> listMeasureDef = new ArrayList<String>();
		for (LifeStatus lStatus : personSelected.getLifeStatus()) {
			listMeasureDef.add(lStatus.getMeasureDefinition().getMeasureName());
		}

		if (listMeasureDef.size() > 0) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_spinner_item, listMeasureDef);
			spinnerType.setAdapter(adapter); // this will set list of values to
												// spinner

			spinnerType.setSelection(0);// set selected value in spinner

			spinnerType.setAdapter(adapter);

		}
		buttonSaveGoal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int day = datePickerDeadline.getDayOfMonth();
				int month = datePickerDeadline.getMonth();
				int year = datePickerDeadline.getYear();
				Date d = new Date(year - 1900, month, day);

				if (editTextValue.getText().toString() != "") {

					Goal newGoal = new Goal();
					newGoal.setDeadline(d.getTime());
					newGoal.setMeasureDefinition(personSelected.getLifeStatus().get(spinnerType.getSelectedItemPosition()).getMeasureDefinition());
					newGoal.setPerson(personSelected);
					newGoal.setValue(editTextValue.getText().toString());

					new AddGoalTask(getApplicationContext(), AddGoalActivity.this, newGoal).execute();
				}
			}
		});

		// new GoalsTask(getApplicationContext(), token, this,
		// personId).execute();
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
