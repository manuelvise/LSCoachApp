package sde.lifecoach.adapter;

import java.util.Date;
import java.util.List;

import com.example.lifecoachapp.R;

import sde.lifecoach.model.Goal;
import sde.lifecoach.model.HealthMeasureHistory;
import sde.lifecoach.model.HealthMeasureHistoryList;
import sde.lifecoach.model.Weight;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GoalsAdapter extends ArrayAdapter<Goal> {

	public GoalsAdapter(Context context, int resource,
			List<Goal> objects) {
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
			v = vi.inflate(R.layout.row_list_goals, null);

		}

		Goal g = getItem(position);

		if (g != null) {
			TextView tvType = (TextView) v.findViewById(R.id.textViewType);
			TextView tvValue = (TextView) v.findViewById(R.id.textViewValue);
			TextView tvDate = (TextView) v.findViewById(R.id.textViewDate);
			
			if(tvType != null){
				tvType.setText(g.getMeasureDefinition().getMeasureName());
			}

			if (tvValue != null) {
				tvValue.setText(g.getValue() + " m        ");
			}

			if (tvDate != null) {
					tvDate.setText("Deadline: "+new Date(Long.valueOf(g.getDeadline())).toString());
			}
			
		}

		return v;
	}
	
	
	

}
