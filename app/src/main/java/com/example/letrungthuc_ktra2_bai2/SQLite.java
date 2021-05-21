package com.example.letrungthuc_ktra2_bai2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.letrungthuc_ktra2_bai2.adapter.Course;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    private static final String DB_Name = "HocOnline.db";
    private static final int DB_Version = 1;

    public SQLite(@Nullable Context context) {
        super(context,DB_Name,null,DB_Version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE course(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "date TEXT," +
                "major TEXT," +
                "active INTEGER)";
        db.execSQL(sql);
    }

    public void addPerson(Course course){
        String sql= "INSERT INTO course(name,date,major, active) " +
                "VALUES (?, ?, ?, ?)";
        String[] args= {course.getName(),course.getDate(),
                course.getMajor(),Integer.toString(course.getActive())};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL(sql,args);
    }

    public List<Course> getListAll(){
        List<Course> courses= new ArrayList<>();
        SQLiteDatabase sql = getReadableDatabase();
        Cursor cursor = sql.query("course",null,
                null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            String major = cursor.getString(3);
            int active   = cursor.getInt(4);

            Course course = new Course(id,name,date,major,active);
            courses.add(course);
        }
        return courses;
    }

    public List<Course> getCourseByName(String name) {
        List<Course> list = new ArrayList<>();
        String whereClause  = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query("course",
                null, whereClause, whereArgs, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int idD = cursor.getInt(cursor.getColumnIndex("id"));
            String nameD = cursor.getString(cursor.getColumnIndex("name"));
            String dateD = cursor.getString(cursor.getColumnIndex("date"));
            String majorD = cursor.getString(cursor.getColumnIndex("major"));
            int active = cursor.getInt(cursor.getColumnIndex("active"));

            list.add(new Course(idD, nameD, dateD, majorD,active));
        }
        cursor.close();
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
