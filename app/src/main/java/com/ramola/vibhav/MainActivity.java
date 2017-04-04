package com.ramola.vibhav;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ramola.vibhav.Model.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private EditText searchEditText;
private Button searchBtn;
private ImageView userPic;
private TextView userName, userRollNo, userRoomNo;
private LinearLayout userDetailLayout;
private CoordinatorLayout coordinatorLayout;
private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main);
        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        userDetailLayout = (LinearLayout) findViewById(R.id.userDetailLayout);
        userName = (TextView) findViewById(R.id.nameUser);
        userRollNo = (TextView) findViewById(R.id.rollNoUser);
        userPic = (ImageView) findViewById(R.id.picUser);
        userRoomNo = (TextView) findViewById(R.id.roomNoUser);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchEditText.getText().toString().isEmpty())
                { search(searchEditText.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);

                }

                else {
                    Snackbar.make(coordinatorLayout, "Please Enter The Required Data", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(MainActivity.this,StudentListActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void search(String id){

        Call<Student> call = Util.getRetrofitService().getStudentInformation(id);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student student = response.body();
                progressBar.setVisibility(View.GONE);
                if(student!=null&&response.isSuccess()){

                    userDetailLayout.setVisibility(View.VISIBLE);
                    userName.setText(student.getName());
                    userRoomNo.setText(student.getRoomNo());
                    userRollNo.setText(student.getRollNo());
                    Glide.with(MainActivity.this).load(student.getPicUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(userPic);
                }
                else {
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                 t.printStackTrace();
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}
