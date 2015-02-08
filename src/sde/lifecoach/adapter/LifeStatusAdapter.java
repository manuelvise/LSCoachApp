package sde.lifecoach.adapter;

import java.util.Date;
import java.util.List;

import com.example.lifecoachapp.R;

import sde.lifecoach.model.Goal;
import sde.lifecoach.model.HealthMeasureHistory;
import sde.lifecoach.model.HealthMeasureHistoryList;
import sde.lifecoach.model.LifeStatus;
import sde.lifecoach.model.Weight;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LifeStatusAdapter extends ArrayAdapter<LifeStatus> {

	private List<Goal> goals;

	public LifeStatusAdapter(Context context, int resource,
			List<LifeStatus> objects, List<Goal> goals) {
		super(context, resource, objects);
		this.goals = goals;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if (v == null) {

			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.row_list_lifestatus, null);

		}

		LifeStatus g = getItem(position);

		if (g != null) {
			TextView tvType = (TextView) v.findViewById(R.id.textViewType);
			TextView tvValue = (TextView) v.findViewById(R.id.textViewValue);
			TextView tvGoal = (TextView) v.findViewById(R.id.textViewGoal);

			if (tvType != null) {
				tvType.setText("Type: "
						+ g.getMeasureDefinition().getMeasureName());
			}

			if (tvValue != null) {
				tvValue.setText("Value: " + g.getValue());
			}

			if (tvGoal != null) {
				boolean found = false;
				if (goals != null) {
					for (Goal goal : goals) {

						if (goal.getMeasureDefinition()
								.getMeasureName()
								.equals(g.getMeasureDefinition()
										.getMeasureName())) {
							found = true;
							tvGoal.setText("Deadline: "
									+ new Date(Long.valueOf(goal.getDeadline()))
											.toString() + ", value: "
									+ goal.getValue());

							if ((Long.valueOf(goal.getValue()) > Long.valueOf(g
									.getValue()))
									&& goal.getMeasureDefinition()
											.getMeasureName().equals("weight")) {
								tvGoal.setBackgroundColor(Color.RED);
							}
							if ((Long.valueOf(goal.getValue()) <= Long
									.valueOf(g.getValue()))
									&& goal.getMeasureDefinition()
											.getMeasureName().equals("weight")) {
								tvGoal.setBackgroundColor(Color.GREEN);
							}

							break;
						}
					}
				} else {
					tvGoal.setText("No goal setted for this measureDefinition  ");
					tvGoal.setBackgroundColor(Color.LTGRAY);
				}
//				if (!found) {
//
//					tvGoal.setText("No goal setted.");
//
//				}

			}

		}

		return v;
	}

}
