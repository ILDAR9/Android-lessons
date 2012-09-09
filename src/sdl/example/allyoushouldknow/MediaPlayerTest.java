package sdl.example.allyoushouldknow;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MediaPlayerTest extends Activity {
	MediaPlayer mediaPlayer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView image = new ImageView(this);
		image.setImageResource(R.drawable.media_back);
		setContentView(image);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		mediaPlayer = new MediaPlayer();
		try {
			AssetManager aManager = getAssets();
			AssetFileDescriptor descriptor = aManager.openFd("sounds/alex.mp3");
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(), descriptor.getLength());
			mediaPlayer.prepare();
			mediaPlayer.setLooping(true);
		} catch (IOException ioe) {
			Toast.makeText(this,
					"Couldn't load music file, " + ioe.getMessage(),
					Toast.LENGTH_LONG).show();
			mediaPlayer = null;
			ioe.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mediaPlayer != null) {
			mediaPlayer.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mediaPlayer != null) {
			mediaPlayer.pause();
			if (isFinishing()) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
	}
}
