package com.example.ngoan.sqlite_sugarorm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ngoan.sqlite_sugarorm.adpter.CustomAdapetr;
import com.example.ngoan.sqlite_sugarorm.jobsmodel.Jobs;
import com.orm.SugarRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Jobs jobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobs = new Jobs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert:
                Dialog dialogInsert = new Dialog(MainActivity.this);
                dialogInsert.setContentView(R.layout.insert);
                setDialog(dialogInsert);
                dialogInsert.setCanceledOnTouchOutside(true);
                dialogInsert.setTitle("Add Job Title");
                EditText edtTitle = dialogInsert.findViewById(R.id.edt_title);
                Button btnSave = dialogInsert.findViewById(R.id.btn_save);
                btnSave.setOnClickListener(view -> {
                    String job_title = edtTitle.getText().toString().trim();
                    jobs.setJobTitle(job_title);
                    jobs.save();
                });
                dialogInsert.show();
                break;
            case R.id.update:
                Dialog dialogUpdate = new Dialog(MainActivity.this);
                dialogUpdate.setContentView(R.layout.update);
                setDialog(dialogUpdate);
                dialogUpdate.setTitle("Update Job Title");
                dialogUpdate.setCanceledOnTouchOutside(true);
                EditText edtUpdate = dialogUpdate.findViewById(R.id.edt_title);
                Button btnUpdate =  dialogUpdate.findViewById(R.id.btn_update);
                btnUpdate.setOnClickListener(view -> {
                    Jobs mJobs = Jobs.findById(Jobs.class,1);
                    String job_title = edtUpdate.getText().toString().trim();
                    mJobs.setJobTitle(job_title);
                    mJobs.update();
                });
                dialogUpdate.show();
                break;
            case R.id.display:
                Dialog dialogDisplay = new Dialog(this);
                dialogDisplay.setContentView(R.layout.display_jobs);
                setDialog(dialogDisplay);
                dialogDisplay.setTitle("Danh sách Jobs Title");
                dialogDisplay.setCanceledOnTouchOutside(true);
                RecyclerView recyclerView = dialogDisplay.findViewById(R.id.listView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                List<Jobs> arrJobs = SugarRecord.listAll(Jobs.class);
                CustomAdapetr  adapter = new CustomAdapetr(arrJobs);
                    adapter.setOnClickItemListener((v, position) -> {
                        Jobs itemJobs = arrJobs.get(position);
                        Jobs mJobs = Jobs.findById(Jobs.class,itemJobs.getId());
                        mJobs.delete();
                        Toast.makeText(getApplication(),mJobs.getJobTitle(),Toast.LENGTH_LONG).show();
                        adapter.removeAt(position);
                        adapter.notifyDataSetChanged();
                    });
                recyclerView.setAdapter(adapter);
                dialogDisplay.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDialog(Dialog dialog){
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }
}
