package cse.mobile.userinterface3;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.button1);
        b1.setText("코드에서도 변경 가능");

        Button b2 = findViewById(R.id.button2);
        b2.setEnabled(false);
    }
}
