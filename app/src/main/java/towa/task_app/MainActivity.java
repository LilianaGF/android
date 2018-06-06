package towa.task_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    //                                                      //tag for Log.d() method.
    final static String tag = "LGF";
    BroadcastReceiver showTaskReceiver = new ShowTaskReceiver();
    BroadcastReceiver updateTaskCountReceiver = new UpdateTaskCountReceiver();




    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    //                                                      //Called when the activity is first created.
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "The onCreate() event");

        /*
        //                                                  //To register receivers of the broadcast
        Log.d(tag, "Registrando....");

        IntentFilter intentTaskReady = new IntentFilter("com.LGF.CUSTOM_INTENT.TasksReady");
        this.registerReceiver(this.showTaskReceiver, intentTaskReady);

        IntentFilter intentTaskCountReady = new IntentFilter("com.LGF.CUSTOM_INTENT.TasksCountReady");
        this.registerReceiver(updateTaskCountReceiver, intentTaskCountReady);
        */

    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onStart()
    //                                                      //Called when the activity is about to become visible.
    {
        super.onStart();
        Log.d(tag, "The onStart() event");
        //                                                  //To register receivers of the broadcast
        Log.d(tag, "Registrando....");

        IntentFilter intentTaskReady = new IntentFilter("com.LGF.CUSTOM_INTENT.TasksReady");
        this.registerReceiver(this.showTaskReceiver, intentTaskReady);

        IntentFilter intentTaskCountReady = new IntentFilter("com.LGF.CUSTOM_INTENT.TasksCountReady");
        this.registerReceiver(updateTaskCountReceiver, intentTaskCountReady);





    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onResume()
    //                                                      //Called when the activity has become visible.
    {
        super.onResume();
        Log.d(tag, "The onResume() event");

        TaskDB taskDBInstance = TaskDB.getTaskDB(getApplicationContext());
        //                                                  //Asking to the DB for data.
        DBUtil.DBGetAllTask(taskDBInstance, getApplicationContext());
        //                                                  //Asking to the DB to count.
        DBUtil.DBTaskCount(taskDBInstance, getApplicationContext());
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
        this.unregisterReceiver(this.updateTaskCountReceiver);
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
        Intent intent = new Intent(getApplicationContext(), TaskListActivity.class);
        startActivity(intent);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void FinishApp(View view)
    //                                                      //onClick-toolbar (Toolbar back button)
    {
        Log.d(tag, "Bye Li...");
        finish();
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
    private class UpdateTaskCountReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //                                              //When com.LGF.CUSTOM_INTENT.ToDoTaskCountReady occurs

            int intToDoTask = DBUtil.getToDoTaskCount();
            TextView textViewTaskToDo = findViewById(R.id.TextViewTaskToDoCount);
            textViewTaskToDo.setText(String.valueOf(intToDoTask) + " Task To Do");

            int intDoingTask = DBUtil.getDoingTaskCount();
            TextView textViewTaskDoing = findViewById(R.id.TextViewTaskDoingCount);
            textViewTaskDoing.setText(String.valueOf(intDoingTask) + " Task Doing");

            int intDoneTask = DBUtil.getDoneTaskCount();
            TextView textViewTaskDone = findViewById(R.id.TextViewTaskDoneCount);
            textViewTaskDone.setText(String.valueOf(intDoneTask) + " Task Done");
        }
    }
    //------------------------------------------------------------------------------------------------------------------

}
/*END-ACTIVITY*/