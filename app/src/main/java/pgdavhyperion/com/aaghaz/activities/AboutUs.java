package pgdavhyperion.com.aaghaz.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import pgdavhyperion.com.aaghaz.R;

public class AboutUs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);
        TextView dvs = (TextView) findViewById(R.id.developer_vs);
        dvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse("https://www.facebook.com/vishal.jangid.902/"));
                Intent chooser= Intent.createChooser(in, "Open Link through...");
                startActivity(chooser);
            }
        });

        TextView das = (TextView) findViewById(R.id.developer_as);
        das.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse("https://www.facebook.com/akansh1/"));
                Intent chooser= Intent.createChooser(in, "Open Link through...");
                startActivity(chooser);
            }
        });

    }
}
