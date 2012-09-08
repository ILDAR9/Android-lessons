package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LayoutsTest extends Activity implements OnClickListener
{
	Button webViewButton;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.lineartest);
		
		webViewButton = (Button)findViewById(R.id.lButtom);
		webViewButton.setOnClickListener(this);
		
	}

	public void onClick(View v) 
	{
		if(v.getId() == webViewButton.getId())
		{
			startActivity(new Intent(this, WebViewTest.class));
		}
	}
	
	
}
