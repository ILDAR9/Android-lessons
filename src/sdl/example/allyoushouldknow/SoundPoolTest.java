package sdl.example.allyoushouldknow;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SoundPoolTest extends Activity implements OnTouchListener {
	SoundPool soundPool;
	int awesomeId = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soundpool);
		ImageView btn = (ImageView) findViewById(R.id.img_btn);
		btn.setOnTouchListener(this);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

		try {
			AssetManager aManager = getAssets();
			AssetFileDescriptor descriptor = aManager
					.openFd("sounds/awesome.mp3");
			awesomeId = soundPool.load(descriptor, 1);
		} catch (IOException ioe) {
			Toast.makeText(
					this,
					"Couldn't load sound effect from asset:( "
							+ ioe.getMessage(), Toast.LENGTH_LONG).show();
			ioe.printStackTrace();
		}

	}

	public void onResume() {
		super.onResume();
		Toast.makeText(this, getResources().getString(R.string.sp_dcpt),Toast.LENGTH_LONG).show();
	}

	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (awesomeId != -1) {
				soundPool.play(awesomeId, 1.0f, 1.0f, 0, 0, 1);
			}
		}
		return true;
	}
}
