package com.sourceit.task3.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceit.task3.R;

public class MyFragment extends Fragment {

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    private TextView text;
    private String textValue;

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        textValue = ((MainActivity) getActivity()).getText(MyFragment.this.getTag());
        text = (TextView) view.findViewById(R.id.textView_FragmentNumber);
        text.setText(textValue);

        return view;
    }
}
