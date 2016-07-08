package pgdavhyperion.com.aaghaz.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pgdavhyperion.com.aaghaz.R;

public class Society extends AppCompatActivity {

    TextView tv_society, tv_type, tv_desc, events, aboutus, name1, name2, no1, no2;
    LinearLayout l,reach1,reach2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society);

        final int eventindex = getIntent().getExtras().getInt("society_index");
        final String[] Society_Name = getResources().getStringArray(R.array.society_name);
        final String[] Society_Type = getResources().getStringArray(R.array.society_type);
        final String[] Society_Desc = getResources().getStringArray(R.array.society_desc);
        final String[] Society_n1 = getResources().getStringArray(R.array.society_contactn1);
        final String[] Society_n2 = getResources().getStringArray(R.array.society_contactn2);
        final String[] Society_p1 = getResources().getStringArray(R.array.society_contactp1);
        final String[] Society_p2 = getResources().getStringArray(R.array.society_contactp2);
        int Society_Image[] = {R.drawable.ic_chanakya, R.drawable.ic_conundrum, R.drawable.ic_diversity, R.drawable.ic_impressions, R.drawable.ic_iris, R.drawable.ic_navrang, R.drawable.ic_raaga, R.drawable.ic_rapbeats, R.drawable.ic_rudra, R.drawable.ic_techwiz};

        tv_society = (TextView) findViewById(R.id.society_name);
        tv_type = (TextView) findViewById(R.id.society_type);
        tv_desc = (TextView) findViewById(R.id.society_desc);
        events = (TextView) findViewById(R.id.events);
        aboutus = (TextView) findViewById(R.id.aboutus);
        l = (LinearLayout) findViewById(R.id.linearevents);
        iv = (ImageView) findViewById(R.id.iv);
        name1= (TextView) findViewById(R.id.name1);
        name2= (TextView) findViewById(R.id.name2);
        no1= (TextView) findViewById(R.id.no1);
        no2= (TextView) findViewById(R.id.no2);
        reach1= (LinearLayout) findViewById(R.id.reach1);
        reach2= (LinearLayout) findViewById(R.id.reach2);

        Typeface tf_heading = Typeface.createFromAsset(getAssets(), "kushan.otf");
        Typeface tf_body = Typeface.createFromAsset(getAssets(), "sofia.otf");
        Typeface tf_bar = Typeface.createFromAsset(getAssets(), "lobster.otf");
        tv_society.setTypeface(tf_heading);
        tv_desc.setTypeface(tf_body);
        tv_type.setTypeface(tf_bar);
        events.setTypeface(tf_bar);
        aboutus.setTypeface(tf_bar);
        name1.setTypeface(tf_heading);
        name2.setTypeface(tf_heading);
        no1.setTypeface(tf_heading);
        no2.setTypeface(tf_heading);

        tv_society.setText(Society_Name[eventindex]);
        tv_type.setText(Society_Type[eventindex]);
        tv_desc.setText(Society_Desc[eventindex]);
        iv.setImageResource(Society_Image[eventindex]);
        name1.setText(Society_n1[eventindex]);
        name2.setText(Society_n2[eventindex]);
        no1.setText(Society_p1[eventindex]);
        no2.setText(Society_p2[eventindex]);

        reach1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_DIAL);
                in.setData(Uri.parse("tel:" + no1.getText().toString()));
                Intent chooser= Intent.createChooser(in, "Choose Dialer...");
                startActivity(chooser);
            }
        });
        reach2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_DIAL);
                in.setData(Uri.parse("tel:" + no2.getText().toString()));
                Intent chooser= Intent.createChooser(in, "Choose Dialer...");
                startActivity(chooser);
            }
        });

        SQLiteDatabase db = openOrCreateDatabase("Aaghaz16", MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from events where soc='" + Society_Name[eventindex] + "'", null);
        if (c.moveToFirst()) {
            do {
                LayoutInflater li = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View v = li.inflate(R.layout.event_society, l, false);
                final TextView ename = (TextView) v.findViewById(R.id.ename);
                Button b = (Button) v.findViewById(R.id.rbutton);
                ename.setText(c.getString(1));
                ename.setTypeface(tf_heading);
                b.setTypeface(tf_bar);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(Society.this, Events.class);
                        in.putExtra("event_name", ename.getText());
                        startActivity(in);
                    }
                });
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(Society.this, RegisterActivity.class);
                        in.putExtra("event_name", ename.getText());
                        startActivity(in);
                    }
                });
                l.addView(v);
            }
            while (c.moveToNext());
        } else {
            events.setVisibility(View.GONE);
        }
        c.close();
        db.close();
    }

}