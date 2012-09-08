package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTest extends Activity 
{
	StringBuilder builder = new StringBuilder("LifrCycleTest Activity! Do you know how it works?\n");
	TextView textView;
	
	private void log(String message)
	{
		Log.d("LifeCycleTest", message);
		builder.append(message + "\n");
		textView.setText(builder.toString());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		
		setContentView(textView);
		
		log("Created");
	}
	
	protected void onResume()
	{
		super.onResume();
		
		log("Resumed");
	}
	
	protected void onPause()
	{
		super.onPause();
		
		log("Paused");
		
		if(isFinishing())
		{
			log("Finishing");
		}
	}

}
