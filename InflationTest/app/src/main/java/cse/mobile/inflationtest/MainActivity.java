package cse.mobile.inflationtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); // 이 아이는 밑에 애들같은걸 쭉 하고 있다는 것 아래 과정같은걸 조 메서드안에 다 넣음 이걸 인플레이트한다고한다. 인플레이션

        Button buttonOK = findViewById(R.id.button);
        buttonOK.setText("Yes");

        TextView textViewHello = new TextView(this);
        textViewHello.setText("Hello, Android!");

//        Button buttonOK = new Button(this);
//        buttonOK.setText("OK in code");

        LayoutInflater inflater = getLayoutInflater();
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater = LayoutInflater.from(this);

        Button buttonMyButton = (Button) View.inflate(this, R.layout.mybutton, null);

        Button myButton = (Button) inflater.inflate(R.layout.mybutton, null); // 최상위를 반환 new가 되는 것

        LinearLayout linearLayoutManager = new LinearLayout(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutManager.addView(textViewHello, llParams);
//        linearLayoutManager.addView(buttonOK, llParams);
//        linearLayoutManager.addView(buttonMyButton); // addView의 기본 속성이 height가 wrap_content이다. addView의 디폴트를 먹기 때문에 XMl에 아무리 속성줘도 의미없음 하지만 쓰지는않지만 넣어줘야댐 xml에
        linearLayoutManager.addView(buttonMyButton, llParams); // addView의 기본 속성이 height가 wrap_content이다. addView의 디폴트를 먹기 때문에 XMl에 아무리 속성줘도 의미없음 하지만 쓰지는않지만 넣어줘야댐 xml에
        linearLayoutManager.addView(myButton, llParams); // addView의 기본 속성이 height가 wrap_content이다. addView의 디폴트를 먹기 때문에 XMl에 아무리 속성줘도 의미없음 하지만 쓰지는않지만 넣어줘야댐 xml에
        setContentView(linearLayoutManager);
    }
}
