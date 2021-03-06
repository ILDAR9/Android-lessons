package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimationTest extends Activity {
	final int MENU_ALPHA_ID = 1;
	final int MENU_SCALE_ID = 2;
	final int MENU_TRANSLATE_ID = 3;
	final int MENU_ROTATE_ID = 4;
	final int MENU_COMBO_ID = 5;
	TextView tv;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.animtest);
		tv = (TextView) findViewById(R.id.tv);
		registerForContextMenu(tv);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		if (v.getId() == R.id.tv) {
			menu.add(0, MENU_ALPHA_ID, 0, "Alpha");
			menu.add(0, MENU_SCALE_ID, 0, "Scale");
			menu.add(0, MENU_ROTATE_ID, 0, "Rotate");
			menu.add(0, MENU_TRANSLATE_ID, 0, "Translate");
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean onContextItemSelected(MenuItem item) {

		Animation anim = null;

		switch (item.getItemId()) {
		case MENU_ALPHA_ID: {
			anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
			break;
		}
		case MENU_SCALE_ID: {
			anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
			break;
		}
		case MENU_TRANSLATE_ID: {
			anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
			break;
		}
		case MENU_ROTATE_ID: {
			anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
			break;
		}
		}

		tv.startAnimation(anim);
		return super.onContextItemSelected(item);

	}
}
