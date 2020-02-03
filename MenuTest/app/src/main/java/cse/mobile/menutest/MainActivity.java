package cse.mobile.menutest;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode; // 이렇게도 가능 옛날것 스태틱하게 라이브러리를 심는 것임 이건 용량 커지겠지

//import android.view.ActionMode; 원랜 이거!

public class MainActivity extends AppCompatActivity {

    ActionMode mActionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        registerForContextMenu(button);

        TextView textView = findViewById(R.id.textViewHello);
        // registerForContextMenu(textView);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false; // 중첩을 막기위함 null일땐 넘어가고.. 액션모드가 모달로 동작하지 않기 때문 온클릭 호출됨
                }
//                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
                mActionMode = MainActivity.this.startSupportActionMode(mActionModeCallback);

                v.setSelected(true);
                return true; // 클릭이 호출되지 않겠다.
            }
        });
    }

    // 2.4미만 버튼누를때, 3.0이상 액티비ㅣㅌ 만들때
    @Override
    // 딱한번 실행 근데 메뉴 내용이 달라지고 싶을떈? 얘는 한번밖에 실행이안되서 onPrepareOptionsMenu <- 매번 실행됨 얘는 2.4버튼누를때마다 매번 실행
    public boolean onCreateOptionsMenu(Menu menu) { // 메뉴전체가 파라미터로
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }
    // invalidateMenu 호출시 onPrepareOptionsMenu() 가 실행댐 3.0이상 부터는 버튼이 없음! 이 메서드를 제공해서 onPrepare에 동적으로 만들어넣고

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 어느게 눌렸는지 파라미터로
        switch (item.getItemId()) {
            case R.id.menuNew:
                Toast.makeText(this, "Options Menu New", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuOpen:
                Toast.makeText(this, "Options Menu Open", Toast.LENGTH_SHORT).show();
                return true;
            default: // 처리가 안된 것은 디폴트로 처리
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        //        inflater.inflate(R.menu.cmenu_activity_main_button, menu);
        //        inflater.inflate(R.menu.cmenu_activity_main_textview, menu);

        int cmenuResId;
        switch (v.getId()) {
            case R.id.button:
                cmenuResId = R.menu.cmenu_activity_main_button;
                break;
            case R.id.textViewHello:
                cmenuResId = R.menu.cmenu_activity_main_textview;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        inflater.inflate(cmenuResId, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cmenuCreate:
                Toast.makeText(this, "Context Menu Create", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cmenuDelete:
                Toast.makeText(this, "Context Menu Delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cmenuCopy:
                Toast.makeText(this, "Context Menu Copy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cmenuPaste:
                Toast.makeText(this, "Context Menu Paste", Toast.LENGTH_SHORT).show();
                return true;
            default: // 처리가 안된 것은 디폴트로 처리
                return super.onContextItemSelected(item);
        }
    }

    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.cmenu_activity_main_textview, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cmenuCreate:
                    Toast.makeText(getApplicationContext(), "Context Menu Create", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.cmenuDelete:
                    Toast.makeText(getApplicationContext(), "Context Menu Delete", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.cmenuCopy:
                    Toast.makeText(getApplicationContext(), "Context Menu Copy", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.cmenuPaste:
                    Toast.makeText(getApplicationContext(), "Context Menu Paste", Toast.LENGTH_SHORT).show();
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
