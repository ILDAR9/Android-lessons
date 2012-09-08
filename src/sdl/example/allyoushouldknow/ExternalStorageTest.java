package sdl.example.allyoushouldknow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class ExternalStorageTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		
		String state = Environment.getExternalStorageState();
		if(!state.equals(Environment.MEDIA_MOUNTED))
		{
			textView.setText("No external storage mounted!");
		} else
		{
			File exDir = Environment.getExternalStorageDirectory();
			File textFile = new File(exDir.getAbsolutePath() + File.separator + "ExternalStorageTest.txt");
			try
			{
				writeTextFile(textFile, "This is a test. Alexander");
				textView.setText(readTextFile(textFile));
			} catch (IOException ioe)
			{
				textView.setText("Somethong went wrong! " + ioe.getMessage());
			}
		}
	}
	
	
	private void writeTextFile(File file, String text) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(text);
		bw.close();
	}
	
	private String readTextFile(File file) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder builder = new StringBuilder();
		String line;
		while( (line = br.readLine()) != null )
		{
			builder.append(line + "\n");
		}
		br.close();
		return builder.toString();
	}
}
