package sdl.example.allyoushouldknow;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewTest extends Activity {

	private FastRenderView fastRenderView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			fastRenderView = new FastRenderView(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setContentView(fastRenderView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		fastRenderView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		fastRenderView.pause();
	}

	class FastRenderView extends SurfaceView implements Runnable {

		Thread renderThread;
		SurfaceHolder holder;
		volatile boolean running = false;
		Random rnd = new Random();
		Sprite sprite;
		Bitmap spriteImg = null;
		long startTime, sleepTime;

		public FastRenderView(Context context) throws IOException {
			super(context);
			holder = getHolder();
			InputStream is = null;

			try {
				AssetManager aManager = context.getAssets();
				is = aManager.open("images/image.png");
				spriteImg = BitmapFactory.decodeStream(is);
			} catch (IOException ioe) {
				Log.e("LoadingAsstets", "IOException while loading");
			} finally {
				is.close();
			}

		}

		public void pause() {
			running = false;
			while (true) {
				try {
					renderThread.join();
					break;
				} catch (InterruptedException ie) {
					Log.d("FastRenderView", "Interrupt Exception");
				}
			}
		}

		public void resume() {
			running = true;
			renderThread = new Thread(this);
			renderThread.start();
		}

		public void run() {
			// TODO Auto-generated method stub
			sprite = new Sprite(fastRenderView, spriteImg);
			startTime = System.currentTimeMillis();
			while (running) {
				if (!holder.getSurface().isValid())
					continue;

				Canvas canvas = holder.lockCanvas();
				canvas.drawColor(Color.BLACK);
				sprite.drawYourSelf(canvas);
				holder.unlockCanvasAndPost(canvas);

			}

			sleepTime = 100 - (System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				} else {
					Thread.sleep(10);
				}
			} catch (Exception e) {
				Log.e("error", "exception");
			}
		}

	}

	class Sprite {
		final int COLUMNS = 3;
		final int ROWS = 4;
		Bitmap spriteImg;
		FastRenderView fastRenderView;
		int x, y, speedX = 1, speedY = 0;
		int width, height;
		Rect src, dst;
		int currentFrame = 0;

		public Sprite(FastRenderView frv, Bitmap bmp) {
			this.fastRenderView = frv;
			this.spriteImg = bmp;

			Random rnd = new Random();
			// this.x=rnd.nextInt(fastRenderView.getWidth());
			// this.y=rnd.nextInt(fastRenderView.getHeight());
			this.x = rnd.nextInt(500);
			this.y = rnd.nextInt(500);
			this.width = spriteImg.getWidth() / COLUMNS;
			this.height = spriteImg.getHeight() / ROWS;
		}

		public void update() {
			x += speedX;
			if (x < 0) {
				x = fastRenderView.getWidth();
			}
			if (x > fastRenderView.getWidth()) {
				x = 0;
			}
			currentFrame = ++currentFrame % 3;
			src = new Rect(this.width * currentFrame, this.height * 2,
					this.width * (currentFrame + 1), this.height * 3);
			dst = new Rect(this.x, this.y, this.x + this.width, this.y
					+ this.height);
		}

		public void drawYourSelf(Canvas canvas) {
			update();
			canvas.drawBitmap(spriteImg, src, dst, null);
		}
	}
}
