package sdl.example.allyoushouldknow;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AllYouShouldKnow extends ListActivity {
	String tests[];
	private final String packageName = "sdl.example.allyoushouldknow.";
	private class AppsStore {	    
		private String appName;
	    private String description;
	    
	    public AppsStore(String appName, String description) {
	        this.appName = appName;
	        this.description = description;
	    }
	    public String getAppName() {
			return appName;
		}

		public String getDescription() {
			return description;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Resources res = getResources();
		tests = res.getStringArray(R.array.app_name);
		String[] dcpt = res.getStringArray(R.array.app_dcpt);
		ArrayList<AppsStore> appsList = new ArrayList<AppsStore>();
		for (int i =0;i < tests.length; i++){
			appsList.add(new AppsStore(tests[i], dcpt[i]));
		}
		setListAdapter(new AppsItemAdapter(this, R.layout.test, appsList));
	}

	public class AppsItemAdapter extends ArrayAdapter<AppsStore> {
		private ArrayList<AppsStore> apps;

		public AppsItemAdapter(Context context, int textViewResourceId,
				ArrayList<AppsStore> apps) {
			super(context, textViewResourceId, apps);
			this.apps = apps;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.main_item, null);
			}
			AppsStore app = apps.get(position);
			if (app != null) {
				TextView head = (TextView) v.findViewById(R.id.tHead);
				TextView uner = (TextView) v.findViewById(R.id.tUnder);

				if (head != null) {
					head.setText(app.getAppName());
				}

				if (uner != null) {
					uner.setText(app.getDescription());
				}
			}
			return v;
		}
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position,
			long id) {
		super.onListItemClick(list, view, position, id);
		String testName = tests[position];
		try {
			Class cl = Class.forName(packageName + testName);
			startActivity(new Intent(this, cl));
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
