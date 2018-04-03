package com.example.shobhraj.message;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1;
    EditText mno,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.SEND_SMS))
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
            }
        }
        else
        {
            //do nothing
        }

        bt1=findViewById(R.id.send);
        mno=findViewById(R.id.mno);
        msg=findViewById(R.id.message1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=mno.getText().toString();
                String sms=msg.getText().toString();

                try{
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,sms,null,null);
                    Toast.makeText(MainActivity.this,"message sent",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"failed to send message",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode)
        {
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this,"permission granted",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(this,"permission denied",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }
}
