package sde.lifecoach.adapter;

import java.util.Date;
import java.util.List;

import com.example.lifecoachapp.R;

import sde.lifecoach.model.HealthMeasureHistory;
import sde.lifecoach.model.HealthMeasureHistoryList;
import sde.lifecoach.model.Weight;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ActivitiesAdapter extends ArrayAdapter<HealthMeasureHistory> {

	public ActivitiesAdapter(Context context, int resource,
			List<HealthMeasureHistory> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if (v == null) {

			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.row_list_activities, null);

		}

		HealthMeasureHistory hm = getItem(position);

		if (hm != null) {
			TextView tvWeight = (TextView) v.findViewById(R.id.textViewWeight);
			TextView tvDate = (TextView) v.findViewById(R.id.textViewDate);
			TextView textTypeActivity = (TextView)v.findViewById(R.id.textTypeActivity);

			if (tvWeight != null) {
				tvWeight.setText(hm.getValue() + " m        ");
			}

			if (tvDate != null) {
				if (Character.isDigit(hm.getTimestamp().charAt(0))) {
					tvDate.setText(new Date(Long.valueOf(hm.getTimestamp())).toString());
				} else {
					tvDate.setText(hm.getTimestamp());
				}
			}
			
			if(textTypeActivity !=  null){
				textTypeActivity.setText(hm.getMeasureDefinition().getMeasureName());
			}
		}

		return v;
	}

}
