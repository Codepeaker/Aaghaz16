package pgdavhyperion.com.aaghaz.starperfomance;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.activities.TabActivity;


public class ViewPagerDemo extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public Intent i;
    private ImageButton btnNext;
    private ImageButton btnFinish;
    private TextView btnSkip;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    private int pos;
    private Boolean caller;

    private int[] mImageResources = {
            R.mipmap.day1celeberaty,
            R.mipmap.day1bandndj,
            R.mipmap.day2singer,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_demo);

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);
        btnNext = (ImageButton) findViewById(R.id.btn_next);
        btnFinish = (ImageButton) findViewById(R.id.btn_finish);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        mAdapter = new ViewPagerAdapter(ViewPagerDemo.this, mImageResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);

        caller = getIntent().getBooleanExtra("caller", false);
        setUiPageViewController();
        pos = 0;

        new CountDownTimer(1000000, 3000) {

            @Override
            public void onTick(long millisUntilFinished) {
                switch (pos) {
                    case 0:
                        intro_images.setCurrentItem(pos);
                        pos = 1;
                        break;
                    case 1:
                        intro_images.setCurrentItem(pos);
                        pos = 2;
                        break;
                    case 2:
                        intro_images.setCurrentItem(pos);
                        pos = 0;
                        break;
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }



    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);

        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < (dotsCount - 1)) ? intro_images.getCurrentItem() + 1 : 0);
                break;

            case R.id.btn_finish:
                if(caller)
                {
                    i = new Intent(ViewPagerDemo.this, TabActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    finish();
                }
                break;





        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void skip(View v){
        i = new Intent(ViewPagerDemo.this, TabActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onPageSelected(int position) {
        pos = position;
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        if (caller) {
            i = new Intent(ViewPagerDemo.this, TabActivity.class);
            startActivity(i);
            finish();
        } else {
            finish();
        }
    }
}
