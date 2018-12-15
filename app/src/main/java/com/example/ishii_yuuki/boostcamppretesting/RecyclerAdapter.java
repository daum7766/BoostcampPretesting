package com.example.ishii_yuuki.boostcamppretesting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.Vector;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>
{
    Vector<MovieItems> movieItems;
    public RecyclerAdapter(Vector<MovieItems> movieItems){
        this.movieItems = movieItems;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item ,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.titleText.setText(Html.fromHtml(movieItems.get(position).getTitle()));
        holder.actorText.setText(movieItems.get(position).getActor());
        holder.directorText.setText(movieItems.get(position).getDirector());
        holder.ratingBar.setRating(movieItems.get(position).getUserRating());
        holder.imageView.setImageBitmap(movieItems.get(position).getImage());
        holder.yearText.setText(movieItems.get(position).getYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( movieItems.get(position).getLink()  ));
                view.getContext().startActivity(intent);
            }
        });
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    // 커스텀 뷰홀더
// item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private RatingBar ratingBar;
        private  TextView titleText;
        private TextView directorText;
        private TextView actorText;
        private TextView yearText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.urlImage);
            ratingBar = itemView.findViewById(R.id.rating);
            titleText = itemView.findViewById(R.id.titleText);
            directorText = itemView.findViewById(R.id.directorText);
            actorText = itemView.findViewById(R.id.actorText);
            yearText = itemView.findViewById(R.id.yearText);

        }
    }

}
