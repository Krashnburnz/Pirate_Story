package com.tinyrealm.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

//******************************************************************************
// swipe:: Swipe example program to demonstrate page turning...
// Edwin FG. Armstrong AKA (efa)
// -----------------------------------------------------------------------------
// Date     Notes                                                       who
// ======== ==========================================================  ===
// 02/07/14 Got paging working with buttons and views.                  efa
// 02/08/14 Paging working with fragments. Help fragment not working.   efa
// 02/09/14 Removed help buttons and page. Conflicts w/swipes.          efa
//******************************************************************************

public class MainActivity extends FragmentActivity {
    private PagerAdapter mPagerAdapter;
    private ViewPager pager;
    Fragment fragTitle;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;
    Fragment fragment6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.activity_main);

        //initialise the pager
       this.initialisePaging();
    }

     //***************************************************************
     // initialisePaging:: Initialise the fragments to be paged.
     // last updated 2/09/14 (efa)
     //***************************************************************
     private void initialisePaging() {
         final PagerTabStrip strip = PagerTabStrip.class.cast(super.findViewById(R.id.pts_main));
         strip.setDrawFullUnderline(false);
         strip.setTabIndicatorColor(Color.DKGRAY);
         strip.setBackgroundColor(Color.GRAY);
         strip.setNonPrimaryAlpha(0.5f);
         strip.setTextSpacing(25);
         strip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

         List<Fragment> fragments = new ArrayList<Fragment>();

         fragTitle = Fragment.instantiate(this, FragmentTitle.class.getName());
         fragments.add(fragTitle);

         fragment1 = Fragment.instantiate(this, Fragment1.class.getName());
         fragments.add(fragment1);

         fragment2 = Fragment.instantiate(this, Fragment2.class.getName());
         fragments.add(fragment2);

         fragment3 = Fragment.instantiate(this, Fragment3.class.getName());
         fragments.add(fragment3);

         fragment4 = Fragment.instantiate(this, Fragment4.class.getName());
         fragments.add(fragment4);

         fragment5 = Fragment.instantiate(this, Fragment5.class.getName());
         fragments.add(fragment5);

         fragment6 = Fragment.instantiate(this, Fragment6.class.getName());
         fragments.add(fragment6);

            /*
         fragments.add(Fragment.instantiate(this, Fragment1.class.getName()));
         fragments.add(Fragment.instantiate(this, Fragment2.class.getName()));
         fragments.add(Fragment.instantiate(this, Fragment3.class.getName()));
         fragments.add(Fragment.instantiate(this, Fragment4.class.getName()));
         fragments.add(Fragment.instantiate(this, Fragment5.class.getName()));
         fragments.add(Fragment.instantiate(this, Fragment6.class.getName()));
         */

         this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
         //
         pager = (ViewPager)super.findViewById(R.id.viewPager);
         pager.setAdapter(this.mPagerAdapter);
         pager.setPageTransformer(true, new CardTransformer(0.7f));
         pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {

                 switch (position) {
                     case 0:
                         fragment1.onPause();
                         fragment2.onPause();
                         fragment3.onPause();
                         fragment4.onPause();
                         fragment5.onPause();
                         fragment6.onPause();
                         break;
                     case 1:
                         fragTitle.onPause();
                         fragment2.onPause();
                         fragment3.onPause();
                         fragment4.onPause();
                         fragment5.onPause();
                         fragment6.onPause();
                         break;
                     case 2:
                         fragTitle.onPause();
                         fragment1.onPause();
                         fragment3.onPause();
                         fragment4.onPause();
                         fragment5.onPause();
                         fragment6.onPause();
                         break;
                     case 3:
                         fragTitle.onPause();
                         fragment1.onPause();
                         fragment2.onPause();
                         fragment4.onPause();
                         fragment5.onPause();
                         fragment6.onPause();
                         break;
                     case 4:
                         fragTitle.onPause();
                         fragment1.onPause();
                         fragment2.onPause();
                         fragment3.onPause();
                         fragment5.onPause();
                         fragment6.onPause();
                         break;
                     case 5:
                         fragTitle.onPause();
                         fragment1.onPause();
                         fragment2.onPause();
                         fragment3.onPause();
                         fragment4.onPause();
                         fragment6.onPause();
                         break;
                     case 6:
                         fragTitle.onPause();
                         fragment1.onPause();
                         fragment2.onPause();
                         fragment3.onPause();
                         fragment4.onPause();
                         fragment5.onPause();
                         break;
                 }
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });

     }

    public void jumpToPageOne(View view) {
        pager.setCurrentItem(1);

    }

    public void jumpToPageTwo(View view) {

        pager.setCurrentItem(2);
    }

    public void jumpToPageThree(View view) {

        pager.setCurrentItem(3);
    }

    public void jumpToPageFour(View view) {

        pager.setCurrentItem(4);
    }

    public void jumpToPageFive(View view) {

        pager.setCurrentItem(5);
    }

    public void jumpToPageSix(View view) {

        pager.setCurrentItem(6);
    }

    public void jumpToPageTitle(View view) {

        pager.setCurrentItem(0);
    }

    //***************************************************************
    // CardTransformer borrowed from: Provides a fading effect for transitions.
    // https://gist.github.com/astuetz/4090135
    //***************************************************************

    class CardTransformer implements ViewPager.PageTransformer {

        private final float scalingStart;

        public CardTransformer(float scalingStart) {
            super();
            this.scalingStart = 1 - scalingStart;
        }

        @Override
        public void transformPage(View page, float position) {

            if (position >= 0) {
                final int w = page.getWidth();
                float scaleFactor = 1 - scalingStart * position;

                page.setAlpha(1 - position);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setTranslationX(w * (1 - position) - w);
            }
        }
    }
}