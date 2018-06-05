package towa.task_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    //                                                      //tag for Log.d() method.
    final static String tag = "LGF";
    BroadcastReceiver showTaskReceiver = new ShowTaskReceiver();
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //                                                      //Called when the activity is first created.
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "The onCreate() event");
        //                                                  //To register a receiver of the broadcast
        Log.d(tag, "Registrando....");

        IntentFilter intentFilter = new IntentFilter("com.LGF.CUSTOM_INTENT.TasksReady");
        this.registerReceiver(this.showTaskReceiver, intentFilter);
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
        //                                                  //Asking to the DB for data.
        TaskDB taskDBInstance = TaskDB.getTaskDB(getApplicationContext());
        DBUtil.DBGetAllTask(taskDBInstance, getApplicationContext());
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onPause()
    //                                                      //Called when another activity is taking focus.
    {
        super.onPause();
        Log.d(tag, "The onPause() event");

        //                                                  //To unregister a receiver of the broadcast
        Log.d(tag, "Unregistrando....");
        this.unregisterReceiver(this.showTaskReceiver);
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
        //                                                  //Destroy de DB INSTANCE.
        TaskDB.destroyInstance();
    }

    //------------------------------------------------------------------------------------------------------------------

    public void ShowNewTaskForm(View view)
    //                                                      //onClick-ButtonNewTask (New button)
    {
        //                                                  //To verify the click get the method.
        Log.d(tag, "click ButtonNewTask");

        //                                                  //Explicit Intent to start NewTaskFormActivity.
        Intent intent = new Intent(getApplicationContext(), NewTaskFormActivity.class);
        startActivity(intent);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void ShowAllTasks(View view)
    //                                                      //onClick-ButtonShowAll (Show All button)
    {
        //                                                  //To verify the click get the method.
        Log.d(tag, "click ButtonShowAll");

        //                                                  //Explicit Intent to start TaskListActivity.
    }

    //------------------------------------------------------------------------------------------------------------------
    private class ShowTaskReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //                                              //When com.LGF.CUSTOM_INTENT.TasksReady occurs

            List<Task> listOfTask = DBUtil.getTasks();
            for (Task task: listOfTask){
                Log.d("LGF - Tasks ", task.getShortDescription() + ", " +
                        String.valueOf(task.getPercentage()));
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
/*END-ACTIVITY*/