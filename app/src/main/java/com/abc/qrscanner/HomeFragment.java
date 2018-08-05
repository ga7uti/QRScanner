package com.abc.qrscanner;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;


public class HomeFragment extends Fragment {

    private QrDetails qrDetails;

    private TextView displayResult;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        displayResult=view.findViewById(R.id.display_result);
        Bundle bundle=getArguments();
        String message=bundle.getString("result");
        qrDetails=new QrDetails();
        qrDetails.setName("");
        qrDetails.setNumber(message);
        qrDetails.setEmail("");
        qrDetails.setGender("");
        Gson gson=new Gson();
        String j=gson.toJson(qrDetails);

        displayResult.setText(j);

        return view;
    }

}
