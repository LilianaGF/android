package towa.task_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    //                                                      //tag for Log.d() method.
    final static String tag = "LGF";
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //                                                      //Called when the activity is first created.
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "The onCreate() event");
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onStart()
    //                                                      //Called when the activity is about to become visible.
    {
        super.onStart();
        Log.d(tag, "The onStart() event");
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onResume()
    //                                                      //Called when the activity has become visible.
    {
        super.onResume();
        Log.d(tag, "The onResume() event");
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onPause()
    //                                                      //Called when another activity is taking focus.
    {
        super.onPause();
        Log.d(tag, "The onPause() event");
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onStop()
    //                                                      //Called when the activity is no longer visible.
    {
        super.onStop();
        Log.d(tag, "The onStop() event");
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void onDestroy()
    //                                                      //Called just before the activity is destroyed.
    {
        super.onDestroy();
        Log.d(tag, "The onDestroy() event");
    }
    //------------------------------------------------------------------------------------------------------------------
}
/*END-ACTIVITY*/