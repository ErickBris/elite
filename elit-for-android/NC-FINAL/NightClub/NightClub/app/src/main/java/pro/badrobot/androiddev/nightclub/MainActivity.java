package pro.badrobot.androiddev.nightclub;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> mNavigationTitles;
    CharSequence mTitle;
    DrawerLayout mDrawerLayout;
    FrameLayout mContentLayout;
    ListView mNavigationList;
    LinearLayout mNavigationLayout;
    ImageView mLogoImage;
    ActionBarDrawerToggle mDrawerToggle;
    RetainFragment mRetainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationTitles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.navigation_titles)));
        mRetainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(RetainFragment.RETAIN_FRAGMENT_TAG);
        if(mRetainFragment == null) {
            mRetainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(mRetainFragment,RetainFragment.RETAIN_FRAGMENT_TAG).commit();
        }
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mContentLayout = (FrameLayout)findViewById(R.id.content_layout);
        mNavigationLayout = (LinearLayout)findViewById(R.id.navigation_layout);
        mNavigationList = (ListView)mNavigationLayout.findViewById(R.id.navigation_list);
        mLogoImage = (ImageView)findViewById(R.id.nightclub_logo);

        Log.d("onCreate", mNavigationTitles.get(0));

        mNavigationList.setAdapter(new NavigationListAdapter(this, mNavigationTitles));
        mLogoImage.setOnClickListener(new EmptyClickListener());

        mNavigationList.setOnItemClickListener(new NavigationOnItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mTitle = getTitle();
        selectNavigationItem(0);
    }

    private class EmptyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }

    public RetainFragment getRetainFragment()
    {
        return mRetainFragment;
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle()
    {
        return mDrawerToggle;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private class NavigationOnItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mNavigationList.setItemChecked(position, true);
            selectNavigationItem(position);
        }

    }

    private void selectNavigationItem (int position)
    {
        Fragment fragment = null;
        Bundle args = new Bundle();
        args.putInt(NightClubFragment.ARG_POSITION, position);

        switch(mNavigationTitles.get(position))
        {
            case "events":
            {
                fragment = new EventListFragment();
                break;
            }
            case "mixes":
            {
                fragment = new MixesListFragment();
                break;
            }
            case "dj's":
            {
                fragment = new DjsListFragment();
                break;
            }
            case "news":
            {
                fragment = new NewsListFragment();
                break;
            }
            case "photo": {
                fragment = new PhotoListFragment();
                break;
            }
            case "about": {
                fragment = new AboutFragment();
                break;
            }
        }
        if(fragment == null)
            return;
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit();
        mNavigationList.setItemChecked(position, true);
        mTitle = mNavigationTitles.get(position);
        getSupportActionBar().setTitle(mTitle);
        mDrawerLayout.closeDrawer(mNavigationLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mDrawerToggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
