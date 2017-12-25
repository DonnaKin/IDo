package com.example.minkyung.ido;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {
    Button call,message;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        call=(Button)findViewById(R.id.call);
        message=(Button)findViewById(R.id.message);
    }
    public void onClick(View v){
        Intent i=null;
        switch(v.getId()) {
            case R.id.call:
                i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:112"));
                startActivity(i);
                break;
            case R.id.message:
                uri = Uri.parse("smsto:112");
                i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", "이름/전화번호/주소/내용으로 기재해주시길 바랍니다.");
                startActivity(i);
                break;
        }
    }
}
