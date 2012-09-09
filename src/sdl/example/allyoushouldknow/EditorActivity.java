package sdl.example.allyoushouldknow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditorActivity extends Activity {
	private static final int IDM_NEW = 200;
	private static final int IDM_OPEN = 201;
	private static final int IDM_SAVE = 202;
	private static final int IDM_EXIT = 203;

	private static final String DIRECTORY_DOCUMENTS = "/docs";
	private static final String FILE_EXT = ".txt";

	private EditText editText;
	private String curFileName = "";
	private String dir;
	private int pos = 0;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.editor);
		editText = (EditText) findViewById(R.id.editor);		
		dir = Environment.getExternalStorageDirectory().toString()
				+ DIRECTORY_DOCUMENTS;
		File folder = new File(dir);

		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_NEW, Menu.NONE, R.string.menu_new)
				.setIcon(R.drawable.menu_new).setAlphabeticShortcut('n');
		menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, R.string.menu_open)
				.setIcon(R.drawable.menu_open).setAlphabeticShortcut('o');
		menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, R.string.menu_save)
				.setIcon(R.drawable.menu_save).setAlphabeticShortcut('s');
		menu.add(Menu.NONE, IDM_EXIT, Menu.NONE, R.string.menu_exit)
				.setIcon(R.drawable.menu_exit).setAlphabeticShortcut('x');

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_NEW:
			curFileName = "";
			editText.setText("");
			this.setTitle(R.string.app_name);
			break;
		case IDM_OPEN:
			callOpenDialog();
			break;
		case IDM_SAVE:
			callSaveDialog();
			break;
		case IDM_EXIT:
			finish();
			break;
		default:
			return false;
		}
		return true;
	}

	private void callSaveDialog() {

		LayoutInflater inflater = this.getLayoutInflater();
		View root = inflater.inflate(R.layout.savedialog, null);

		final EditText editFileName = (EditText) root
				.findViewById(R.id.edit_filename);
		editFileName.setText(curFileName);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(root);
		builder.setTitle(R.string.title_save);

		builder.setPositiveButton(R.string.btn_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						saveFile(editFileName.getText().toString());
					}
				});

		builder.setNegativeButton(R.string.btn_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});

		builder.show();
	}

	private void callOpenDialog() {

		try {
			final String[] files = findFiles(dir);
			if (files.length > 0) {
				pos = 0;
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(R.string.title_open);
				builder.setSingleChoiceItems(files, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								pos = item;
							}
						});

				builder.setPositiveButton(R.string.btn_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								curFileName = files[pos];
								openFile(curFileName);
							}
						});

				builder.setNegativeButton(R.string.btn_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				builder.setCancelable(false);
				builder.show();
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

	}

	private void saveFile(String fileName) {
		try {
			if (!fileName.endsWith(FILE_EXT)) {
				fileName += FILE_EXT;
			}
			File file = new File(dir, fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(editText.getText().toString().getBytes());
			fos.close();
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private void openFile(String fileName) {

		try {
			File file = new File(dir, fileName);
			FileInputStream inStream = new FileInputStream(file);

			if (inStream != null) {
				InputStreamReader tmp = new InputStreamReader(inStream);
				BufferedReader reader = new BufferedReader(tmp);
				String str;
				StringBuffer buffer = new StringBuffer();

				while ((str = reader.readLine()) != null) {
					buffer.append(str + "\n");
				}

				inStream.close();
				editText.setText(buffer.toString());

				curFileName = fileName;
				this.setTitle(getResources().getString(R.string.app_name)
						+ ": " + curFileName);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	private String[] findFiles(String dirPath) {
		ArrayList<String> items = new ArrayList<String>();
		try {

			File f = new File(dirPath);
			File[] files = f.listFiles();

			for (int i = 0; i < files.length; i++) {
				File file = files[i];

				if (!file.isDirectory()) {
					items.add(file.getName());
				}
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		return items.toArray(new String[items.size()]);
	}
}