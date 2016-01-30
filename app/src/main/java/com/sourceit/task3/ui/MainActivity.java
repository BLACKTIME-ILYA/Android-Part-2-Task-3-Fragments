package com.sourceit.task3.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sourceit.task3.R;
import com.sourceit.task3.utils.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int UPDATE_TIME = 1000;
    private final int MSG = 1;
    private List<MyFragment> fragments = new ArrayList<>();
    private final int CYCLE_FIRST_COUNT = 0;
    private int visibleVariableCount = 0;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MyFragment fragment1;
    MyFragment fragment2;
    MyFragment fragment3;
    MyFragment fragment4;

    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            L.d("Message");
            if (msg.what == MSG) {
                setActiveFragment();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment1 = MyFragment.newInstance();
        fragment2 = MyFragment.newInstance();
        fragment3 = MyFragment.newInstance();
        fragment4 = MyFragment.newInstance();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);

        fragmentTransaction.add(R.id.activity_container, fragment1, "fragment1");
        fragmentTransaction.add(R.id.activity_container, fragment2, "fragment2");
        fragmentTransaction.add(R.id.activity_container, fragment3, "fragment3");
        fragmentTransaction.add(R.id.activity_container, fragment4, "fragment4");
        for (MyFragment fragment : fragments) {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commit();
        L.d("commit...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(MSG, UPDATE_TIME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(MSG);
    }

    private void setActiveFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        visibleVariable();
        fragmentTransaction.commit();
        handler.sendEmptyMessageDelayed(MSG, UPDATE_TIME);
    }

    private void visibleVariable() {

        if (visibleVariableCount == CYCLE_FIRST_COUNT) {
            fragmentTransaction.hide(fragments.get(fragments.size() - 1));
        } else fragmentTransaction.hide(fragments.get(visibleVariableCount - 1));

        fragmentTransaction.show(fragments.get(visibleVariableCount));

        if (visibleVariableCount != fragments.size() - 1) {
            visibleVariableCount++;
        } else visibleVariableCount = CYCLE_FIRST_COUNT;
    }

    public String getText(String tag) {
        L.d("getText..." + tag);
        if (tag.equals("fragment1")) {
            return "Fragment Number One";
        } else if (tag.equals("fragment2")) {
            return "Fragment Number Two";
        } else if (tag.equals("fragment3")) {
            return "Fragment Number Three";
        } else if (tag.equals("fragment4")) {
            return "Fragment Number Four";
        } else
            return "";
    }
}
