package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener {
	private StringBuilder builder = new StringBuilder();
	private TextView textView, textCount;
	float x[] = new float[10];
	float y[] = new float[10];
	boolean touched[] = new boolean[10];
	private String count[] = new String[10];

	private void updateTextView() {
		builder.setLength(0);
		for (int i = 0; i < 10; i++) {
			builder.append(touched[i] + ", " + x[i] + ", " + y[i] + "\n\n");
		}
		textView.setText(builder.toString());
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multy_touch);
		textView = (TextView) findViewById(R.id.text_multy);
		textView.setText("Touch and drag (multiply fingers supported)!");
		textCount = (TextView) findViewById(R.id.text_count);
		((ViewGroup) findViewById(R.id.multy_listener))
				.setOnTouchListener(this);
		count[0] = "1";
		for (int i = 1; i < 10; i++) {
			count[i] = String.format("%s\n\n%d", count[i - 1], i + 1);
		}
	}

	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;

		int pointerId = event.getPointerId(pointerIndex);

		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			touched[pointerId] = true;
			x[pointerId] = (int) event.getX(pointerIndex);
			y[pointerId] = (int) event.getY(pointerIndex);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL:
			touched[pointerId] = false;
			x[pointerId] = (int) event.getX(pointerIndex);
			x[pointerId] = (int) event.getY(pointerIndex);
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() <= 10) {
				textCount.setText(count[event.getPointerCount() - 1]);
			} else {
				textCount.setText(count[9]);
			}
			for (int i = 0; i < event.getPointerCount(); i++) {
				pointerId = event.getPointerId(i);
				x[pointerId] = (int) event.getX(i);
				y[pointerId] = (int) event.getY(i);
			}
			break;
		}
		updateTextView();
		return true;
	}

}
