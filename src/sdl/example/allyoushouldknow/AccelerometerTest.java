package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener
{
	StringBuilder builder= new StringBuilder();
	TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		
		setContentView(textView);
		
		SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		if(sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0)
		{
			textView.setText("No accelerometer installed!");
		} else
		{
			Sensor accelerometer = sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			if(!sManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME))
			{
				textView.setText("Couldn't register sensor listener");
			}
		}
	}
	
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) 
	{
		builder.setLength(0);
		builder.append("x: " + event.values[0] + "\n");
		builder.append("y: " + event.values[1] + "\n");
		builder.append("z: " + event.values[2] + "\n");
		textView.setText(builder.toString());
	}

}
