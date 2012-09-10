package sdl.example.allyoushouldknow;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MediaPlayerTest extends Activity {
	private MediaPlayer mediaPlayer;
	private ImageButton btnStop, btnPlay;
	private boolean isPlaying = false;
	private AssetFileDescriptor descriptor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.media_player);
		btnPlay = (ImageButton) findViewById(R.id.btn_play);
		btnStop = (ImageButton) findViewById(R.id.btn_stop);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		try {
			AssetManager aManager = getAssets();
			descriptor = aManager.openFd("sounds/alex.mp3");
		} catch (IOException ioe) {
			Toast.makeText(this,
					"Couldn't load music file, " + ioe.getMessage(),
					Toast.LENGTH_LONG).show();
			mediaPlayer = null;
			ioe.printStackTrace();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play:
			isPlaying = true;
			if (mediaPlayer == null) {
				try {
					mediaPlayer = new MediaPlayer();
					mediaPlayer
							.setDataSource(descriptor.getFileDescriptor(),
									descriptor.getStartOffset(),
									descriptor.getLength());
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
			mediaPlayer.start();
			btnStop.setVisibility(View.VISIBLE);
			btnPlay.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_pause:
			isPlaying = false;
			if (mediaPlayer != null) {
				mediaPlayer.pause();
			}
			btnPlay.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_stop:
			isPlaying = false;
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
			btnStop.setVisibility(View.INVISIBLE);
			btnPlay.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isPlaying) {
			if (mediaPlayer != null) {
				mediaPlayer.start();
				btnPlay.setVisibility(View.INVISIBLE);
				btnStop.setVisibility(View.VISIBLE);
			}
		}

	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mediaPlayer != null) {
			mediaPlayer.pause();
			if (isFinishing()) {
				mediaPlayer.stop();
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.release();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean("isPlaying", isPlaying);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		isPlaying = savedInstanceState.getBoolean("isPlaying");
	}
}
