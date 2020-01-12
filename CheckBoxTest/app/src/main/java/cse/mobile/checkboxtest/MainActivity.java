package cse.mobile.checkboxtest;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBox_meat = findViewById(R.id.checkbox_meat);
        CheckBox checkBox_cheeee = findViewById(R.id.checkbox_cheese);

//        checkBox_meat.setOnClickListener(new View.OnClickListener() { // 이거를
//            @Override
//            public void onClick(View view) { // 업케스팅안하면 온클리에 모든걸 다 오버로딩해야댐
//                boolean checked = ((CheckBox)view).isChecked();
//
//                if(checked){
//                    Toast.makeText(MainActivity.this, "고기 눌림", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "고기 취소", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        checkBox_cheeee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean checked = ((CheckBox)view).isChecked();
//
//                if(checked){
//                    Toast.makeText(MainActivity.this, "치즈 눌림", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "치즈 취소", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        checkBox_meat.setOnClickListener(mCheckBoxClickListener);
        checkBox_cheeee.setOnClickListener(mCheckBoxClickListener);
    }

    // 근데 모양이 비슷하니까 one object로 들어갈수 있음 객체 하나 만들어서 함 시험나옴~!~~!~!~!~!~!
    View.OnClickListener mCheckBoxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox) view).isChecked();

            switch (view.getId()) {
                case R.id.checkbox_meat:
                    if (checked) {
                        Toast.makeText(MainActivity.this, "고기 눌림", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "고기 취소", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.checkbox_cheese:
                    if (checked) {
                        Toast.makeText(MainActivity.this, "치즈 눌림", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "치즈 취소", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
