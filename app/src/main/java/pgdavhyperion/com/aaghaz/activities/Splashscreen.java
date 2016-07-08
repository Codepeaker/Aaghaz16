package pgdavhyperion.com.aaghaz.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.starperfomance.ViewPagerDemo;

public class Splashscreen extends AppCompatActivity {

    public static final long SPLASH_TIME = 2500;
    Runnable mjumprunnable;
    Handler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mjumprunnable = new Runnable() {
            @Override
            public void run() {
                jump();
            }
        };
        mhandler = new Handler();
        mhandler.postDelayed(mjumprunnable, SPLASH_TIME);

        ImageView imageView1 = (ImageView) findViewById(R.id.aaghazsplash);
        ImageView imageView2 = (ImageView) findViewById(R.id.splashpgdav);

        Animation in = AnimationUtils.loadAnimation(this, R.anim.fadein);
        imageView1.startAnimation(in);
        imageView1.setVisibility(View.VISIBLE);
        imageView2.startAnimation(in);
        imageView2.setVisibility(View.VISIBLE);


        final String[] Events_Name = getResources().getStringArray(R.array.Event_Name);
        final String[] Events_Type = getResources().getStringArray(R.array.Event_Type);
        final String[] Events_Desc = getResources().getStringArray(R.array.Event_Desc);
        final String[] Events_Rules = getResources().getStringArray(R.array.Event_Rules);
        final String[] Events_Date = getResources().getStringArray(R.array.Event_Date);
        final String[] Events_Time = getResources().getStringArray(R.array.Event_Time);
        final String[] Events_Venue = getResources().getStringArray(R.array.Event_Venue);
        final String[] Events_Min = getResources().getStringArray(R.array.Event_Min);
        final String[] Events_Max = getResources().getStringArray(R.array.Event_Max);
        final String[] Events_Soc = getResources().getStringArray(R.array.Event_Soc);
        final String[] Events_Note = getResources().getStringArray(R.array.note);

        SharedPreferences spd = getSharedPreferences("Aaghaz16", MODE_PRIVATE);
        int initial = spd.getInt("update", 0);
        if (initial!=3) {
            SQLiteDatabase db = openOrCreateDatabase("Aaghaz16", MODE_PRIVATE, null);
            db.execSQL("drop table if exists events");
            db.execSQL("create table if not exists events (id integer PRIMARY KEY AUTOINCREMENT,name varchar,type varchar,desc varchar, rules varchar, date varchar, time varchar,venue varchar,min int,max int,soc varchar,note varchar)");
            for (int i = 0; i < Events_Name.length; i++)
                db.execSQL("insert into events(name,type,desc,rules,date,time,venue,min,max,soc,note) values (\"" + Events_Name[i] + "\",\"" + Events_Type[i] + "\",\"" + Events_Desc[i] + "\",\"" + Events_Rules[i] + "\",\"" + Events_Date[i] + "\",\"" + Events_Time[i] + "\",\"" + Events_Venue[i] + "\"," + Events_Min[i] + "," + Events_Max[i] + ",'"+Events_Soc[i]+"','"+Events_Note[i]+"')");
            db.close();
            SharedPreferences.Editor e = spd.edit();
            e.putInt("update", 3);
            e.apply();
        }

    }

    public void jump() {

        if (isFinishing()) {
            return;
        }

        Intent in=new Intent(this, ViewPagerDemo.class);
        in.putExtra("caller",true);
        startActivity(in);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }
}

