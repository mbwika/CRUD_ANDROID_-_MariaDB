package com.codesndata.crudapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo;
    EditText txtPID, txtPName, txtPrice;
    Button btnCreate, btnRead, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txtInfo);
        txtPID = findViewById(R.id.txtPID);
        txtPName = findViewById(R.id.txtPName);
        txtPrice = findViewById(R.id.txtPrice);
        btnCreate = findViewById(R.id.btnCreate);
        btnRead = findViewById(R.id.btnRead);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    String post_url = "http://192.168.43.58/CRUDAPI/create.php";

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse(post_url).newBuilder();
                    urlBuilder.addQueryParameter("PName", txtPName.getText().toString());
                    urlBuilder.addQueryParameter("Price", txtPrice.getText().toString());

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        txtInfo.setText(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/CRUDAPI/read.php").newBuilder();
                    urlBuilder.addQueryParameter("PID", txtPID.getText().toString());

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        //txtInfo.setText(response.body().string());

                                        try {
                                            String data = response.body().string();

                                            JSONArray jsonArray = new JSONArray(data);
                                            JSONObject jsonObject;

                                            jsonObject = jsonArray.getJSONObject(0);

                                            txtPID.setText(jsonObject.getString("ProductID"));
                                            txtPName.setText(jsonObject.getString("PName"));
                                            txtPrice.setText(jsonObject.getString("Price"));

                                        } catch (JSONException e) {
                                            txtInfo.setText(e.getMessage());
                                        }


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/CRUDAPI/update.php").newBuilder();
                    urlBuilder.addQueryParameter("PID", txtPID.getText().toString());
                    urlBuilder.addQueryParameter("PName", txtPName.getText().toString());
                    urlBuilder.addQueryParameter("Price", txtPrice.getText().toString());

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        txtInfo.setText(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/CRUDAPI/delete.php").newBuilder();
                    urlBuilder.addQueryParameter("PID", txtPID.getText().toString());

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        txtInfo.setText(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
