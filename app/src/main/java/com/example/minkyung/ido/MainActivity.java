package com.example.minkyung.ido;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText editName, editNum, editaddress;
    Button  butInsert;
    MyDBHelper myHelper;
    SQLiteDatabase sqldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker)findViewById(R.id.date);
        editName=(EditText)findViewById(R.id.edit_name);
        editaddress=(EditText)findViewById(R.id.edit_address);
        editNum=(EditText)findViewById(R.id.edit_num);
        butInsert=(Button)findViewById(R.id.but_insert);
        //DB 생성
        myHelper=new MyDBHelper(this);
        //기존의 테이블이 존재하면 삭제하고 테이블을 새로 생성한다.
        butInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sqldb=myHelper.getWritableDatabase();
                String sql="insert into data values('"+editName.getText()+"','"+editNum.getText()+"','"+editaddress.getText()+"')";
                Toast.makeText(MainActivity.this,"감사합니다",Toast.LENGTH_LONG).show();
                sqldb.execSQL(sql);
                sqldb.close();
            }
        });
        Button b = (Button)findViewById(R.id.but_insert);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        MainActivity2.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
    class MyDBHelper extends SQLiteOpenHelper{
        public MyDBHelper(Context context) {
            super(context, "DB", null, 1);//DB라는 이름의 데이터베이스가 생성된다. 숫자 1은 버전임
        }
        // data라는 이름의 테이블 생성
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql="create table data(Name text not null primary key, Date Integer, Address text, Tel text)";
            db.execSQL(sql);
        }
        //이미 data가 존재한다면 기존의 테이블을 삭제하고, 새로 테이블을 만들 때 호출
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int j) {
            String sql="drop table if exists data";
            db.execSQL(sql);
            onCreate(db);
        }//내부클래스
    }
}
