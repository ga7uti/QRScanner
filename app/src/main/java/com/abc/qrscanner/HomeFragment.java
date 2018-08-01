package com.abc.qrscanner;


import android.Manifest;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {

    private Button scannerBtn;
    private TextView displayResult;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        scannerBtn=view.findViewById(R.id.scan_btn);
        scannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(),"Permission denied",Toast.LENGTH_SHORT).show();
                   ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
               }else {
                   ScannerFragment scannerFragment=new ScannerFragment();
                   FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                   fragmentTransaction.replace(R.id.fragment_container,scannerFragment,null).addToBackStack(null).commit();
               }

            }
        });

        return view;
    }

}
