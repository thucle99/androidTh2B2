package com.example.letrungthuc_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.letrungthuc_ktra2_bai2.activity.AddActivity;
import com.example.letrungthuc_ktra2_bai2.adapter.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Course> courses;
    private SQLite sqLite;
    private CourseAdapter adapter;
    private RecyclerView recycle;
    private FloatingActionButton addIcon;
    private TextView sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle=findViewById(R.id.recycle);
        addIcon=findViewById(R.id.addIcon);
        sum=findViewById(R.id.sum);
        sqLite= new SQLite(this);

        courses = sqLite.getListAll();
        adapter = new CourseAdapter(courses,this);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        recycle.setAdapter(adapter);

        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        if(courses!=null && courses.size()>0){
            sum.setVisibility(View.VISIBLE);
            long tong=0;
            sum.setText("Số môn học: " + courses.size());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Course> list = sqLite.getCourseByName(newText);
                adapter.setPerson(list);
                recycle.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}