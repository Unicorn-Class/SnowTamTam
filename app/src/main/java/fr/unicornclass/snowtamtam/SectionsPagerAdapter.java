package fr.unicornclass.snowtamtam;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import model.Airport;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private int count = 0;
    private List<Airport> listAirports;

    public SectionsPagerAdapter(Context context, FragmentManager fm, List<Airport> list) {
        super(fm);
        mContext = context;
        Log.d("LIST",list.toString());
        count = list.size();
        listAirports = list;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, listAirports.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listAirports.get(position).getOaciCode();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return count;
    }
}