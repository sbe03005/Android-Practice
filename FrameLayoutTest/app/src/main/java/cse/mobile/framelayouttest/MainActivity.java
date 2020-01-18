package cse.mobile.framelayouttest;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textViewRed = findViewById(R.id.textViewRed);
        final TextView textViewGreen = findViewById(R.id.textViewGreen);
        final TextView textViewBlue = findViewById(R.id.textViewBlue);

        RadioGroup radioGroup_color = findViewById(R.id.radioGroup_color);

        radioGroup_color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int checkedId) {
                RadioButton rg_color = radiogroup.findViewById(checkedId); // 여기서 파인트뷰 걸면 여기 안에서 찾는거임

                    switch (checkedId) {
                        case R.id.radio_red:
                            Toast.makeText(getApplicationContext(), rg_color.getText() + "빨간색 by Group", Toast.LENGTH_SHORT).show();
                            textViewRed.setVisibility(View.VISIBLE);
                            textViewGreen.setVisibility(View.INVISIBLE);
                            textViewBlue.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.radio_green:
                            Toast.makeText(getApplicationContext(), rg_color.getText() + "초록색 by Group", Toast.LENGTH_SHORT).show();
                            textViewRed.setVisibility(View.INVISIBLE);
                            textViewGreen.setVisibility(View.VISIBLE);
                            textViewBlue.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.radio_blue:
                            Toast.makeText(getApplicationContext(), rg_color.getText() + "파란색 by Group", Toast.LENGTH_SHORT).show();
                            textViewRed.setVisibility(View.INVISIBLE);
                            textViewGreen.setVisibility(View.INVISIBLE);
                            textViewBlue.setVisibility(View.VISIBLE);
                            break;
                }
            }
        });
    };
}
