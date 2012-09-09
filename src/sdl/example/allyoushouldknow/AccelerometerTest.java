package sdl.example.allyoushouldknow;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerTest extends Activity implements SensorEventListener {
	StringBuilder builder = new StringBuilder();
	TextView textX;
	TextView textY;
	TextView textZ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.accelerometr);
		textX = (TextView) findViewById(R.id.text_x);
		textY = (TextView) findViewById(R.id.text_y);
		textZ = (TextView) findViewById(R.id.text_z);
		SensorManager sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		if (sManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
			Toast.makeText(this, "No accelerometer installed!",
					Toast.LENGTH_LONG).show();
		} else {
			Sensor accelerometer = sManager.getSensorList(
					Sensor.TYPE_ACCELEROMETER).get(0);
			if (!sManager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_GAME)) {
				Toast.makeText(this, "Couldn't register sensor listener",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent event) {
		textX.setTextColor(colorChange(textX, event.values[0]));
		textX.setText(String.format("%.4f", event.values[0]));

		textY.setTextColor(colorChange(textY, event.values[1]));
		textY.setText(String.format("%.4f", event.values[1]));

		textZ.setTextColor(colorChange(textZ, event.values[2]));
		textZ.setText(String.format("%.4f", event.values[2]));
	}

	private int colorChange(TextView text, float value){
		int stepColor = 28;
		if((int)value > 0){
			return Color.rgb(0,(int)value * stepColor,0);
		}
		if((int)value < 0){
			return Color.rgb((int)value * -stepColor,0,0);
		}
		return Color.rgb(stepColor,stepColor,0);
	}
}
