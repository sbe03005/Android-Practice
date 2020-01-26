package cse.mobile.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final int REQ_CODE_SUBACTIVITY_0 = 0;
    TextView mTVMainReturn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data); // reqeustCode는 밑에서 보낸 REQ이게 들어오는거임

        switch (requestCode) {
            case REQ_CODE_SUBACTIVITY_0:
                if (resultCode == RESULT_OK) {
                    mTVMainReturn.setText(data.getStringExtra("subInput"));
                } else {
                    mTVMainReturn.setText("Cancel 눌림!");
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main2);

        mTVMainReturn = findViewById(R.id.tvMainReturn);

        Button btMainCall = findViewById(R.id.btMainCall);
        btMainCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sintent = new Intent(getApplicationContext(), SubActivity.class);
//                startActivity(sintent);

                // 주는 법
                sintent.putExtra("mainReturnStr", mTVMainReturn.getText().toString());

                // 받는 법
                startActivityForResult(sintent, REQ_CODE_SUBACTIVITY_0); // 두번째 인자는 어떤 액티비티에서 오는지 알아야함. 스위치 케이스로 하믄댐

            }
        });
    }
}
