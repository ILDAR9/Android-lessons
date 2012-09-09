package sdl.example.allyoushouldknow;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class GoogleMapTest extends MapActivity {
	private MapView mapView;
	private Location location;
	MapController mController;
	private LocationManager lManager;
	private MyLocationOverlay cMyLocationOverlay;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map);

		mapView = (MapView) findViewById(R.id.map_view);

		// Get the MapController
		mController = mapView.getController();

		mapView.setBuiltInZoomControls(true);

		lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		LocationListener lListener = new LocationListener() {
//
//			public void onLocationChanged(Location arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			public void onProviderDisabled(String arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			public void onProviderEnabled(String arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
//				// TODO Auto-generated method stub
//
//			}
//		};
//
//		lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
//				lListener);

		cMyLocationOverlay = new MyLocationOverlay(this,
				mapView);
		cMyLocationOverlay.disableCompass();
		cMyLocationOverlay.enableMyLocation();
				
	}
	
	public void onClick(View v){
		location = lManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		try {
			// mController.animateTo(cMyLocationOverlay.getMyLocation());
			mController.animateTo(new GeoPoint((int)(location.getLatitude()*1E6), 
					(int)(location.getLongitude()*1E6)));
			mController.setZoom(30);
		} catch (Exception e) {
			Log.d("GoogleMapTest", "Exception has occured!");
		}
		mapView.getOverlays().add(cMyLocationOverlay);
	}


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
