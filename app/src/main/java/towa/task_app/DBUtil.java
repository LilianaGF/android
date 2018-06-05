package towa.task_app;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    static List<Task> tasks = new ArrayList<>();
    public static List<Task> getTasks() {
        return tasks;
    }

    public static List<Task> DBGetAllTask(TaskDB taskDBInstance, Context context){
        GetAllTask getAllTask = new GetAllTask(taskDBInstance, context);
        getAllTask.execute();
        return tasks;
    }

    public static void DBSaveNewTask(TaskDB taskDBInstance, Task task){
        SaveNewTask saveNewTask = new SaveNewTask(taskDBInstance, task);
        saveNewTask.execute();
    }

    //-----------------------------------------------------------------------------------------------------------------
    private static class GetAllTask extends AsyncTask<Void, Void, Void> {
        TaskDB taskDBInstance;
        Context context;

        public GetAllTask(TaskDB taskDBInstance, Context context) {
            this.taskDBInstance = taskDBInstance;
            this.context = context;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            tasks = taskDBInstance.taskDAO().getAll();
            Log.d("LGF ", tasks.size() + " Tasks in DB");
            return null;
        }

        @Override
        protected void onPostExecute(Void v)
        {
            Log.d("LGF Broadcast ", "TasksReady");
            Intent intent = new Intent();
            intent.setAction("com.LGF.CUSTOM_INTENT.TasksReady");
            context.sendBroadcast(intent);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------
    private static class SaveNewTask extends AsyncTask<Void, Void, Void> {
        TaskDB taskDBInstance;
        Task task;

        public SaveNewTask(TaskDB taskDBInstance, Task task) {
            this.taskDBInstance = taskDBInstance;
            this.task = task;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            taskDBInstance.taskDAO().insertTask(task);
            Log.d("LGF ", "Saving new task ");
            return null;
        }
    }

    //-----------------------------------------------------------------------------------------------------------------

}
