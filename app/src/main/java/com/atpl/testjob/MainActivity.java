package com.atpl.testjob;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.atpl.notifyme.NotifyMe;
import com.atpl.testjob.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        setBuildType();

        sp=getSharedPreferences("sp",MODE_PRIVATE);
        if (sp.getString("firstTime","yes").equalsIgnoreCase("yes")){
            setNotify();
        }
    }

    public void setBuildType(){
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")){
            binding.buildTypeTV.setText(getString(R.string.qa));
        }else {
            binding.buildTypeTV.setText(getString(R.string.production));
        }
    }

    public void setNotify(){
        Intent intent = new Intent(getApplicationContext(),TestActivity.class);
        intent.putExtra("test","test");
        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(getString(R.string.app_nameNotify))
                .content(getString(R.string.running))
                .key("test")
                .build();
        sp.edit().putString("firstTime","no").apply();

        final NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (notificationManager!=null)
                    notificationManager.cancelAll();
            }
        },1000*60*5);

    }
}
