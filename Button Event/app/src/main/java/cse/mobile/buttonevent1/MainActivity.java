package cse.mobile.buttonevent1;

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
        Button button =(Button)findViewById(R.id.button);

        ButtonClickListener buttonClickListener = new ButtonClickListener();
        button.setOnClickListener(buttonClickListener);
    }

    class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "inner class & one named object", Toast.LENGTH_SHORT).show();
        }
    }
}
