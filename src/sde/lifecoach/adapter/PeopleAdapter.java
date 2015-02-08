package sde.lifecoach.adapter;

import java.util.Date;
import java.util.List;

import com.example.lifecoachapp.R;

import sde.lifecoach.model.Goal;
import sde.lifecoach.model.HealthMeasureHistory;
import sde.lifecoach.model.HealthMeasureHistoryList;
import sde.lifecoach.model.Person;
import sde.lifecoach.model.Weight;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PeopleAdapter extends ArrayAdapter<Person> {

	public PeopleAdapter(Context context, int resource,
			List<Person> objects) {
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
			v = vi.inflate(R.layout.row_list_people, null);

		}

		Person g = getItem(position);

		if (g != null) {
			TextView tvName = (TextView) v.findViewById(R.id.textViewName);
			
			if(tvName != null){
				tvName.setText(g.getName());
			}

		}

		return v;
	}
	
	
	

}
