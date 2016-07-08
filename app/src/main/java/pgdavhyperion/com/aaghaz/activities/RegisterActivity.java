package pgdavhyperion.com.aaghaz.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;

import me.james.biuedittext.BiuEditText;
import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.extras.ServletInterface;

public class RegisterActivity extends AppCompatActivity {

    boolean success = false;
    String[] Events;
    BetterSpinner spinner1, spinner2, spinner3;
    BiuEditText college, name, pno, course, email;
    Button b;
    String event = "";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Registrations has been closed except Respawn (Counter Strike)...", Snackbar.LENGTH_LONG);
        snackbar.show();

        college = (BiuEditText) findViewById(R.id.viewcollege);
        name = (BiuEditText) findViewById(R.id.viewname);
        pno = (BiuEditText) findViewById(R.id.viewpno);
        course = (BiuEditText) findViewById(R.id.viewcourse);
        email = (BiuEditText) findViewById(R.id.viewemail);
        b = (Button) findViewById(R.id.register);
        tv = (TextView) findViewById(R.id.textview_note);

        final String[] Colleges = getResources().getStringArray(R.array.Colleges);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Colleges);
        spinner1 = (BetterSpinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position + 1 == Colleges.length) {
                    college.setVisibility(View.VISIBLE);
                } else {
                    college.setVisibility(View.GONE);
                }
            }
        });

        Typeface tf_heading = Typeface.createFromAsset(getAssets(), "kushan.otf");
        tv.setTypeface(tf_heading);
        int index = 0;
        SQLiteDatabase db = openOrCreateDatabase("Aaghaz16", MODE_PRIVATE, null);

        Cursor c = db.rawQuery("select count(name) from events", null);
        c.moveToFirst();
        Events = new String[c.getInt(0)];
        c.close();

        Cursor k = db.rawQuery("select name from events", null);
        if (k.moveToFirst())
            do {
                Events[index++] = k.getString(0);
            } while (k.moveToNext());
        k.close();
        db.close();

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Events);
        spinner2 = (BetterSpinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map_event_part();
            }
        });
        spinner3 = (BetterSpinner) findViewById(R.id.spinner3);

        if (getIntent().hasExtra("event_name"))
            spinner2.setText(getIntent().getExtras().getString("event_name"));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!spinner2.getText().toString().equals("Respawn"))
                {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Registrations has been closed except Respawn (Counter Strike)...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (!isInternetConnection(RegisterActivity.this)) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "No Internet Connection...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else if ((email.getText().length() == 0) || (course.getText().length() == 0) || (pno.getText().length() == 0) || (name.getText().length() == 0) || (spinner1.getText().length() == 0) || (spinner2.getText().length() == 0) || (spinner3.getText().length() == 0)) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "All fields are must...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else if (pno.getText().length() != 10) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Invalid Phone no...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else if ((college.getVisibility() == View.VISIBLE) && (college.getText().length() == 0)) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "College name is must...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else if (!(email.getText().toString().contains("@")) || !(email.getText().toString().contains("."))) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Invalid Email ID...", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else {

                    switch (spinner2.getText().toString()) {
                        case "Rebuttal":
                            event = "Rebuttal(The Conventional Debate)";
                            break;
                        case "Game of Gavels":
                            event = "Creative Writing";
                            break;
                        case "Worth Words? Wordsworth?":
                            event = "";
                            break;
                        case "Radeef":
                            event = "Radeef(The Poetry Recitation)";
                            break;
                        case "360 degrees":
                            event = "360degrees(The Annual National Quiz)";
                            break;
                        case "Bring Out Life- B.O.L.":
                            event = "BOL(The Anchoring Talent)";
                            break;
                        case "Battle of Bands":
                            event = "Battle of Bands";
                            break;
                        case "Step Burn":
                            event = "Stepburn(Western Group Dance)";
                            break;
                        case "Jazbaa":
                            event = "Jazba(Western Solo Dance)";
                            break;
                        case "Nrityangana":
                            event = "Nrityangna(Classical Solo Dance)";
                            break;
                        case "Jashan":
                            event = "Jashan(Folk Group Dance)";
                            break;
                        case "Chatori Dilli":
                            event = "Chatori Delhi(On the spot Painting)";
                            break;
                        case "Ye Joota Dilli ka":
                            event = "Joota Dilli ka(Shoe Painting)";
                            break;
                        case "On Spot Photography":
                            event = "On the Spot Photography";
                            break;
                        case "On Spot Videography":
                            event = "On the Spot Videography";
                            break;
                        case "Abhivyakti":
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Registrations Not Available... Contact Event Head", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            success = true;
                            return;
                        case "Swaragini":
                            event = "Swaragni(Group Classical Singing)";
                            break;
                        case "Swaranjali":
                            event = "Swaranjali(Solo Classical Singing)";
                            break;
                        case "Sursringar":
                            event = "Sur Sringar(Indian Solo Light Singing)";
                            break;
                        case "R.A.P.":
                            event = "R.A.P(Rhythm and Poetry)";
                            break;
                        case "DJ Wars":
                            event = "DJ Wars";
                            break;
                        case "Beat Boxing":
                            event = "Beat Boxing";
                            break;
                        case "Shor":
                            Snackbar snackbar1 = Snackbar.make(findViewById(R.id.coordinate), "Registrations Not Available... Contact Event Head", Snackbar.LENGTH_LONG);
                            snackbar1.show();
                            success = true;
                            return;
                        case "Techquiz":
                            event = "Techquiz(I.T. Quiz)";
                            break;
                        case "Code Storm":
                            event = "Code storm(Coding)";
                            break;
                        case "Respawn":
                            event = "Respawn(Counter-Strike)";
                            break;
                        case "LAN Racing":
                            event = "Lan Racing(NFS)";
                            break;
                        case "Web Canvas":
                            event = "Web Canvas(Web Designing)";
                            break;
                        case "Treasure Hunt":
                            event = "Treasure Hunt";
                            break;
                        case "Beg Borrow Steal":
                            event = "Beg Borrow Steal";
                            break;
                    }

                    final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                    pd.setIndeterminate(true);
                    pd.setTitle("Just a sec...");
                    pd.setMessage("Please have patience while we are registering for the event :)");
                    pd.setCancelable(false);
                    pd.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RegisterActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    new CountDownTimer(8000, 8000) {

                                        @Override
                                        public void onTick(long millisUntilFinished) {

                                        }

                                        @Override
                                        public void onFinish() {
                                            pd.cancel();
                                            if (!success) {
                                                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Bad Network Connection :( Try Again", Snackbar.LENGTH_LONG);
                                                snackbar.show();
                                            }
                                        }
                                    }.start();
                                }
                            });
                            String result = ServletInterface.executeHttpRequest(spinner1.getText().toString(), event, spinner3.getText().toString(), name.getText().toString(), pno.getText().toString(), course.getText().toString(), email.getText().toString());
                            if (result.equals("1")) {
                                RegisterActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pd.cancel();
                                        success = true;
                                        Toast.makeText(RegisterActivity.this, "Registration Successful :)", Toast.LENGTH_SHORT).show();
                                        Intent in = new Intent(RegisterActivity.this, TabActivity.class);
                                        startActivity(in);
                                        finish();
                                    }
                                });
                            } else {
                                RegisterActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pd.cancel();
                                        success = true;
                                        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinate), "Registration Unsuccessful :(", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        map_event_part();
    }

    public boolean isInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            return true;
        return false;
    }

    void map_event_part() {
        spinner3.setText(null);
        ArrayList<String> no = new ArrayList<String>();
        SQLiteDatabase db = openOrCreateDatabase("Aaghaz16", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("select min,max from events where name='" + spinner2.getText() + "'", null);
        if (c.moveToFirst()) {
            int min = c.getInt(0);
            int max = c.getInt(1);
            if (min == 0) {
                spinner3.setText("");
                no.add("");
            } else if (min == max) {
                spinner3.setText(min + "");
                no.add(min + "");
            } else {
                for (int i = min; i <= max; i++) {
                    no.add(i + "");
                }
            }
        }
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, no);
        spinner3.setAdapter(adapter3);

        c = db.rawQuery("select note from events where name='" + spinner2.getText() + "'", null);
        if (c.moveToFirst()) {
            if(c.getString(0).length()>0)
            {
                tv.setVisibility(View.VISIBLE);
                tv.setText("Note: "+c.getString(0));
            }
            else {
                tv.setVisibility(View.GONE);
                tv.setText("");
            }
        }
        c.close();
        db.close();
    }
}
