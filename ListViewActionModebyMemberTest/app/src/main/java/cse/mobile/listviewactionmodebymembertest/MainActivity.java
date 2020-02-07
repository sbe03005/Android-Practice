package cse.mobile.listviewactionmodebymembertest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mALItems;
    ArrayAdapter<String> mAdapter;
    ActionMode mActionMode = null;
    int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mALItems = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mALItems);

        ListView lvItems = findViewById(R.id.lvItems);
        lvItems.setAdapter(mAdapter);

        final EditText etAdd = findViewById(R.id.etAdd);
        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mALItems.add(etAdd.getText().toString());
                etAdd.setText("");
                mAdapter.notifyDataSetChanged(); // 데이터를 다시 읽어옴
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
                mPosition = position;
            }
        });

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mActionMode != null) {
                    mActionMode.finish();
                    return false; // 중첩을 막기위함 null일땐 넘어가고.. 액션모드가 모달로 동작하지 않기 때문 온클릭 호출됨
                }

                mPosition = position;
                mActionMode = MainActivity.this.startSupportActionMode(mActionModeCallback);

                view.setSelected(true);
                return true; // 클릭이 호출되지 않겠다.
            }
        });
    }

    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.cmenu_activity_main_lvitems, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cmenuDelete:
                    mALItems.remove(mPosition); // info에 포지션 들어옴 사실 item에 들어있음
//                    mAdapter.remove(mAdapter.getItem(mPosition));
                    mAdapter.notifyDataSetChanged();
                    mActionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
}

/* OnLongClickListner 안되네?
 * OnItemLongClickLister 여기다 하면 되긴함
 * 근데 안들어옴 값이
 * setMulitichoiceModeListener
 * itemlong클릭하면 뜨긴뜸
 * 액션 id하면 여러개 해보자
 * onActionitem여기엔 info 해도 null로 들어오니 멤버변수잡아서 해보자.*/
