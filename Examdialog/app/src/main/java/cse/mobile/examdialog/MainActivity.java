package cse.mobile.examdialog;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btDialog = findViewById(R.id.btDialog);
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Noitce")
                        .setMessage("공지입니다.")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        Button btDialogColor = findViewById(R.id.btDialogColor);
        btDialogColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment colorDialogFragment = new ColorDialogFragment();
                colorDialogFragment.setCancelable(false);
                colorDialogFragment.show(getSupportFragmentManager(), "colorDialogFragment");
            }
        });

    }

    public static class ColorDialogFragment extends DialogFragment {
        private CharSequence[] mItems = {"RED", "GREEN", "BLUE"};
        private static boolean[] mCheckedItems = {true, false, true};

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                mCheckedItems = savedInstanceState.getBooleanArray("mCheckedItems");
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

            alertDialogBuilder
                    .setTitle("Choose the color what you want :")
                    .setMultiChoiceItems(mItems, mCheckedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked)
                                mCheckedItems[which] = true;
                            else
                                mCheckedItems[which] = false;
                        }
                    })
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            return alertDialogBuilder.create();
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putBooleanArray("mCheckedItems", mCheckedItems);
        }
    }
}
