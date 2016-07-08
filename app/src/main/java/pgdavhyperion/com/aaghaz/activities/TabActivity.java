

package pgdavhyperion.com.aaghaz.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.extras.MyApplication;
import pgdavhyperion.com.aaghaz.fragments.FragmentEventDay1;
import pgdavhyperion.com.aaghaz.fragments.FragmentEventDay2;
import pgdavhyperion.com.aaghaz.fragments.FragmentSociety;
import pgdavhyperion.com.aaghaz.glideimages.GalleryActivity;
import pgdavhyperion.com.aaghaz.starperfomance.ViewPagerDemo;

public class TabActivity extends AppCompatActivity implements
        MaterialTabListener,
        NavigationView.OnNavigationItemSelectedListener {
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    public static final int SOCIETY_LIST = 0;
    public static final int DAY1_LIST = 1;
    public static final int DAY2_LIST = 2;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    private DrawerLayout drawer;

    public void setmUserLearnedDrawer(boolean mUserLearnedDrawer) {
        this.mUserLearnedDrawer = mUserLearnedDrawer;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUserLearnedDrawer = MyApplication.readFromPreferences(this, KEY_USER_LEARNED_DRAWER, false);
        mFromSavedInstanceState = savedInstanceState != null ? true : false;


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if(!mUserLearnedDrawer){
            drawer.openDrawer(navigationView);
            mUserLearnedDrawer = true;
            MyApplication.saveToPreferences(this, KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer);
        }
        navigationView.setNavigationItemSelectedListener(this);


        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i)) //.setIcon(adapter.getIcon(i))
                            .setTabListener(this));

        }
        viewPager.setCurrentItem(1);




        navigationView.setItemIconTintList(null);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(TabActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        Fragment switchFragment = null;


        if (id == R.id.nav_home) {
            Intent in = new Intent(TabActivity.this, HomeActivity.class);
            startActivity(in);
        }
        if (id == R.id.nav_gallery) {
            Intent in = new Intent(TabActivity.this, GalleryActivity.class);
            startActivity(in);
        }
        if (id == R.id.nav_culture) {
            viewPager.setCurrentItem(0);
            drawer.closeDrawers();
        }
        if (id == R.id.nav_event1) {
            viewPager.setCurrentItem(1);
            drawer.closeDrawers();
        }
        if (id == R.id.nav_event2) {
            viewPager.setCurrentItem(2);
            drawer.closeDrawers();
        }
        if (id == R.id.nav_register) {
            Intent in = new Intent(TabActivity.this, RegisterActivity.class);
            startActivity(in);
        }
        if (id == R.id.nav_timeline) {
            Intent in=new Intent(TabActivity.this,Timeline.class);
            startActivity(in);
        }
        if (id == R.id.nav_about) {
            Intent in = new Intent(TabActivity.this, AboutUs.class);
            startActivity(in);
        }
        if (id == R.id.nav_collegemap) {
            Intent in=new Intent(TabActivity.this,CollegeMapActivity.class);
            startActivity(in);
        }
        if (id == R.id.starnight) {
            Intent in=new Intent(TabActivity.this,ViewPagerDemo.class);
            startActivity(in);
        }
        if(id==R.id.nav_facebook)
        {
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse("https://www.facebook.com/aaghazpgdav"));
            Intent chooser= Intent.createChooser(in, "Open Link through...");
            startActivity(chooser);
        }
        if(id==R.id.nav_twitter)
        {
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse("https://twitter.com/Aaghaz16"));
            Intent chooser= Intent.createChooser(in, "Open Link through...");
            startActivity(chooser);
        }
        if(id==R.id.nav_instagram)
        {
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse("https://www.instagram.com/aaghaz16/"));
            Intent chooser= Intent.createChooser(in, "Open Link through...");
            startActivity(chooser);
        }
        if(id==R.id.nav_locate)
        {
            double latitude = 28.566785;
            double longitude = 77.251446;
            String label = "PGDAV College";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query =  "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        return true;
    }



    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
            switch (num) {
                case SOCIETY_LIST:
                    fragment = FragmentSociety.newInstance("", "");
                    break;
                case DAY1_LIST:
                    fragment = FragmentEventDay1.newInstance("", "");
                    break;
                case DAY2_LIST:
                    fragment = FragmentEventDay2.newInstance("", "");
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

    }
}
