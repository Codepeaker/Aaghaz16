package pgdavhyperion.com.aaghaz.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pgdavhyperion.com.aaghaz.R;

public class HomeActivity extends AppCompatActivity {

    TextView tvdesc1, tvdesc2, tvdesc3, tvdesc4, tvdesc5, tvdesc6, tvdesc7, n1, n2, p1, p2, tvreachus, tvrules, tvgrules,tvmail,tvsite;
    LinearLayout reach1, reach2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });

        String desc1 = getResources().getString(R.string.aaghaz_theme1);
        String desc2 = getResources().getString(R.string.aaghaz_theme2);
        String desc3 = getResources().getString(R.string.aaghaz_theme3);
        String desc4 = getResources().getString(R.string.aaghaz_theme4);
        String desc5 = getResources().getString(R.string.aaghaz_theme5);
        String desc6 = getResources().getString(R.string.aaghaz_theme6);
        String desc7 = getResources().getString(R.string.aaghaz_theme7);
        String rules = getResources().getString(R.string.rules);

        tvdesc1 = (TextView) findViewById(R.id.aaghazinfo1);
        tvdesc2 = (TextView) findViewById(R.id.aaghazinfo2);
        tvdesc3 = (TextView) findViewById(R.id.aaghazinfo3);
        tvdesc4 = (TextView) findViewById(R.id.aaghazinfo4);
        tvdesc5 = (TextView) findViewById(R.id.aaghazinfo5);
        tvdesc6 = (TextView) findViewById(R.id.aaghazinfo6);
        tvdesc7 = (TextView) findViewById(R.id.aaghazinfo7);
        tvrules = (TextView) findViewById(R.id.aaghazrules);
        tvgrules = (TextView) findViewById(R.id.grules);
        tvreachus = (TextView) findViewById(R.id.reachus);
        tvmail = (TextView) findViewById(R.id.mail);
        tvsite = (TextView) findViewById(R.id.site);
        n1 = (TextView) findViewById(R.id.name1);
        n2 = (TextView) findViewById(R.id.name2);
        p1 = (TextView) findViewById(R.id.no1);
        p2 = (TextView) findViewById(R.id.no2);
        iv = (ImageView) findViewById(R.id.imageviewcd);
        reach1 = (LinearLayout) findViewById(R.id.reach1);
        reach2 = (LinearLayout) findViewById(R.id.reach2);

        tvdesc1.setText(desc1);
        tvdesc2.setText(desc2);
        tvdesc3.setText(desc3);
        tvdesc4.setText(desc4);
        tvdesc5.setText(desc5);
        tvdesc6.setText(desc6);
        tvdesc7.setText(desc7);
        tvrules.setText(rules);
        tvmail.setText("Mail Us at info@pgdavhyperion.com");
        tvsite.setText("www.pgdavhyperion.com");
        n1.setText("Talal Ansari");
        n2.setText("Nihal Nizami");
        p1.setText("8510861678");
        p2.setText("8750376944");

        Typeface tf_heading = Typeface.createFromAsset(getAssets(), "kushan.otf");
        Typeface tf_body = Typeface.createFromAsset(getAssets(), "sofia.otf");
        Typeface tf_bar = Typeface.createFromAsset(getAssets(), "lobster.otf");
        tvgrules.setTypeface(tf_bar);
        tvreachus.setTypeface(tf_bar);
        tvdesc1.setTypeface(tf_heading);
        tvdesc2.setTypeface(tf_heading);
        tvdesc3.setTypeface(tf_heading);
        tvdesc4.setTypeface(tf_heading);
        tvdesc5.setTypeface(tf_heading);
        tvdesc6.setTypeface(tf_heading);
        tvdesc7.setTypeface(tf_heading);
        tvrules.setTypeface(tf_heading);
        tvsite.setTypeface(tf_heading);
        tvmail.setTypeface(tf_heading);
        n1.setTypeface(tf_heading);
        n2.setTypeface(tf_heading);
        p1.setTypeface(tf_body);
        p2.setTypeface(tf_body);

        reach1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL);
                in.setData(Uri.parse("tel:" + p1.getText().toString()));
                Intent chooser = Intent.createChooser(in, "Choose Dialer...");
                startActivity(chooser);
            }
        });
        reach2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL);
                in.setData(Uri.parse("tel:" + p2.getText().toString()));
                Intent chooser = Intent.createChooser(in, "Choose Dialer...");
                startActivity(chooser);
            }
        });

        tvmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@pgdavhyperion.com", null));
                in.putExtra(Intent.EXTRA_SUBJECT, "Aaghaz'16 Enquiry");
                Intent chooser = Intent.createChooser(in, "Mail through...");
                startActivity(chooser);
            }
        });

        tvsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse("http://www.pgdavhyperion.com"));
                Intent chooser= Intent.createChooser(in, "Open Link through...");
                startActivity(chooser);
            }
        });

        new CountDownTimer(1000000, 5000) {
            int i = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                switch (i) {
                    case 0:
                        iv.setImageResource(R.drawable.ic_2);
                        i = 1;
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.ic_38);
                        i = 2;
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.ic_41);
                        i = 0;
                        break;
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

}
