package sdl.example.allyoushouldknow;

import java.io.File;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllYouShouldKnow extends ListActivity 
{
	String tests [] = { "LifeCycleTest", "SingleTouchTest", "MultiTouchTest", "LayoutsTest",
						"KeyTest", "AccelerometerTest", "AssetsTest", 
						"ExternalStorageTest", "SoundPoolTest", "MediaPlayerTest", 
						"FullScreenTest", "LocationTest", "GoogleMapTest", "IntentTest", "RenderViewTest", "ShapeTest", "BitmapTest",
						"FontTest", "SurfaceViewTest" };
	String packageName = "sdl.example.allyoushouldknow.";
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main, R.id.tView, tests));
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id)
    {
    	super.onListItemClick(list, view, position, id);
    	String testName = tests[position];
    	try
    	{
    		Class cl = Class.forName(packageName + testName);
    		startActivity(new Intent(this, cl));
    	} catch(ClassNotFoundException cnfe)
    	{
    		cnfe.printStackTrace();
    	}
    	   	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
