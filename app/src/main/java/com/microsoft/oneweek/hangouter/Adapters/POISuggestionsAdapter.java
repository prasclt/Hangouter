package com.microsoft.oneweek.hangouter.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.microsoft.oneweek.hangouter.Interfaces.POI;
import com.microsoft.oneweek.hangouter.R;

import java.util.List;

/**
 * Created by prmeno on 7/26/2017.
 */

public class POISuggestionsAdapter extends RecyclerView.Adapter<POISuggestionsAdapter.ViewHolder> {

    private List<? extends POI> pois;
    private POISelectionhandler poiSelectionHandler;

    public POISuggestionsAdapter(List<? extends POI> pois, POISelectionhandler poiSelectionhandler) {
        this.pois = pois;
        this.poiSelectionHandler = poiSelectionhandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder poiViewHolder = new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.poi, parent, false)
        );

        return poiViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(pois.get(position));
    }

    @Override
    public int getItemCount() {
        return pois.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poiImage;
        TextView poiName;
        RatingBar ratingBar;
        TextView poiInfo;
        POI poi;

        public ViewHolder(View itemView) {
            super(itemView);
            poiImage = itemView.findViewById(R.id.poiImage);
            poiName = itemView.findViewById(R.id.poiName);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            poiInfo = itemView.findViewById(R.id.poiInfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    poiSelectionHandler.onPOISelected(
                            pois.get(getLayoutPosition())
                    );
                }
            });
        }

        private void bindData(POI poi) {
            this.poi = poi;
            poiName.setText(poi.getName());
            ratingBar.setRating(poi.getRating());
            poiInfo.setText(poi.getInfoOnType());
            setThumbnail(poi.getThumbnailUri());
        }

        private void setThumbnail(Uri uri) {
            if (uri != null) {
                Glide.with(itemView.getContext())
                        .load(uri)
                        .apply(RequestOptions.circleCropTransform())
                        .into(poiImage);
                return;
            }

            Glide.with(itemView.getContext())
                    .load(R.drawable.restaurant_default)
                    .apply(RequestOptions.circleCropTransform())
                    .into(poiImage);
        }
    }

    public interface POISelectionhandler {
        public void onPOISelected(POI poi);
    }
}
