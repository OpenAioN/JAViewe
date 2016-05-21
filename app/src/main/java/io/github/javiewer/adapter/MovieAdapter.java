package io.github.javiewer.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.javiewer.R;
import io.github.javiewer.network.wrapper.MovieWrapper;

/**
 * Author: MagicDroidX
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading()
            .cacheInMemory()
            .cacheOnDisc()
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
            .bitmapConfig(Bitmap.Config.ARGB_8888) // default
            .delayBeforeLoading(1000)
            .displayer(new FadeInBitmapDisplayer(500)) // default
            .build();

    private List<MovieWrapper> movies;

    public MovieAdapter(List<MovieWrapper> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MovieWrapper movie = movies.get(position);

        holder.parse(movie);

        holder.mImageCover.setTag(movie.image);
        ImageLoader.getInstance().displayImage(movie.image, holder.mImageCover, options);

        holder.mImageHot.setVisibility(movie.hot ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.movie_title)
        public TextView mTextTitle;

        @Bind(R.id.movie_code)
        public TextView mTextCode;

        @Bind(R.id.movie_time)
        public TextView mTextTime;

        @Bind(R.id.movie_cover)
        public ImageView mImageCover;

        @Bind(R.id.movie_hot)
        public ImageView mImageHot;

        public void parse(MovieWrapper movie) {
            mTextCode.setText(movie.code);
            mTextTitle.setText(movie.title);
            mTextTime.setText(movie.time);
        }

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}