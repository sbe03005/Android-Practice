package cse.mobile.dialogtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {
    private static boolean[] checkedItems = {true, false, true};

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBooleanArray("checkedItems", checkedItems);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            checkedItems = savedInstanceState.getBooleanArray("checkedItems");
        }

        // 일반 인포메이션
        Button buttonDialog = findViewById(R.id.button);
        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder alertDialogBuiler = new AlertDialog.Builder(MainActivity.this);
//                alertDialogBuiler.setMessage("Time Out!");
//                alertDialogBuiler.setTitle("Notice");
//                alertDialogBuiler.setIcon(R.mipmap.ic_launcher);
//
//                // 가장 쉬운 방법 .show() 보여주고 버리고
//                alertDialogBuiler.show();

                // 체이닝
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Time Out!")
                        .setTitle("Notice")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("Close",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .setCancelable(false)
                        .show();

                // 책의 213페이지
            }
        });

        // 프래그먼트
        Button buttonDialogNoticeDialog = findViewById(R.id.buttonNoticeDialog);
        // 보여주는 것과 관리하는 것을 이원화 함
        buttonDialogNoticeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기서는 show만 걸어주는 것
                DialogFragment noticeDialogFragment = new NoticeDialogFragment();
                noticeDialogFragment.setCancelable(false);
                noticeDialogFragment.show(getSupportFragmentManager(), "noticeDialogFragment"); // 매니저가 관리할 수 있도록 이름을 준다.
            }
        });

        // 리스트
        Button buttonListDialog = findViewById(R.id.buttonListDialog);
        final DialogFragment listDialogFragment = new ListDialogFragment(); // 밖으로 빼도 초기화문제는 소용없음 onCreateDialog를 계속 호출하고 있기 때문에
        buttonListDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogFragment listDialogFragment = new ListDialogFragment();
                listDialogFragment.setCancelable(false);
                listDialogFragment.show(getSupportFragmentManager(), "listDialogFragment");
            }
        });

        // 리스트2
        Button buttonListDialog2 = findViewById(R.id.buttonListDialog2);
        final DialogFragment listDialogFragment2 = new ListDialogFragment2(); // 밖으로 빼도 초기화문제는 소용없음 onCreateDialog를 계속 호출하고 있기 때문에
        buttonListDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogFragment listDialogFragment = new ListDialogFragment();
                listDialogFragment2.setCancelable(false);
                listDialogFragment2.show(getSupportFragmentManager(), "listDialogFragment2"); // support라이브러리는 올드버전을 위한 호환을 위한 라이브러리다.
            }
        });

        /*
         * 프래그먼트로 하는건 관리를 해주길 원하는건 관리해야 할때 리스트 같은거
         * 그럴때 쓰는 게 DialogFragment인데
         * 설정을 하고나서 나중에 다시 키니까 초기값돌아오면 원래 목적과 맞지않다.
         * 다음에도 열때 내가 마지막으로 설정한 값들이 그대로 있길 원한다.
         *
         * 그럼 이걸 갱신할때마다 환경설정이다 그러면 직전에 설정이 업데이트가 되어가야한다하면
         * 우리 방법은 문제가 있다.
         * 뭔가 방법이 있다.
         *
         * 이 초기화 문제 어떻게 해결할래? 과제!!!!!!
         * */
    }

    public static class NoticeDialogFragment extends DialogFragment { // public static 으로 해야됨.
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) { // show시 계속 호출됨
            AlertDialog.Builder alertDialogBuiler = new AlertDialog.Builder(getActivity()); // 여러가지 시도해봐

            alertDialogBuiler.setMessage("Time Out!")
                    .setTitle("Notice")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .setCancelable(false); // 이거 안먹음 이제 다이얼로그 프래그먼트한테 관리권을 넘겼기 때문에 의미 없음

            return alertDialogBuiler.create();
        }
    }

    public static class ListDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final CharSequence[] items = {"Red", "Green", "Blue"};
//            final ArrayList<CharSequence> mSelectedItems = null; // 이걸로 which 업데이트
//            final boolean[] checkedItems = {true, false, true};

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity()); // 여러가지 시도해봐

            alertDialogBuilder
                    .setTitle("Please choose the color what you want")
//                    .setItems(items, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) { // 어디가 눌렸는지 인덱스
//                            Toast.makeText(getActivity(), items[which], Toast.LENGTH_SHORT).show();
//                        }
//                    }) // 하나밖에 선택안됨 선택되면 바로 나가짐
//                    .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getActivity(), items[which], Toast.LENGTH_SHORT).show();
//
//                        }
//                    })
                    .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() { // 두 번째 널 주면 초기값 안주는거
                        // 이름이 다르다! 멀티초이스 리스너임
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                            String str = (isChecked) ? " Checked" : " Unchecked";
//                            Toast.makeText(getActivity(), items[which] + str, Toast.LENGTH_SHORT).show();
//                            checkedItems[which] = isChecked; // 이런식으로 업데이트만 쭉 하면됨 근데 final이라 멤버변수로 바꾸는 작업해주고 해야함
                            if (isChecked)
                                checkedItems[which] = true;
                            else
                                checkedItems[which] = false;
                        }
                    })
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }) // 널주면 똑같음 안주는거랑
                    .setCancelable(false);

            return alertDialogBuilder.create();
        }
    }

    public static class ListDialogFragment2 extends DialogFragment {
        boolean[] mCheckedItems2 = {true, false, true};
        final static CharSequence[] mItems = {"Red", "Green", "Blue"};

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            // 최초 호출이면 아무것도 안들어있겠지
            if(savedInstanceState!=null){
                mCheckedItems2 = savedInstanceState.getBooleanArray("mCheckedItems2");
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity()); // 여러가지 시도해봐

            alertDialogBuilder
                    .setTitle("Please choose the color what you want")
                    .setMultiChoiceItems(mItems, mCheckedItems2, new DialogInterface.OnMultiChoiceClickListener() { // 두 번째 널 주면 초기값 안주는거
                        // 이름이 다르다! 멀티초이스 리스너임
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked)
                                mCheckedItems2[which] = true;
                            else
                                mCheckedItems2[which] = false;
                        }
                    })
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }) // 널주면 똑같음 안주는거랑
                    .setCancelable(false);

            return alertDialogBuilder.create();
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putBooleanArray("mCheckedItems2", mCheckedItems2);
        }
    }
}
