package com.ramola.vibhav;


import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ramola.vibhav.Model.Student;
import com.ramola.vibhav.Model.StudentListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private StudentAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isShowingRecord = false;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EasyOutPass");
        recyclerView = (RecyclerView) findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        adapter = new StudentAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getDetail();

        swipeRefreshLayout.setOnRefreshListener(this);


         mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {

        }
        else
        {
            /*
            Intent i =new Intent(StudentListActivity.this,ServerSide.class);
            startService(i);
            */
        }
    }

    private void getDetail(){
        Call<StudentListResponse> call = Util.getRetrofitService().getAllStudentDetail();
        call.enqueue(new Callback<StudentListResponse>() {
            @Override
            public void onResponse(Call<StudentListResponse> call, Response<StudentListResponse> response) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                isShowingRecord=false;
                progressBar.setVisibility(View.GONE);
                StudentListResponse r=response.body();
                if(r!=null&&response.isSuccess()){
                    ArrayList<Student> list = response.body().getGetAllStudent();
                    adapter.refresh(list);
                }
            }

            @Override
            public void onFailure(Call<StudentListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }


    private void  getRecordDetail(){
        Call<StudentListResponse> call = Util.getRetrofitService().getAllRecordDetail();
        call.enqueue(new Callback<StudentListResponse>() {
            @Override
            public void onResponse(Call<StudentListResponse> call, Response<StudentListResponse> response) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                isShowingRecord=true;
                progressBar.setVisibility(View.GONE);
                StudentListResponse r=response.body();
                if(r!=null&&response.isSuccess()){
                    ArrayList<Student> list = response.body().getGetAllStudent();
                    adapter.refresh(list);
                }
            }

            @Override
            public void onFailure(Call<StudentListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }


    private int getMidnightTime(){
        int midTime =0;

        return midTime;
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
        if(id==R.id.action_connect){
            if(mBluetoothAdapter.isEnabled()){
            Intent i =new Intent(StudentListActivity.this,ClientThread.class);
            i.putExtra(ClientThread.ADDRESS,"30:14:12:23:15:13");
            startService(i);}
            else {
                Toast.makeText(this,"Please Enable The Bluetooth",Toast.LENGTH_LONG).show();
            }
        }
        else if(id==R.id.action_record){
            progressBar.setVisibility(View.VISIBLE);
            getRecordDetail();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {

        if(isShowingRecord){
            getRecordDetail();
        }
        else
        getDetail();
    }
}
