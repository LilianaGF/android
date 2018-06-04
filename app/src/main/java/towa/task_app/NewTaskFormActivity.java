package towa.task_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class NewTaskFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void CancelNewTask(View view) {

        //                                                  //Back to main activity.
        android.content.Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void SaveNewTask(View view) {
        //                                                  //Back to main activity.
        android.content.Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
/*END-ACTIVITY*/