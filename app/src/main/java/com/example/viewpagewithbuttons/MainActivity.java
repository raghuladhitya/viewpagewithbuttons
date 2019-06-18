package com.example.viewpagewithbuttons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> f;
    int Position = 0;
    Button b1,b2,b3,b4;
    private static final int NUM_PAGES = 3;
    private FragmentStateAdapter pagerAdapter;
    ViewPager2 mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.Skip);
        b2 = (Button)findViewById(R.id.next);
        b3 = (Button)findViewById(R.id.intro_btn_finish);
        b4 = (Button)findViewById(R.id.Previous);

        f = new ArrayList<>();
        f.add(new TopRatedFragment());
        f.add(new GamesFragment());
        f.add(new MoviesFragment());

        mPager = (ViewPager2) findViewById(R.id.viewPager2);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        mPager.setAdapter(pagerAdapter);
        mPager.setUserInputEnabled(false);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(getItem(+1),true);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(getItem(-1),true);
            }
        });
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    b1.setVisibility(View.VISIBLE);         //skip
                    b2.setVisibility(View.VISIBLE);         //next
                    b3.setVisibility(View.INVISIBLE);            //finish
                    b4.setVisibility(View.INVISIBLE);            //previous
                } if (position == 2) {
                    b1.setVisibility(View.INVISIBLE);            //skip
                    b2.setVisibility(View.INVISIBLE);            //next
                    b3.setVisibility(View.VISIBLE);         //finish
                    b4.setVisibility(View.VISIBLE);         //previous
                }if(position >0 && position<2){
                    b1.setVisibility(View.INVISIBLE);            //skip
                    b2.setVisibility(View.VISIBLE);         //next
                    b3.setVisibility(View.INVISIBLE);            //finish
                    b4.setVisibility(View.VISIBLE);         //previous
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }


    private int getItem(int i) {
        return mPager.getCurrentItem() + i;
    }
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return f.get(position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
