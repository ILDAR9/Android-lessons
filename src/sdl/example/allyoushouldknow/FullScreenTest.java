package sdl.example.allyoushouldknow;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenTest extends SingleTouchTest
{
	WakeLock wakeLock;
		
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		
		PowerManager pManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = pManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My lock");
		
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		wakeLock.acquire();
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		wakeLock.release();
	}
	
}
