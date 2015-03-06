package br.liveo.adapter;

import android.support.v4.app.Fragment;

import br.liveo.ui.MainFragment;

public class TabPagerItem {

    private final CharSequence mTitle;
    private final int position;

    private Fragment[] listFragments;

    public TabPagerItem(int position, CharSequence title) {
        this.mTitle = title;
        this.position = position;

        listFragments = new Fragment[]{new MainFragment().newInstance(title.toString()), new MainFragment().newInstance(title.toString()), new MainFragment().newInstance(title.toString())};
    }

    public Fragment createFragment() {
        return listFragments[position];
    }

    public CharSequence getTitle() {
        return mTitle;
    }
}
