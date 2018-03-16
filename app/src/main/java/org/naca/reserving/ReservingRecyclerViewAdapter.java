package org.naca.reserving;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ReservingRecyclerViewAdapter extends RecyclerView.Adapter<ReservingRecyclerViewHolder> {

    private static final String LOG_TAG = ReservingRecyclerViewAdapter.class.getSimpleName();

    private List<House> mHouseList;
    private Context mContext;

    public ReservingRecyclerViewAdapter(Context context, List<House> houseList) {
        mHouseList = houseList;
        mContext = context;
    }

    @Override
    public ReservingRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(lp);

        ReservingRecyclerViewHolder reservingRecyclerViewHolder =
                new ReservingRecyclerViewHolder(view);

        return reservingRecyclerViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mHouseList != null ? mHouseList.size() : 0);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ReservingRecyclerViewHolder holder, int position) {
        // Obtenemos el elemento que va a estar en la posición pedida
        House houseItem = mHouseList.get(position);

        Log.d(LOG_TAG, "Processing: " + houseItem.getName() + " -> " + Integer.toString(position));

        // Pintamos el thumbnail en la pantalla
        try {

            Picasso.with(mContext).load(houseItem.getImage())
                    .error(R.drawable.placeholder)      // En caso de error
                    .placeholder(R.drawable.placeholder)// Mientras descarga
                    .into(holder.getThumbnail());

            holder.mTitle.setText(houseItem.getName());
            holder.mDirection.setText(houseItem.getDirection());
            holder.mPrice.setText(houseItem.getPrice() + mContext.getString(R.string.euro));
        } catch (Exception e) {
        }
    }

    public void loadNewData(List<House> houses) {
        mHouseList = houses;

        notifyDataSetChanged();
    }

    public House getPhoto(int position) {
        return (mHouseList != null ? mHouseList.get(position) : null);
    }
}





















