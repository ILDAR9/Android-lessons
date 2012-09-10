package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener
{
	StringBuilder builder = new StringBuilder();
	TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.single_touch);
		textView = (TextView)findViewById(R.id.text_coordinates);
		textView.setText("Touch and drag (one finger only)!");		
		((ImageView)findViewById(R.id.board)).setOnTouchListener(this);			
	}
	
	public boolean onTouch(View v, MotionEvent event) 
	{
		builder.setLength(0);
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			builder.append("down, ");
			break;
		case MotionEvent.ACTION_MOVE:
			builder.append("move, ");
			break;
		case MotionEvent.ACTION_UP:
			builder.append("  up, ");
			break;
		case MotionEvent.ACTION_CANCEL:
			builder.append("cancel, ");
			break;
		}
		
		builder.append(event.getX() + ",  " + event.getY() + "\n");
		String text = builder.toString();
		Log.d("SingleTouchTest", text);
		textView.setText(text);	
		
		return true;
	}

}
