package com.example.letrungthuc_ktra2_bai2.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letrungthuc_ktra2_bai2.MainActivity;
import com.example.letrungthuc_ktra2_bai2.R;
import com.example.letrungthuc_ktra2_bai2.SQLite;
import com.example.letrungthuc_ktra2_bai2.adapter.Course;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private TextView date;
    private Spinner sp;
    private Button btnAdd;
    private CheckBox active;
    private  String[] majors;
    private SQLite sqLite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initView();
        addSprinner();
        sqLite= new SQLite(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + " / " + (month + 1) + " / " + year);
                    }
                }, mYear, mMonth, mDay);
                dialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String c=sp.getSelectedItem().toString();
                String d=date.getText().toString();
                boolean a= active.isChecked();
                int check;
                if(a==true){
                    check= 1;
                }
                else {
                    check=0;
                }

                Course course= new Course(n,c,d,check);
                sqLite.addPerson(course);
                startActivity(new Intent(AddActivity.this, MainActivity.class));
                reset();
                finish();
            }
        });
    }

    private void initView() {
        name=findViewById(R.id.name);
        sp=findViewById(R.id.sp);
        date=findViewById(R.id.date);
        active=findViewById(R.id.active);
        btnAdd=findViewById(R.id.btnAdd);
    }

    private void addSprinner(){
        majors=new String[]{"Tiếng Anh","CNTT","Kinh Tế","Truyền thông"};
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner,majors);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }
    public  void reset(){
        name.setText("");
        date.setText("");
    }
}
