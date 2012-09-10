package sdl.example.allyoushouldknow;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class RenderViewTest extends Activity
{
	Random rnd = new Random();
	
	class RenderView extends View
	{
		RenderView(Context context)
		{
			super(context);
		}
		
		protected void onDraw(Canvas canvas)
		{
			canvas.drawRGB(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			invalidate();
		}
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new RenderView(this));
	}
}
