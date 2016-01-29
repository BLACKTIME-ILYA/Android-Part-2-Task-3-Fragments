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
    private int visibleVariableCount = 1;
    private List<MyFragment> fragments = new ArrayList<>();

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
        handler.sendEmptyMessageDelayed(MSG, UPDATE_TIME);
    }

    private void setActiveFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        visibleVariable(visibleVariableCount);
        fragmentTransaction.commit();
        handler.sendEmptyMessageDelayed(MSG, UPDATE_TIME);
    }

    private void visibleVariable(int count) {
        for (MyFragment fragment : fragments) {
            if (fragment.getTag().equals("fragment" + count)) {
                fragmentTransaction.show(fragment);
            } else fragmentTransaction.hide(fragment);
        }
        if (visibleVariableCount != 4) {
            visibleVariableCount++;
        } else visibleVariableCount = 1;
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
