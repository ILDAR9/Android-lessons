package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteTest extends Activity implements OnClickListener {
	Button btnAdd, btnRead, btnClear, btnUpd, btnDel;
	EditText etName, etEmail, etID;

	TextView textView;

	DBHelper dbHelper;

	StringBuilder sb;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlitetest);

		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(this);

		btnRead = (Button) findViewById(R.id.btnRead);
		btnRead.setOnClickListener(this);

		btnUpd = (Button) findViewById(R.id.btnUpd);
		btnUpd.setOnClickListener(this);

		btnDel = (Button) findViewById(R.id.btnDel);
		btnDel.setOnClickListener(this);

		btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(this);

		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etID = (EditText) findViewById(R.id.etID);

		textView = (TextView) findViewById(R.id.text_sql);

		sb = new StringBuilder();

		dbHelper = new DBHelper(this);
	}

	private boolean isNumber(String id) {
		if (id.equalsIgnoreCase("")) {
			Toast.makeText(getBaseContext(), R.string.no_id, Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		for (char x : id.toCharArray()) {
			if (!(x > '0' && x < '9')) {
				Toast.makeText(getBaseContext(), R.string.id_not_number,
						Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		return true;
	}

	public void onClick(View v) {

		ContentValues cv = new ContentValues();

		String name = etName.getText().toString();
		String email = etEmail.getText().toString();
		String id = etID.getText().toString();

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		switch (v.getId()) {
		case R.id.btnAdd: {
			cv.put("name", name);
			cv.put("email", email);
			long rowID = db.insert("mytable", null, cv);
			textView.setText("row inserted, ID = " + rowID);
			etName.setText("");
			etEmail.setText("");
			break;
		}
		case R.id.btnRead: {
			Cursor cursor = db.query("mytable", null, null, null, null, null,
					null);

			if (cursor.moveToFirst()) {

				int idColIndex = cursor.getColumnIndex("id");
				int nameColIndex = cursor.getColumnIndex("name");
				int emailColIndex = cursor.getColumnIndex("email");
				sb.setLength(0);
				do {
					sb.append("ID = " + cursor.getInt(idColIndex)
							+ ",\nname = " + cursor.getString(nameColIndex)
							+ ",\nemail = " + cursor.getString(emailColIndex)
							+ "\n---------------------------------\n");

				} while (cursor.moveToNext());
				textView.setText(sb.toString());
			} else {
				textView.setText("0 rows");
			}			
			break;
		}
		case R.id.btnClear: {
			int clearCount = db.delete("mytable", null, null);
			textView.setText("deleted rows count = " + clearCount);
			break;
		}
		case R.id.btnUpd:
			if (!isNumber(id)) {
				break;
			}

			cv.put("name", name);
			cv.put("email", email);
			int updCount = db.update("mytable", cv, "id = " + id, null);
			textView.setText("updated rows count = " + updCount);
			break;
		case R.id.btnDel: {
			if (!isNumber(id)) {
				break;
			}
			if (id.equalsIgnoreCase("")) {
				Toast.makeText(getBaseContext(), R.string.no_id,
						Toast.LENGTH_SHORT).show();
				break;
			}
			int delCount = db.delete("mytable", "id = " + id, null);
			textView.setText("deleted rows count = " + delCount);
			break;
		}
		}
		dbHelper.close();

	}

	class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, "myDB", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("create table mytable ("
					+ "id integer primary key autoincrement,"
					+ "name text not null," + "email text not null" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}
}