package sdl.example.allyoushouldknow;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class GoogleMapTest extends MapActivity
{	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mapview);
		
		MapView mapView = (MapView) findViewById(R.id.mapView);
		
		// Get the MapController
		MapController mController = mapView.getController();
		
		mapView.setBuiltInZoomControls(true);
		
		LocationManager lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener lListener = new LocationListener()
		{

			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
		};
		
		lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, lListener);
		
		MyLocationOverlay cMyLocationOverlay = new MyLocationOverlay(this, mapView);
		cMyLocationOverlay.disableCompass();
		cMyLocationOverlay.enableMyLocation();
		mapView.getOverlays().add(cMyLocationOverlay);
		

		
		Location location = lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
			try
			{
				mController.animateTo(cMyLocationOverlay.getMyLocation());
				//mController.animateTo(new GeoPoint((int) location.getLatitude() * 1000000, (int) location.getLongitude() * 1000000));
				mController.setZoom(15);
			} catch (Exception e)
			{
				Log.d("GoogleMapTest", "Exception has occured!");
			}
	}

	@Override
	protected boolean isRouteDisplayed() 
	{
		// TODO Auto-generated method stub
		return false;
	}
}
