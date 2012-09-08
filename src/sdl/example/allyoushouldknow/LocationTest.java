package sdl.example.allyoushouldknow;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class LocationTest extends Activity implements OnTouchListener
{
	TextView textView;
	LocationManager lManager;
	Location location;
	StringBuilder builder = new StringBuilder();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			
		textView = new TextView(this);
		textView.setOnTouchListener(this);
		setContentView(textView);
		
		
		if(!isGpsEnable() && !isNtwEnable())
		{
			textView.setText("No providers are enable");
			enableLocationSettings();
		}
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		if(isGpsEnable())
		{
			location = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			builder.append("GPS Provider is enable\n");
		}
		
		if(isNtwEnable())
		{
			location = lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			builder.append("Network provider is enable\n");
		}
		
		//builder.append(location);
		
		if(builder.length() > 0)
		{
			textView.setText(builder.toString());
		}
		
		if(location != null)
		{
		new ReverseGeocodingTask(this).execute(lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		} 
		
		textView.setText(builder.toString());
		/*
		if(location != null)
		{
		builder.append("Latitude: " + location.getLatitude() + "\n" +
						"Longitude: " + location.getLongitude() + "\n" +
						"Other information goes here: " + location.getTime());
		}	
		*/
	}
	
	private boolean isNtwEnable()
	{
		return lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}
	
	private boolean isGpsEnable()
	{
		return lManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
	
	
	private void enableLocationSettings()
	{
		Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(settingsIntent);
	}
	
	private void refresh()
	{
		textView.setText("");
		textView.setText(builder.toString());
	}
	
	
	
	private class ReverseGeocodingTask extends AsyncTask<Location, Void, Void>
	{
		Context mContext;
		
		public ReverseGeocodingTask(Context context)
		{
			super();
			mContext = context;
		}

		@Override
		protected Void doInBackground(Location... locs) 
		{
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
			
			Location location = locs[0];
			List<Address> addresses = null;
			try
			{
				addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			} catch(Exception e)
			{
				textView.setText("Exception has occured " + e.getMessage() + "Geocoder is present: " + Geocoder.isPresent() + "\n" + geocoder.toString());
			
			}
			if(addresses != null && addresses.size() > 0)
			{
				Address address = addresses.get(0);
				builder.setLength(0);
				builder.append(address.getAddressLine(0) + "\n" +
								address.getLocality() + "\n" +
								address.getCountryName() + "\n" +
								address.getLongitude() + "\n" +
								address.getLatitude());
			}
			return null;
		}

	}



	public boolean onTouch(View view, MotionEvent me) 
	{
		refresh();
		return true;
	}
}
