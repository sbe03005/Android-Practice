package cse.mobile.customtoasttest;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 액티비티 안의 findviewById

        Button buttonOK = findViewById(R.id.button);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout rootView = (LinearLayout) View.inflate(getApplicationContext(), R.layout.mytoast, null); // this 를 줘버리면 OnClickListener()를 의미하게 됨
                TextView tvMessage = rootView.findViewById(R.id.textViewMessage); // 레이아웃안에 뷰에 대한 파인트뷰바이아이디 범위 한정!!!!!!
                tvMessage.setText("Button clicked!");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(rootView); // 셋뷰가 뷰만 받게 되어있어서 그전에 위와같은 작업을 해야함 혹은 xml에 ㅏㅁㄴ들어놓은걸 수동으로 inflattion을 걸어줘야함
                toast.show();
            }
        });
    }
}
