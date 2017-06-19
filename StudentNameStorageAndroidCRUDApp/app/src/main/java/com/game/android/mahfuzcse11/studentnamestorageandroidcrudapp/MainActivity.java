package com.game.android.mahfuzcse11.studentnamestorageandroidcrudapp;

import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName;
    TextView tvAllStudentInfo;
    DbController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        tvAllStudentInfo = (TextView) findViewById(R.id.tvAllStudentInfo);

        dbController = new DbController(this, "", null, 1);
    }


    public void onClick(View view) {


        switch (view.getId()) {


            case R.id.btnAdd:
                try {

                    dbController.insertStudent(etFirstName.getText().toString(), etLastName.getText().toString());

                } catch (SQLException e) {
                    Toast.makeText(this, "ALREADY EXISTS ", Toast.LENGTH_LONG).show();

                }


                break;
            case R.id.btnDelete:

                dbController.deleteStudent(etFirstName.getText().toString());
                break;
            case R.id.btnUpdate:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);


                builder.setTitle("Enter New First Name");


                final EditText newEditText = new EditText(this);

                builder.setView(newEditText);


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbController.updateStudent(etFirstName.getText().toString(), newEditText.getText().toString());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Update Cancled", Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();

                break;
            case R.id.btnShowAllSudent:


                dbController.listAllStudent(tvAllStudentInfo);
                break;

        }
    }
}
