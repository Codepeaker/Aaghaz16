package pgdavhyperion.com.aaghaz.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import pgdavhyperion.com.aaghaz.R;

public class Timeline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SubsamplingScaleImageView imageDetail = (SubsamplingScaleImageView) findViewById(R.id.imageView);
        imageDetail.setImage(ImageSource.resource(R.drawable.timeline_img));
    }


}
