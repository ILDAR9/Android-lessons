package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener
{
	StringBuilder builder = new StringBuilder();
	TextView textView;
	float x [] = new float [10];
	float y [] = new float [10];
	boolean touched [] = new boolean [10];
	
	private void updateTextView()
	{
		builder.setLength(0);
		for(int i = 0; i < 10; i++)
		{
			builder.append(touched[i] + ", " + x[i] + ", " + y[i] + "\n");
		}
		textView.setText(builder.toString());
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		textView.setText("Touch and drag (multiply fingers supported)!");
		textView.setOnTouchListener(this);
		
		setContentView(textView);
	}

	public boolean onTouch(View v, MotionEvent event) 
	{
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> 
							MotionEvent.ACTION_POINTER_ID_SHIFT;
		
		
		int pointerId = event.getPointerId(pointerIndex);
		
		switch(action)
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			touched[pointerId] = true;
			x[pointerId] = (int)event.getX(pointerIndex);
			y[pointerId] = (int)event.getY(pointerIndex);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL:
			touched[pointerId] = false;
			x[pointerId] = (int)event.getX(pointerIndex);
			x[pointerId] = (int)event.getY(pointerIndex);
			break;
		case MotionEvent.ACTION_MOVE:
			for(int i = 0; i < event.getPointerCount(); i++)
			{
				pointerId = event.getPointerId(i);
				x[pointerId] = (int)event.getX(i);
				y[pointerId] = (int)event.getY(i);
			}
			break;
		}
		updateTextView();	
		return true;
	}

}
