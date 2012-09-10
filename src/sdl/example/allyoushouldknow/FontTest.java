package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FontTest extends Activity {
	class RenderView extends View {
		Paint paint;
		Typeface font;
		Rect bounds = new Rect();

		RenderView(Context context) {
			super(context);
			paint = new Paint();
			font = Typeface
					.createFromAsset(context.getAssets(), "testfont.ttf");
		}

		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.BLACK);

			paint.setColor(Color.YELLOW);
			paint.setTypeface(font);
			paint.setTextSize(35);
			paint.setTextAlign(Paint.Align.CENTER);

			canvas.drawText(
					"This is image, ",
					canvas.getWidth() / 2, canvas.getHeight() / 2, paint);
			canvas.drawText(
					"which is drawed by string input",
					canvas.getWidth() / 2, canvas.getHeight() / 2 + 40, paint);
			canvas.drawText(
					"in some font style",
					canvas.getWidth() / 2, canvas.getHeight() / 2+80, paint);
			String text = "Another text font is used! O_o";
			paint.setColor(Color.GREEN);
			paint.setTextSize(25);
			paint.setTextAlign(Paint.Align.LEFT);
			paint.getTextBounds(text, 0, text.length(), bounds);

			canvas.drawText(text, canvas.getWidth() - bounds.width(), 140,
					paint);

			invalidate();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(new RenderView(this));
	}
}
