package cse.mobile.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(mButtononClickListener);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Last", Toast.LENGTH_SHORT).show();
            }
        });
//        ButtonClickListener buttonClickListener = new ButtonClickListener();
//        button.setOnClickListener(buttonClickListener);
    }

    class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener mButtononClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Button Clicked Anounymous", Toast.LENGTH_SHORT).show();
        }
    };
}
