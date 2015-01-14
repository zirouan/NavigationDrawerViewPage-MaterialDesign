package br.liveo.navigationviewpagerliveo;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.liveo.fragment.FragmentMain;
import br.liveo.fragment.FragmentViewPager;
import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;

public class NavigationMain extends NavigationLiveo implements NavigationLiveoListener {

    private List<String> mListNameItem;

    @Override
    public void onInt(Bundle bundle) {
        this.setNavigationListener(this);

        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.inbox));
        mListNameItem.add(1, getString(R.string.starred));
        mListNameItem.add(2, getString(R.string.sent_mail));
        mListNameItem.add(3, getString(R.string.drafts));
        mListNameItem.add(4, getString(R.string.more_markers)); //This item will be a subHeader
        mListNameItem.add(5, getString(R.string.trash));
        mListNameItem.add(6, getString(R.string.spam));

        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_inbox_black_24dp);
        mListIconItem.add(1, R.drawable.ic_star_black_24dp);
        mListIconItem.add(2, R.drawable.ic_send_black_24dp);
        mListIconItem.add(3, R.drawable.ic_drafts_black_24dp);
        mListIconItem.add(4, 0); //When the item is a subHeader the value of the icon 0
        mListIconItem.add(5, R.drawable.ic_delete_black_24dp);
        mListIconItem.add(6, R.drawable.ic_report_black_24dp);

        //{optional}
        List<Integer> mListHeaderItem = new ArrayList<>(); //indicate who the items is a subheader
        mListHeaderItem.add(4);

        //{optional}
        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
        mSparseCounterItem.put(0, 7);
        mSparseCounterItem.put(6, 250);

        //remove the shadow of the toolbar since I'm using tabs
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getToolbar().setElevation(0);
        }

        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
        this.setFooterInformationDrawer(R.string.settings, R.drawable.ic_settings_black_24dp);

        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);
    }

    @Override
    public void onUserInformation() {
        this.mUserName.setText("Rudson Lima");
        this.mUserEmail.setText("rudsonlive@gmail.com");
        this.mUserPhoto.setImageResource(R.drawable.ic_rudsonlive);
        this.mUserBackground.setImageResource(R.drawable.ic_user_background);
    }

    @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {

        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){

            case 1:
                mFragment = new FragmentViewPager();
                break;

            default:
                mFragment = new FragmentMain().newInstance(mListNameItem.get(position));
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(layoutContainerId, mFragment).commit();
        }
    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int position, boolean visible) {
        switch (position) {
            case 0:
                menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);
                break;
        }
    }

    @Override
    public void onClickFooterItemNavigation(View v) {
        Toast.makeText(this, R.string.open_settings, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickUserPhotoNavigation(View v) {
        Toast.makeText(this, R.string.open_user_profile, Toast.LENGTH_SHORT).show();
    }
}
