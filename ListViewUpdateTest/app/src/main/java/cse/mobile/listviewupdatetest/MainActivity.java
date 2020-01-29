package cse.mobile.listviewupdatetest;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mALItems;
    ArrayAdapter<String> mAdapter;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cmenu_activity_main_lvitems, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { // 어디가 눌린건지 알수 없다 이것만으로
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); // 다운 캐스팅 걸어서..

        switch (item.getItemId()) {
            case R.id.cmenuDelete:
                mAdapter.remove(mAdapter.getItem(info.position));
                // mALItems.remove(info.position); // info에 포지션 들어옴 사실 item에 들어있음
                mAdapter.notifyDataSetChanged();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mALItems = savedInstanceState.getStringArrayList("mALItems");
        } else {
            mALItems = new ArrayList<String>();
        }

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
            }
        });

        registerForContextMenu(lvItems);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("mALItems", mALItems);
    }
}

/* OnLongClickListner 안되네?
 * OnItemLongClickLister 여기다 하면 되긴함
 * 근데 안들어옴 값이
 * setMulitichoiceModeListener
 * itemlong클릭하면 뜨긴뜸
 * 액션 id하면 여러개 해보자
 * onActionitem여기엔 info 해도 null로 들어오니 멤버변수잡아서 해보자.*/
