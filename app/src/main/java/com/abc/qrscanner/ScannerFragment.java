package com.abc.qrscanner;



import android.Manifest;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerFragment extends Fragment {

    private CodeScanner codeScanner;



    public ScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_scanner, container, false);

        scanCode(view);

        return view;
    }

    private void scanCode(View view){
        final CodeScannerView scannerView = view.findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(getActivity(), scannerView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getRequest(result.getText());
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
    }

    private void getRequest(String message){
        Map<String, String> postParam= new HashMap<>();
        postParam.put("esp_id", message);
        Uri.Builder builder=new Uri.Builder();
        builder.scheme("http")
                .authority("192.168.0.102")
                .appendPath("api.php")
                .appendQueryParameter("esp_id",message);
        final String url=builder.build().toString();
        Log.d("Request",url);


        Log.d("Request",new JSONArray(Arrays.asList(postParam)).toString());

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url,new JSONArray(Arrays.asList(postParam)), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Request","DOne");
                try {

                    JSONObject jsonObject =response.getJSONObject(0);
                    HomeFragment homeFragment=new HomeFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("name",jsonObject.getString("name"));
                    bundle.putString("email",jsonObject.getString("email"));
                    bundle.putString("qrcode",jsonObject.getString("qrcode"));
                    homeFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,homeFragment,null);
                    fragmentTransaction.addToBackStack(null).commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error :" + error.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Request",error.getMessage());
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(jsonArrayRequest);

    }
    @Override
    public void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }


}
