package pgdavhyperion.com.aaghaz.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import pgdavhyperion.com.aaghaz.R;

public class Events extends AppCompatActivity {

    TextView tv_event, tv_type, tv_desc, tv_rules, tv_date, tv_time, tv_venue, tv_participants, desc, rules, date, time, venue, participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        final String eventname = getIntent().getExtras().getString("event_name");

        tv_event = (TextView) findViewById(R.id.event_name);
        tv_type = (TextView) findViewById(R.id.event_type);
        tv_desc = (TextView) findViewById(R.id.event_desc);
        tv_rules = (TextView) findViewById(R.id.event_rules);
        tv_date = (TextView) findViewById(R.id.event_date);
        tv_time = (TextView) findViewById(R.id.event_time);
        tv_venue = (TextView) findViewById(R.id.event_venue);
        tv_participants = (TextView) findViewById(R.id.event_partcipants);
        desc = (TextView) findViewById(R.id.desc);
        rules = (TextView) findViewById(R.id.rules);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        venue = (TextView) findViewById(R.id.venue);
        participants = (TextView) findViewById(R.id.participants);

        Typeface tf_heading = Typeface.createFromAsset(getAssets(), "kushan.otf");
        Typeface tf_body = Typeface.createFromAsset(getAssets(), "sofia.otf");
        Typeface tf_bar = Typeface.createFromAsset(getAssets(), "lobster.otf");
        tv_event.setTypeface(tf_heading);
        tv_desc.setTypeface(tf_body);
        tv_rules.setTypeface(tf_body);
        tv_date.setTypeface(tf_body);
        tv_time.setTypeface(tf_body);
        tv_venue.setTypeface(tf_body);
        tv_participants.setTypeface(tf_body);
        tv_type.setTypeface(tf_bar);
        desc.setTypeface(tf_bar);
        rules.setTypeface(tf_bar);
        date.setTypeface(tf_bar);
        time.setTypeface(tf_bar);
        venue.setTypeface(tf_bar);
        participants.setTypeface(tf_bar);

        SQLiteDatabase db = openOrCreateDatabase("Aaghaz16", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("select * from events where name='" + eventname + "'", null);
        if (c.moveToFirst()) {
            tv_event.setText(c.getString(1));
            if (c.getString(2).length() > 0) {
                tv_type.setText(c.getString(2));
            } else
                tv_type.setVisibility(View.GONE);
            if (c.getString(3).length() > 0)
                tv_desc.setText(c.getString(3));
            else
                tv_desc.setVisibility(View.GONE);
            if (c.getString(4).length() > 0)
                tv_rules.setText(c.getString(4));
            else {
                tv_rules.setVisibility(View.GONE);
                rules.setVisibility(View.GONE);
            }
            if (c.getString(5).length() > 0)
                tv_date.setText(c.getString(5));
            else {
                date.setVisibility(View.GONE);
                tv_date.setVisibility(View.GONE);
            }
            if (c.getString(6).length() > 0)
                tv_time.setText(c.getString(6));
            else {
                time.setVisibility(View.GONE);
                tv_time.setVisibility(View.GONE);
            }
            if (c.getString(7).length() > 0)
                tv_venue.setText(c.getString(7));
            else {
                venue.setVisibility(View.GONE);
                tv_venue.setVisibility(View.GONE);
            }
            int min, max;
            if ((min = c.getInt(8)) == (max = c.getInt(9))) {
                if (min == 0) {
                    participants.setVisibility(View.GONE);
                    tv_participants.setVisibility(View.GONE);
                } else
                    tv_participants.setText(min + "");
            } else {
                tv_participants.setText(min + "-" + max);
            }
        }
        c.close();
        db.close();

        FloatingActionButton b = (FloatingActionButton) findViewById(R.id.registerhere);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Events.this, RegisterActivity.class);
                in.putExtra("event_name", eventname);
                startActivity(in);
            }
        });
    }

}
