package cse.mobile.asynctasktest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "ThreadTest";
    boolean mRunning = false;
    TextView mTVCount;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVCount = findViewById(R.id.tvCount);
        mProgressBar = findViewById(R.id.pbCount);
    }

    @Override
    protected void onStart() {
        super.onStart();

        CounterTask taskCounter = new CounterTask();
        mRunning = true;
        taskCounter.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mRunning = false;
    }

    private class CounterTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            mTVCount.setText("[" + Thread.currentThread().getName() + "] Count Last : " + result);
            mProgressBar.setProgress(result);
        }

        @Override
        protected void onProgressUpdate(Integer[] values) { // 여러개 받을 수 있따.
            super.onProgressUpdate(values);
            mTVCount.setText("[" + Thread.currentThread().getName() + "] Count : " + values[0]);
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Integer[] values) {
            int i = 0;
            for (i = 0; i < 10 && mRunning; i++) {
                Log.i(TAG, "[" + Thread.currentThread().getName() + "] Count : " + i);
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return i;
        }
    }
}
