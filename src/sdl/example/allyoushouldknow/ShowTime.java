package sdl.example.allyoushouldknow;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTime extends Activity
{
	TextView textView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		
		textView.setTextSize(45);
		setContentView(textView);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(new Date(System.currentTimeMillis()));
		sdf.applyPattern("dd.MM.yyyy");
		String date = sdf.format(new Date(System.currentTimeMillis()));
		
		textView.setText("Current time: \n" + time + "\n Current date: \n" + date);
	}
}
