package com.softgen.jinwar.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softgen.jinwar.R;
import com.softgen.jinwar.beans.DharmashalaRoom;

import java.util.List;

public class AvailableRoomsRecyclerViewAdapter extends RecyclerView.Adapter<AvailableRoomsRecyclerViewAdapter.ViewHolder>{
    private List<DharmashalaRoom> availableRooms;

    private Context context;

    public AvailableRoomsRecyclerViewAdapter(Context context, List<DharmashalaRoom> availableRooms){
        this.context = context;
        this.availableRooms = availableRooms;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dharmashala_individual_room_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DharmashalaRoom dharmashalaRoom = availableRooms.get(position);

        String roomNumber = dharmashalaRoom.getRoomNumber();
        String washroomType = dharmashalaRoom.getWashroomType();
        String coolingSystem = dharmashalaRoom.getCoolingSystem();

        holder.roomNumber.setText(roomNumber);
        holder.washroomType.setText(washroomType);
        holder.coolingSystem.setText(coolingSystem);
    }

    @Override
    public int getItemCount() {
        return availableRooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView roomID;
        TextView roomNumber;
        TextView washroomType;
        TextView coolingSystem;

        TextView roomBaseCharges;
        TextView roomOnlineCharges;
        TextView roomPaymentCharges;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomNumber = itemView.findViewById(R.id.roomNumber);
            washroomType = itemView.findViewById(R.id.coolingSystem);
            coolingSystem = itemView.findViewById(R.id.washroomType);
        }
    }
}
