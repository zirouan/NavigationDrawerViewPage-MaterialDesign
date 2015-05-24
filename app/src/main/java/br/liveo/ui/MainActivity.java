package br.liveo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseIntArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.OnItemClickListener;
import br.liveo.navigationliveo.NavigationLiveo;
import br.liveo.navigationviewpagerliveo.R;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private List<String> mListNameItem;

    @Override
    public void onInt(Bundle bundle) {

        // User Information
        this.userName.setText("Rudson Lima");
        this.userEmail.setText("rudsonlive@gmail.com");
        this.userPhoto.setImageResource(R.drawable.ic_rudsonlive);
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        // name of the list items
        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.inbox));
        mListNameItem.add(1, getString(R.string.starred));
        mListNameItem.add(2, getString(R.string.sent_mail));
        mListNameItem.add(3, getString(R.string.drafts));
        mListNameItem.add(4, getString(R.string.more_markers)); //This item will be a subHeader
        mListNameItem.add(5, getString(R.string.trash));
        mListNameItem.add(6, getString(R.string.spam));

        // icons list items
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.mipmap.ic_inbox_black_24dp);
        mListIconItem.add(1, R.mipmap.ic_star_black_24dp); //Item no icon set 0
        mListIconItem.add(2, R.mipmap.ic_send_black_24dp); //Item no icon set 0
        mListIconItem.add(3, R.mipmap.ic_drafts_black_24dp);
        mListIconItem.add(4, 0); //When the item is a subHeader the value of the icon 0
        mListIconItem.add(5, R.mipmap.ic_delete_black_24dp);
        mListIconItem.add(6, R.mipmap.ic_report_black_24dp);

        //{optional} - Among the names there is some subheader, you must indicate it here
        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(4);

        //{optional} - Among the names there is any item counter, you must indicate it (position) and the value here
        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
        mSparseCounterItem.put(0, 7);
        mSparseCounterItem.put(1, 123);
        mSparseCounterItem.put(6, 250);

        this.setElevationToolBar(this.getCurrentPosition() != 1 ? 15 : 0);

        with(this).startingPosition(1) //Starting position in the list
                .nameItem(mListNameItem)
                .iconItem(mListIconItem)
                .headerItem(mListHeaderItem)
                .countItem(mSparseCounterItem)
                .setOnClickUser(onClickPhoto)
                .setOnClickFooter(onClickFooter)
                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)
                .build();
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 1:
                mFragment = new ViewPagerFragment();
                break;

            default:
                mFragment = MainFragment.newInstance(mListNameItem.get(position));
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 1 ? 15 : 0);
    }

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };
}
