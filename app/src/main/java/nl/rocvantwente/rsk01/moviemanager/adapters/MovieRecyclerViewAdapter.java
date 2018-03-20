package nl.rocvantwente.rsk01.moviemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.rocvantwente.rsk01.moviemanager.R;
import nl.rocvantwente.rsk01.moviemanager.activities.MainActivity;
import nl.rocvantwente.rsk01.moviemanager.activities.MovieDetailActivity;
import nl.rocvantwente.rsk01.moviemanager.models.Movie;

/**
 * Created by Marcel on 3-3-2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());
        String imagePath = movie.getPosterPath();
        Picasso.with(getContext())
                .load(imagePath)
                .into(holder.ivMovieImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getContext() , "Something went wrong" , Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.ivMovieImage)
        ImageView ivMovieImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvOverview)
        TextView tvOverview;
        @BindView(R.id.cvMovie)
        CardView cvMovie;

        ViewHolder(View view) {
            super(view);
//            ivMovieImage = (ImageView) view.findViewById(R.id.ivMovieImage);
//            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//            tvOverview = (TextView) view.findViewById(R.id.tvOverview);
//            cvMovie = (CardView) view.findViewById(R.id.cvMovie);
            ButterKnife.bind(this , view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Movie movie = movies.get(getAdapterPosition());

            Intent intent = new Intent(getContext() , MovieDetailActivity.class);
            intent.putExtra("MOVIE" , movie);
            getContext().startActivity(intent);
        }
    }


}
