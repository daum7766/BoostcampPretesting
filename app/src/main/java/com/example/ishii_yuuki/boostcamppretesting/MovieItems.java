package com.example.ishii_yuuki.boostcamppretesting;

import android.graphics.Bitmap;


public class MovieItems {

    String link;        //들어가는 주소
    String title;       //영화제목
    Bitmap image;       //이미지 url
    float userRating;     //평점
    String actor;       //출연배우
    String director;    //감독
    String year;        //연도


    public MovieItems(String title, String director, String actor, float userRating, Bitmap image, String link, String year )
    {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;
        this.image = image;
        this.link = link;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getImage() {
        return image;
    }

    public float getUserRating() {
        return userRating/2;
    }

    public String getActor() {
        return actor;
    }

    public String getDirector() {
        return director;
    }

}
