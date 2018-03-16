package org.naca.reserving;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fdelgado on 15/2/18.
 */

public class ReservingRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView mThumbnail;
    public TextView  mTitle;
    public TextView  mPrice;
    public TextView  mDirection;

    public ReservingRecyclerViewHolder(View itemView) {
        super(itemView);
        mThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        mTitle = itemView.findViewById(R.id.textViewTitle);
        mPrice = itemView.findViewById(R.id.textViewPrice);
        mDirection = itemView.findViewById(R.id.textViewDirection);
    }

    public ImageView getThumbnail() {
        return mThumbnail;
    }
}
