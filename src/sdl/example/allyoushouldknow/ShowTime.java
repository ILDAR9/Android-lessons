package sdl.example.allyoushouldknow;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ShowTime extends Activity {
	private TextView textView;
	Timer myTimer = new Timer();
	final Handler uiHandler = new Handler();
	TimerTask timerTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.show_time);
		textView = (TextView) findViewById(R.id.text_time);
		timerTask  = new TimerTask() { 
		    @Override
		    public void run() {
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				final String time = sdf.format(new Date(System
						.currentTimeMillis()));
				sdf.applyPattern("dd.MM.yyyy");
				final String date = sdf.format(new Date(System
						.currentTimeMillis()));
		        uiHandler.post(new Runnable() {		            
		            public void run() {
		            	textView.setText(String.format(
								"%s\n%s", time,
								date));
		            }
		        });
		    }
		};
		myTimer.schedule(timerTask, 0L, 1000L); 

	}
}
