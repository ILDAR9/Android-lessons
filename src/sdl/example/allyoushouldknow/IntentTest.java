package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class IntentTest extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intenttest);
		
		Button btnTime = (Button) findViewById(R.id.timeButton);
		Button btnWeb = (Button) findViewById(R.id.webButton);
		Button btnMap = (Button) findViewById(R.id.mapButton);
		Button btnCall = (Button) findViewById(R.id.callButton);
		btnTime.setOnClickListener(this);
		btnWeb.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		btnCall.setOnClickListener(this);
	}

	public void onClick(View view) 
	{
		Intent intent;
		
		switch(view.getId())
		{
		case R.id.timeButton:
			intent = new Intent("showTimePlease");
			startActivity(intent);
			break;
		case R.id.webButton:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
			startActivity(intent);
			break;
		case R.id.mapButton:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.754283, 37.62002"));
			startActivity(intent);
			break;
		case R.id.callButton:
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:79269471910"));
			startActivity(intent);
			break;
		}
		
	}
}
