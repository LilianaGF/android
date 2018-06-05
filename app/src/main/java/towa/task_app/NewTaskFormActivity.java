package towa.task_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class NewTaskFormActivity extends AppCompatActivity {
    //                                                      //tag for Log.d() method.
    final static String tag = "LGF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);

        initiateTheSeekbarPercentage();
        addListenerToTheSeekbarPercentage();

        initiateTheSwitchDone();
        addListenerToTheSwitchDone();
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void initiateTheSeekbarPercentage(){
        SeekBar seekbarPercentage = findViewById(R.id.SeekBarPercentage);

        seekbarPercentage.setMax(100);

        int defaultTaskPercentage = 0;
        seekbarPercentage.setProgress(defaultTaskPercentage);
        defaultTaskPercentage = seekbarPercentage.getProgress();
        Log.d(tag, "Progress" + defaultTaskPercentage);

        TextView textViewPercentage = findViewById(R.id.TextViewPercentage);
        String strDefaultTaskPercentage = defaultTaskPercentage + "%";
        textViewPercentage.setText(strDefaultTaskPercentage);
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void addListenerToTheSeekbarPercentage(){
        SeekBar seekbarPercentage = findViewById(R.id.SeekBarPercentage);

        seekbarPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}

            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(tag, "Percentage changed: " + progressChangedValue);

                Switch switchDone = findViewById(R.id.SwitchDone);
                TextView textViewPercentage = findViewById(R.id.TextViewPercentage);
                if (switchDone.isChecked()){
                    textViewPercentage.setText("100%");
                }
                else{
                    String strTaskPercentage = progressChangedValue + "%";
                    textViewPercentage.setText(strTaskPercentage);
                }
            }
        });
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void initiateTheSwitchDone(){
        Switch switchDone = findViewById(R.id.SwitchDone);
        if (switchDone.isChecked()){
            TextView textViewPercentage = findViewById(R.id.TextViewPercentage);
            textViewPercentage.setText("100%");
        }
    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void addListenerToTheSwitchDone(){
        Switch switchDone = findViewById(R.id.SwitchDone);

        switchDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView textViewPercentage = findViewById(R.id.TextViewPercentage);
                SeekBar seekbarPercentage = findViewById(R.id.SeekBarPercentage);
                if(isChecked){

                    textViewPercentage.setText("100%");
                }
                else {
                    int TaskPercentage = seekbarPercentage.getProgress();
                    String strTaskPercentage = TaskPercentage + "%";
                    textViewPercentage.setText(strTaskPercentage);
                }
            }
        });
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