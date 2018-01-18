package com.example.android.myfirstapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zifanfeng on 12/22/17.
 */

public class FirstFragment extends Fragment {

    onFirstFragmentListener mCallBack;
    public interface onFirstFragmentListener{
        public void onButtonClicked(int num);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mCallBack = (onFirstFragmentListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement onFirstFragmentListener");
        }
    }
    View view;
    Button firstButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_first, container, false);
        firstButton = (Button) view.findViewById(R.id.firstButton);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "First", Toast.LENGTH_LONG).show();
                mCallBack.onButtonClicked(1000);
            }
        });
        return view;
    }
}
