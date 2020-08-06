package com.example.disinfectantrobotcontrollerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    Button forwardButton,stopButton,clearTextButton;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.linkTheElements();

        this.enableTheForwardButton();
        this.enableTheStopButton();
        this.enableTheClearTextButton();

    }
    void linkTheElements(){
        forwardButton=findViewById(R.id.forwardButton);
        stopButton=findViewById(R.id.stopButton);
        editText=findViewById(R.id.editText);
        clearTextButton=findViewById(R.id.clearTextButton);
    }
    void enableTheForwardButton(){
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="http://192.168.0.101/forward?pwm=50&uvl=1";
                //String url="http://scratchpads.eu/explore/sites-list";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                editText.setText("Response is: "+ response);
                                queue.getCache().clear();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        editText.setText("That didn't work!");
                        queue.getCache().clear();
                    }
                });
                queue.add(stringRequest);
            }
        });
    }
    void enableTheStopButton(){
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="http://192.168.0.101/stop?pwm=0&uvl=0";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                editText.setText("Response is: "+ response);
                                queue.getCache().clear();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        editText.setText("That didn't work!");
                        queue.getCache().clear();
                    }
                });
                queue.add(stringRequest);
            }
        });
    }

    void enableTheClearTextButton(){
        clearTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
    }
}