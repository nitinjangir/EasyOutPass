package com.ramola.vibhav;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ramola.vibhav.Model.UpdateStudentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStudentActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText userLocationEditText, userRFIDEditText;
    private Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        userLocationEditText = (EditText) findViewById(R.id.locationEditText);
        userRFIDEditText = (EditText) findViewById(R.id.rfIdEditText);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rfId=userRFIDEditText.getText().toString();
                String location = userLocationEditText.getText().toString();
                if(!rfId.isEmpty()&&!location.isEmpty())
                {
                    progressBar.setVisibility(View.VISIBLE);
                }

                else
                    Toast.makeText(UpdateStudentActivity.this,"Please Enter The Required Information",Toast.LENGTH_LONG).show();

            }
        });

    }


}
