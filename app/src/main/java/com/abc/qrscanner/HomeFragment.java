package com.abc.qrscanner;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class HomeFragment extends Fragment{

    private TextView displayName,displayEmail,displayQrcode;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        displayName=view.findViewById(R.id.display_name_text);
        displayEmail=view.findViewById(R.id.display_email_txt);
        displayQrcode=view.findViewById(R.id.display_qrcode_txt);
        Bundle bundle=getArguments();
        displayName.setText(bundle.getString("name"));
        displayEmail.setText(bundle.getString("email"));
        displayQrcode.setText(bundle.getString("qrcode"));
        return view;
    }

}
