package lv.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mainActivityTitle = findViewById(R.id.main_title);
        mainActivityTitle.setText(R.string.main_title);

        Button btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity2.setText(R.string.btn_activity_2);

        Button btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setText(R.string.btn_dialog);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> selectedMembers = new ArrayList<Integer>();

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.groups_dialog_title)
                        .setMultiChoiceItems(R.array.members, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                        if (isChecked) {
                                            selectedMembers.add(which);
                                            String toastText = getResources().getStringArray(R.array.members)[which] + " " + getResources().getString(R.string.checked);
                                            Toast toast = Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT);
                                            toast.show();
                                        } else if (selectedMembers.contains(which)) {
                                            selectedMembers.remove(which);
                                            String toastText = getResources().getStringArray(R.array.members)[which] + " " + getResources().getString(R.string.unchecked);
                                            Toast toast = Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    }
                                })
                        .setPositiveButton(R.string.btn_ok, null)
                        .setNegativeButton(R.string.btn_close, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast toast = Toast.makeText(MainActivity.this, R.string.closed_dialog, Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }).show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.clicked_ok, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }
}