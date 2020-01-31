package cse.mobile.threadtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "ThreadTest";
    static final int MSG_CODE_THCOUNTER = 0;

    boolean mRunning = false;
    TextView mTVCount;

    // 메시지가 들어올때 어떻게 핸들링 할거냐
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CODE_THCOUNTER:
                    mTVCount.setText("[" + Thread.currentThread().getName() + "] Count : " + msg.arg1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Thread thCounter = new Thread(new CounterThread());
//        mRunning = true; // 언제 로직을 넣는다. 언제 돌릴거냐 이걸 ㅎ선태갛면된다.
//        thCounter.start();

        mTVCount = findViewById(R.id.tvCount);
    }

    private class CounterThread implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10 && mRunning; i++) {
                Log.i(TAG, "[" + Thread.currentThread().getName() + "] Count : " + i);
                // 안된다.
//                mTVCount.setText("[" + Thread.currentThread().getName() + "] Count : " + i);

                // 1. post 방법
//                final int finalI = i;
//                mTVCount.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTVCount.setText("[" + Thread.currentThread().getName() + "] Count : " + finalI);
//                    }
//                });

                // 2. 메시지 방법
                Message msg = new Message();
                msg.arg1 = i;
                msg.what = MSG_CODE_THCOUNTER;
                mHandler.sendMessage(msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 근데이거 너무 복잡하다 그래서 안드에서 만들어준 어싱크태스크
                // 여태우리는 쓰레드만들고 카운팅하고 위젯에 찍으려고 다 따로 했었다..
                // 근데 어싱크타스크 쓰면 간단하게 할수있따.
                
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        mRunning = false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        Thread thCounter = new Thread(new CounterThread());
        mRunning = true; // 언제 로직을 넣는다. 언제 돌릴거냐 이걸 ㅎ선태갛면된다.
        thCounter.start();
    }
}
