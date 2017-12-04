package com.example.sqlitedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private MyOpenHelper helper;
	private SQLiteDatabase db;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyOpenHelper(this, "person.db");
        db = helper.getWritableDatabase();
        insert();  
//      delete();  
//      update();  
//      select(); 
        
    }
    
    /**
     * 向数据库插入数据
     */
    public void insert(){
    	ContentValues values = new ContentValues();
    	values.put("name", "小艾");
    	values.put("phone", "123456789");
    	values.put("salary", "12000");
    	db.insert("person", null, values);
    	values.clear();
    	
    	values.put("name", "小艾的儿子");  
        values.put("phone", "1836382");  
        values.put("salary", "14000"); 
        db.insert("person", null, values);
        values.clear();
        
        values.put("name", "小智的儿子");  
        values.put("phone", "1836382");  
        values.put("salary", "14000");  
        db.insert("person", null, values);  
        values.clear(); 
        
        values.put("name", "小智的老婆");  
        values.put("phone", "1836383");  
        values.put("salary", "13000");  
        db.insert("person", null, values);
        values.clear();
    }

    /** 
     * 删除数据 
     * db.delete(table, whereClause, whereArgs); 
     * table:表名 
     * whereClause:删除的where条件 
     * whereArgs:填充where条件的占位符 
     */  
    public void delete() {
		db.delete("person", "name = ? and id= ?",
				new String[]{"小艾的儿子","3"});
	}
    
    /** 
     * 更改数据 
     * db.update(table, values, whereClause, whereArgs); 
     * table:表名 
     * values:把要更改的数据存放到values中 
     * whereClause:更改的where条件 
     * whereArgs:填充where条件的占位符 
     */  
    public void update() {
		ContentValues values = new ContentValues();
		values.put("salary", "23000");
		db.update("person", values, "_id = ?", new String[]{"4"});
	}
    
    /** 
     * 查询数据 
     * db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit) 
     * table:表名 
     * columns：要查询的列名，是一个字符串数组 
     * selection：查询的where语句 
     * selectionArgs:填充where语句的占位符 
     * limit：设置数据分页显示，"1,10"代表显示从第一行到第10行,"10,10"代表从第10行到第20行 
     *  
     *  
     */ 
    public void select(){
    	Cursor cursor = db.query("person", new String[]{"_id","name","salary"}, "name = ?", 
    			new String[]{"小艾的儿子"}, null, null, null, null);
    	while (cursor.moveToNext()) {
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String salary = cursor.getString(cursor.getColumnIndex("salary"));
			Log.i("main","id=" + _id);//2  
	        Log.i("main", "name=" + name);//小智的儿子  
	        Log.i("main", "salary=" + salary);//14000  
		}
    }
}
