package sdl.example.allyoushouldknow;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		WebView webView = new WebView(this);
		
		setContentView(webView);
		
		webView.loadUrl("http://bash.im");
	}
}
