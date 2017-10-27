package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment.ChannelFragment;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment.QuoteFragment;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment.WallpaperFragment;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment.WebsiteFragment;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

public class MainActivity extends AppCompatActivity implements WallpaperFragment.OnFragmentInteractionListener,
        QuoteFragment.OnFragmentInteractionListener,
        ChannelFragment.OnFragmentInteractionListener{


    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiDex.install(this);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }


//        Interpolator sInterpolator = new DecelerateInterpolator();
//        try {
//            Field mScroller;
//            mScroller = ViewPager.class.getDeclaredField("mScroller");
//            mScroller.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(MainActivity.this, sInterpolator);
//            // scroller.setFixedDuration(5000);
//            mScroller.set(viewPager, scroller);
//        } catch (NoSuchFieldException e) {
//        } catch (IllegalArgumentException e) {
//        } catch (IllegalAccessException e) {
//        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public static final int TAB_NUM = 3;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return WallpaperFragment.newInstance(0, "Wallpaper");
                case 1:
                    return QuoteFragment.newInstance(1, "Quote");
                case 2:
                    return ChannelFragment.newInstance(3, "Videos");
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "WALLPAPERS";
                case 1:
                    return "QUOTES";
                case 2:
                    return "CHANNELS";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TAB_NUM;
        }
    }
}
