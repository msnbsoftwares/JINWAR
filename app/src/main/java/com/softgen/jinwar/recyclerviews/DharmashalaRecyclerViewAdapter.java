package com.softgen.jinwar.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softgen.jinwar.R;
import com.softgen.jinwar.beans.Dharmashala;

import java.util.List;

public class DharmashalaRecyclerViewAdapter extends RecyclerView.Adapter<DharmashalaRecyclerViewAdapter.ViewHolder>{
    private List<Dharmashala> availableDharmashalas;

    private Context context;

    public DharmashalaRecyclerViewAdapter(Context context, List<Dharmashala> availableDharmashalas){
        this.context = context;
        this.availableDharmashalas = availableDharmashalas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dharmashala_individual_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dharmashala dharmashalaDetails = availableDharmashalas.get(position);

        String dharmashalaID = dharmashalaDetails.getDharmashalaID();
        String dharmashalaName = dharmashalaDetails.getDharmashalaName();
        String dharmashalaAddress = dharmashalaDetails.getDharmashalaAddress();
        String dharmashalaCity = dharmashalaDetails.getCityName();
        String dharmashalaState = dharmashalaDetails.getStateName();

        holder.dharmashalaID.setText(dharmashalaID);
        holder.dharmashalaName.setText(dharmashalaName);
        holder.dharmashalaAddress.setText(dharmashalaAddress);
        holder.dharmashalaCity.setText(dharmashalaCity);
        holder.dharmashalaState.setText(dharmashalaState);
    }

    @Override
    public int getItemCount() {
        return availableDharmashalas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dharmashalaID;
        TextView dharmashalaName;
        TextView dharmashalaCity;
        TextView dharmashalaState;
        TextView dharmashalaAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dharmashalaID = itemView.findViewById(R.id.dharmashalaID);
            dharmashalaName = itemView.findViewById(R.id.dharmashalaName);
            dharmashalaCity = itemView.findViewById(R.id.dharmashalaCity);
            dharmashalaState = itemView.findViewById(R.id.dharmashalaState);
            dharmashalaAddress = itemView.findViewById(R.id.dharmashalaAddress);
        }
    }
}
