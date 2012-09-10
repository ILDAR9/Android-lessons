package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class IntentTest extends Activity 
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intenttest);		
	}

	public void onClick(View view) 
	{
		Intent intent;
		
		switch(view.getId())
		{
		case R.id.timeButton:
			intent = new Intent(this, ShowTime.class);
			startActivity(intent);
			break;
		case R.id.webButton:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
			startActivity(intent);
			break;
		case R.id.mapButton:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.75428, 37.6200"));
			startActivity(intent);
			break;
		case R.id.callButton:
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:89269471910"));
			startActivity(intent);
			break;
		}
		
	}
}
