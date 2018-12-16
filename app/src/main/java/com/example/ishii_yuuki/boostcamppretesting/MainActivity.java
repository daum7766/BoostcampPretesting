package com.example.ishii_yuuki.boostcamppretesting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private EditText searchText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    String search = "";
    public LinkedList<MovieItems> movieItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieItems = new LinkedList<MovieItems>();


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchText = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.movieView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ;



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = searchText.getText().toString();
                searchStart searchStart = new searchStart();
                searchStart.execute();

            }
        });



    }


    class searchStart extends AsyncTask<Void, Void, Void>
    {
        ApiData apiData;
        JSONObject jsonObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            apiData = new ApiData();
            movieItems.clear();
        }//시작할때

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String text = URLEncoder.encode(search, "utf-8");
                String apiURL = apiData.getUrl() + "?query=" + text + "&display=" + 100 + "&";

                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", apiData.getClientId());
                con.setRequestProperty("X-Naver-Client-Secret", apiData.getClientSecret());
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                jsonObject = new JSONObject(String.valueOf(sb));
                br.close();
                con.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
            try
            {
                JSONArray jsonArray= jsonObject.getJSONArray("items");
                for(int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObjectM = jsonArray.getJSONObject(i);
                    String title = jsonObjectM.getString("title");
                    String imageUrl = jsonObjectM.getString("image");
                    Bitmap image = null;
                    try{
                        InputStream in = new java.net.URL(imageUrl).openStream();
                        image = BitmapFactory.decodeStream(in);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }

                    float userRating =  Float.parseFloat(jsonObjectM.getString("userRating"));
                    String actor = jsonObjectM.getString("actor");
                    String director = jsonObjectM.getString("director");
                    String link = jsonObjectM.getString("link");
                    String year = jsonObjectM.getString("pubDate");

                    movieItems.add(new MovieItems(title, director, actor, userRating, image, link, year));
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerAdapter = new RecyclerAdapter(movieItems);
            recyclerView.setAdapter(recyclerAdapter);

        }//끝났을때


    }


}
