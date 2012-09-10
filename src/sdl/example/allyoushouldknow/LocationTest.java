package sdl.example.allyoushouldknow;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LocationTest extends Activity {
	private TextView textView, textMenu, textStatus;
	private LocationManager lManager;
	private Location location;
	private StringBuilder builder = new StringBuilder();
	private GetGeocodingTask geo;
	private final String undefined = "Undefined";
	private String ans[] = new String[3];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		textView = (TextView) findViewById(R.id.text_location);
		textMenu = (TextView) findViewById(R.id.text_menu);
		textStatus = (TextView) findViewById(R.id.text_status);
		lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (!isGPSEnable() && !isNTWEnable()) {
			textStatus.setTextColor(Color.RED);
			textStatus.setText("No providers are enable");
			enableLocationSettings();
		}
	}

	private class GetGeocodingTask extends AsyncTask<Location, Void, Void> {
		Context mContext;

		public GetGeocodingTask(Context context) {
			super();
			mContext = context;
		}

		@Override
		protected Void doInBackground(Location... lct) {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

			Location loc = lct[0];
			List<Address> addresses = null;

			try {
				addresses = geocoder.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
			} catch (IOException ioe) {
				Toast.makeText(getBaseContext(),
						"IOException has occured" + ioe.getMessage(),
						Toast.LENGTH_LONG).show();
				ioe.printStackTrace();
			}

			if (addresses != null & addresses.size() > 0) {
				Address address = addresses.get(0);
				ans[0] = address.getCountryName();
				ans[1] = address.getLocality();
				ans[2] = address.getAddressLine(0);

			}
			return null;
		}
	}

	private void insertData() {
		builder.setLength(0);
		for (String x : ans) {
			builder.append(String
					.format("%s\n\n", (x != null ? x : undefined)));
		}
		textView.setText(builder.toString());
		builder.setLength(0);
	}

	@Override
	protected void onResume() {
		super.onResume();
		builder.setLength(0);
		if (isGPSEnable()) {
			location = lManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			builder.append("GPS provider is enable\n");
		}

		if (isNTWEnable()) {
			location = lManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			builder.append("NETWORK provider is enable\n");
		}

		if (location != null) {
			geo = new GetGeocodingTask(this);
			geo.execute(location);
		}
		
		textStatus.setText((builder.length() > 0) ? builder.toString() : "No GPS or NETWORK provier");
		builder.setLength(0);
	}

	private boolean isGPSEnable() {
		return lManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	private boolean isNTWEnable() {
		return lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	private void enableLocationSettings() {
		Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putStringArray("ans", ans);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		ans = savedInstanceState.getStringArray("ans");
		if (ans != null) {
			textMenu.setVisibility(TextView.VISIBLE);
			insertData();
		}
	}

	private void refresh() {
		if (location != null) {
			geo = new GetGeocodingTask(this);
			geo.execute(location);
		} else {
			geo.execute(location);
		}
		insertData();
	}

	public void onClick(View v) {
		textMenu.setVisibility(TextView.VISIBLE);
		refresh();
	}
}
