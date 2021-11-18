package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_textMovieId,lbl_textOriginalLanguage,lbl_textReleaseDate,lbl_textOriginalTitle,lbl_desc,lbl_title, lbl_popularity, lbl_voteaverage
                    ,lbl_vote_count, lbl_genre;
    private String movie_id;
    private ImageView img_poster;
    private MovieViewModel ViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ViewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);

        Intent intent = getIntent();
          movie_id = intent.getStringExtra("movie_id");

        lbl_title = findViewById(R.id.lbl_title_fragment);
        lbl_textMovieId = findViewById(R.id.lbl_movie_details);
        lbl_desc = findViewById(R.id.lbl_desc_fragment);
        lbl_textOriginalTitle = findViewById(R.id.lbl_original_title_details);
        lbl_textReleaseDate = findViewById(R.id.lbl_releasedate_fragment);
        lbl_textOriginalLanguage = findViewById(R.id.lbl_original_language_details);
        lbl_popularity = findViewById(R.id.popularity_details);
        lbl_voteaverage = findViewById(R.id.vote_avg);
        lbl_vote_count = findViewById(R.id.vote_count);
        img_poster = findViewById(R.id.img_poster_details);
        lbl_genre = findViewById(R.id.lbl_genre_fragment);


        ViewModel.getMovieById(movie_id);
        ViewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, show_MovieDetails);

    }

    private Observer<Movies> show_MovieDetails = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String OriTitle = movies.getOriginal_title();
            String ReleaseDate = movies.getRelease_date();
            String id = String.valueOf(movies.getId());
            String OriLang = movies.getOriginal_language();
            String title = movies.getTitle();
            String deskripsi = movies.getOverview();
            String voteAvg = String.valueOf(movies.getVote_average());
            String popular = String.valueOf(movies.getPopularity());
            String voteCount = String.valueOf(movies.getVote_count());

            String genre = "";
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();

            Glide.with(MovieDetailsActivity.this).load(img_path).into(img_poster);
            for (int i = 0; i<movies.getGenres().size(); i++) {
                if (i == movies.getGenres().size() - 1) {
                    genre += movies.getGenres().get(i).getName();
                }else{
                    genre += movies.getGenres().get(i).getName()+", ";
                }
            }

            lbl_title.setText(title);
            lbl_textMovieId.setText(id);
            lbl_textOriginalTitle.setText(OriTitle);
            lbl_textReleaseDate.setText(ReleaseDate);
            lbl_textOriginalLanguage.setText(OriLang);
            lbl_popularity.setText(popular);
            lbl_desc.setText(deskripsi);
            lbl_voteaverage.setText(voteAvg);
            lbl_vote_count.setText(voteCount);
            lbl_genre.setText(genre);


        }
    };




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}