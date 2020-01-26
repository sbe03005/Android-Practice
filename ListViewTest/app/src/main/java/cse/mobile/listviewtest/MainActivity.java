package cse.mobile.listviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String[] FRUITS = {"Apple", "Banana", "Cherry", "Durian"}; // 개별 데이터이므로 리소스로 빼거나 DB로 빼거나. 우리는 리소스로

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FRUITS); // android.R에서 지원
//        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fruits_res, android.R.layout.simple_list_item_1); // 순서 뒤바뀜
//        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fruits_res, R.layout.mytextview);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mylayout, FRUITS);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mylayout, R.id.tvFruits, FRUITS);


        ListView lvFruits = findViewById(R.id.lvFruit);
        lvFruits.setAdapter(adapter);


        lvFruits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // position과 id는 같다. 인덱스의 의미로써
                /* 1번 */
//                Toast.makeText(MainActivity.this, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                /* 2번 (좋은 방법은 아님) 모듈화가 해논 상태인데 DATA를 직접 접근하는건 별로 좋진 않다. */
//                Toast.makeText(MainActivity.this, FRUITS[position], Toast.LENGTH_SHORT).show();

                /* 3번 */
//                Toast.makeText(MainActivity.this, adapter.getItem(position), Toast.LENGTH_SHORT).show();

                /* 4번 */
//                String item = (String) parent.getItemAtPosition(position);
//                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();

                /* array 리소스를 통해 할때 */

                /* 5번 (좋은 방법은 아님) 모듈화가 해논 상태인데 DATA를 직접 접근하는건 별로 좋진 않다. */
//                String[] resFruits = getResources().getStringArray(R.array.fruits_res);
//                Toast.makeText(MainActivity.this, resFruits[position], Toast.LENGTH_SHORT).show();

                /* 6번 */
//                Toast.makeText(MainActivity.this, adapter.getItem(position), Toast.LENGTH_SHORT).show();

                /* 레이아웃 안의 textview 가져오기 */
                TextView tvFruits = view.findViewById(R.id.tvFruits); // view. 안하면 요 액티비티안에서 가져오는 꼴이 됨
                Toast.makeText(getApplicationContext(), tvFruits.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
