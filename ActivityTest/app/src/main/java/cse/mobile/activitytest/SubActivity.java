package cse.mobile.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_sub);
        setContentView(R.layout.activity_sub2);

//        Button btSubCall = findViewById(R.id.btSubCall);
//        btSubCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        }); // 매니패스트 추가해주면됨


        final EditText etSubInput = findViewById(R.id.etSubInput);

        // 메인에서 넘오올때 값이 있으면 받아오고 아니면 넘어가고 ..
        if (getIntent().getStringExtra("mainReturnStr") != null) {
            etSubInput.setHint(getIntent().getStringExtra("mainReturnStr"));
        }


        Button btSubOK = findViewById(R.id.btSubOK);
        btSubOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(); // main으로 돌아가는거라 어디로 갈지 명시할필요없다.
                sIntent.putExtra("subInput", etSubInput.getText().toString());
                setResult(RESULT_OK, sIntent);
                finish();
            }
        });

        Button btSubCancel = findViewById(R.id.btSubCancel);
        btSubCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED); // 아무것도 안함.
                finish();
            }
        });
    }
}
