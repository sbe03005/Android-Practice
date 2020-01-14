package cse.mobile.radiobuttontest;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RadioButton radioButton_red = findViewById(R.id.radio_red);
//        RadioButton radioButton_green = findViewById(R.id.radio_green);
//        RadioButton radioButton_blue = findViewById(R.id.radio_blue);
//
//        radioButton_red.setOnClickListener(mRadioButtonClickListener);
//        radioButton_green.setOnClickListener(mRadioButtonClickListener);
//        radioButton_blue.setOnClickListener(mRadioButtonClickListener);

        // 그룹으로도 때릴수있음! 상태가 바뀌는 걸로 하려면 이거로 해야됨 그룹으로
        RadioGroup radioGroup_color = findViewById(R.id.radioGroup_color);

        radioGroup_color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int checkedId) {
                RadioButton rg_color = radiogroup.findViewById(checkedId); // 여기서 파인트뷰 걸면 여기 안에서 찾는거임

                switch (checkedId) {
                    case R.id.radio_red:
                        Toast.makeText(getApplicationContext(), rg_color.getText() + "빨간색 by Group", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_green:
                        Toast.makeText(getApplicationContext(), rg_color.getText() + "초록색 by Group", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_blue:
                        Toast.makeText(getApplicationContext(), rg_color.getText() + "파란색 by Group", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    View.OnClickListener mRadioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean checked = ((RadioButton) v).isChecked();

            switch (v.getId()) {
                case R.id.radio_red:
                    if (checked) {
                        Toast.makeText(MainActivity.this, "빨간색", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.radio_green:
                    if (checked) {
                        Toast.makeText(MainActivity.this, "초록색", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.radio_blue:
                    if (checked) {
                        Toast.makeText(MainActivity.this, "파란색", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
