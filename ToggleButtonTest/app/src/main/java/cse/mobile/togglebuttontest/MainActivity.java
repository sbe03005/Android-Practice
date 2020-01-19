package cse.mobile.togglebuttontest;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton toggleButtonDecision = findViewById(R.id.toggleButtonDecision);
        toggleButtonDecision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean checked = ((ToggleButton)v).isChecked();

                if(checked){
                    Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Switch switchWiFi = findViewById(R.id.switchWiFi);

        switchWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { // 아예 불린이 들어와서 checked가 들어옴 훨씬 수월
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
