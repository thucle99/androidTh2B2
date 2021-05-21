package com.example.letrungthuc_ktra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letrungthuc_ktra2_bai2.activity.EditActivity;
import com.example.letrungthuc_ktra2_bai2.adapter.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private List<Course> courses;
    Context context;
    private TextView txtId,txtName,txtDate,txtMajor,txtActive;

    public CourseAdapter(List<Course> courses,Context context){
        this.courses=courses;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.information_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.bind(course,position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("course",course);
                context.startActivity(intent);
                return true;
            }// khi long click vao item
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View v) {
            super(v);
            txtId = v.findViewById(R.id.txtId);
            txtName = v.findViewById(R.id.txtName);
            txtDate = v.findViewById(R.id.txtDate);
            txtMajor=v.findViewById(R.id.txtMajor);
            txtActive = v.findViewById(R.id.txtActive);

        }

        public void bind(Course course,int position){
            String isActive="";
            txtId.setText("Mã: "+ course.getId());
            txtName.setText("Tên: " + course.getName());
            txtDate.setText("Thời gian: "+course.getDate());
            txtMajor.setText("Chuyển nghành:" +course.getMajor());
            if(course.getActive()==1){
                isActive="Hoạt động";
            }
            else {
                isActive="Không hoạt động";
            }
            txtActive.setText("Trạng thái: " + isActive);
            // id show information
        }
    }


    public void setPerson(List<Course> courses){
        this.courses=courses;
    }
}
