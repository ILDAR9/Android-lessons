package sdl.example.allyoushouldknow;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BitmapTest extends Activity
{
	class RenderView extends View
	{
		Bitmap firstBmp;
		Bitmap secondBmp;
		Rect dest = new Rect();
		InputStream is;
		RenderView(Context context)
		{
			super(context);
			
			try
			{
				AssetManager aManager = getAssets();
				is = aManager.open("images/first.png");
				firstBmp = BitmapFactory.decodeStream(is);
				is.close();
				
				Log.d("BitmapTest", "firstBmp.png format " + firstBmp.getConfig());
				
				is = aManager.open("images/second.png");
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_4444;
				secondBmp = BitmapFactory.decodeStream(is, null, options);
				is.close();
				Log.d("BitmapTest", "secondBmp.png format " + secondBmp.getConfig());
				
				
			} catch (IOException ioe)
			{
				Log.d("BitmapTest", "Oh my GOD - IOException was occured!");
			} finally
			{
				// ??? Close inputStream??? How?
			}
		}
		
		protected void onDraw(Canvas canvas)
		{
			canvas.drawColor(Color.BLACK);
			dest.set(50, 50, 350, 350);
			canvas.drawBitmap(firstBmp, null, dest, null);
			canvas.drawBitmap(secondBmp, 100, 100, null);
			invalidate();
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstaceState)
	{
		super.onCreate(savedInstaceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(new RenderView(this));
	}
	

}
