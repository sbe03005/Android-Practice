package cse.mobile.buttonevent3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(mButtononClickListener);

        Button button2 = findViewById(R.id.button);

    }

    View.OnClickListener mButtononClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "anonymous class & one named object", Toast.LENGTH_SHORT).show();
        }
    };
}
