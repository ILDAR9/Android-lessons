package sdl.example.allyoushouldknow;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class AssetsTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(this);
		
		setContentView(textView);
		
		AssetManager aManager = getAssets();
		InputStream is = null;
		try
		{
			is = aManager.open("text/text.txt");
			String text = loadTextFile(is);
			textView.setText(text);
		} catch(IOException ioe)
		{
			ioe.printStackTrace();
			textView.setText("Couldn't load file");
		} finally
		{
			if( is != null)
			try
			{
					is.close();
			} catch(IOException ioe)
			{
				ioe.printStackTrace();
				textView.setText("Coludn't close file");
			}
		}
		
	}
	
	
	private String loadTextFile(InputStream is) throws IOException
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte bytes [] = new byte[4096];
		int length = 0;
		while((length = is.read(bytes)) > 0)
		{
			byteStream.write(bytes, 0, length);
		}
		return new String(byteStream.toByteArray(), "UTF-8");
	}
}