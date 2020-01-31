package cse.mobile.listviewactionmodetest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mALItems;
    ArrayAdapter<String> mAdapter;
    ListView mLVItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mALItems = new ArrayList<String>();
        mLVItems = findViewById(R.id.lvItems);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mALItems);

        mLVItems.setAdapter(mAdapter);

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

        mLVItems.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mLVItems.setMultiChoiceModeListener(mMultiChoiceModeListener);
    }

    /* MultiChoiceModeListener */
    AbsListView.MultiChoiceModeListener mMultiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {
        int mPosition;

        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.cmenu_activity_main_lvitems, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cmenuDelete:
                    mAdapter.remove(mAdapter.getItem(mPosition));
                    mAdapter.notifyDataSetChanged();
                    mode.finish();
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {
        }

        @Override
        public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
            Toast.makeText(getApplicationContext(), position + " : " + checked, Toast.LENGTH_SHORT).show();
            mPosition = position;
        }
    };
}
