package com.willhua.tomatowork.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.willhua.tomatowork.modle.entity.Note;
import com.willhua.tomatowork.ui.fragment.CandyListFragment;
import com.willhua.tomatowork.ui.fragment.NoteListFragment;
import com.willhua.tomatowork.ui.fragment.StatisticsFragment;
import com.willhua.tomatowork.ui.fragment.UserFragment;
import com.willhua.tomatowork.ui.fragment.WorkFragment;
import com.willhua.tomatowork.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willhua on 2016/11/19.
 */

public class FunctionPagerAdapter extends FragmentStatePagerAdapter {

    private final int PAGE_NUM;
    private List<Class> mPages;

    public FunctionPagerAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        fillPages();
        PAGE_NUM = mPages.size();
    }

    private void fillPages(){
        mPages = new ArrayList<>();
        mPages.add(Constants.POSITOIN_CANDY, CandyListFragment.class);
        mPages.add(Constants.POSITOIN_NOTE, NoteListFragment.class);
        mPages.add(Constants.POSITOIN_STAT, StatisticsFragment.class);
        mPages.add(Constants.POSITOIN_USER, UserFragment.class);
    }


    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mPages.get(position).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
