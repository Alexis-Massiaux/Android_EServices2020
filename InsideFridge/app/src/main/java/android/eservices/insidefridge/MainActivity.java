package android.eservices.insidefridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.eservices.insidefridge.presentation.cockaildisplay.favorite.fragment.FavoriteFragment;
import android.eservices.insidefridge.presentation.cockaildisplay.fridge.fragment.FridgeFragment;
import android.eservices.insidefridge.presentation.cockaildisplay.search.fragment.SearchFragment;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewPagerAndTabs();
    }

    /**
     * Fill the method to get view references and initialize viewpager to display our fragments
     */
    private void setupViewPagerAndTabs() {
        PagerAdapter pagerAdapter;

        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        //TODO set adapter to viewpager and handle fragment change inside
        viewPager.setAdapter(pagerAdapter);

    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        //TODO we want two fragments with layouts : fragment_one, fragment_two.
        public Fragment getItem(int position) {
            if(position == 0)
                return new FavoriteFragment();
            else if(position == 2)
                return new FridgeFragment();
            return new SearchFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String tabTitles[] = new String[]{"Favoris", "Recherche", "Frigo"};

            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
