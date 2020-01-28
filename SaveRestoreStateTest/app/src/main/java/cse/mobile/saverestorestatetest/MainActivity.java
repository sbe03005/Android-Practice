package cse.mobile.saverestorestatetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int mCount = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* 온세이브 인스턴트 디폴드 저장은 기본은 id가 있어야지 가능하다.
         * id가 없으면 호출하지 않는다.
         * 디폴트 구현이 현재 뷰 하이라이킬중 아이디가 있는애들의
         * 온세이브를 호출해주는건데 얘를 막으면 그 디폴트 작업을 못함 */

        outState.putInt("mCount", mCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("mCount");
        }

        final TextView tvOrder = findViewById(R.id.tvOrder);
        tvOrder.setText("Total amount of order: " + mCount);

        Button btIncrease = findViewById(R.id.btIncrease);
        btIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount++;
                tvOrder.setText("Total amount of order: " + mCount);
            }
        });

        Button btDecrease = findViewById(R.id.btDecrease);
        btDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (--mCount < 0) {
                    mCount = 0;
                }
                tvOrder.setText("Total amount of order: " + mCount);
            }
        });
    }
}
