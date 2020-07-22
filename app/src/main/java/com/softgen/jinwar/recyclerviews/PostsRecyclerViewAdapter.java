package com.softgen.jinwar.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softgen.jinwar.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder> {
    private List<String> postOwnersNames;
    private List<String> postOwnersPicture;
    private List<String> postContents;

    private Context context;

    public PostsRecyclerViewAdapter(Context context, List<String> posts, List<String> postOwnersPicture, List<String> postContents) {
        this.context = context;
        this.postOwnersNames = posts;
        this.postOwnersPicture = postOwnersPicture;
        this.postContents = postContents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_post_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println("Called..");

        Glide.with(context).asBitmap().load(postOwnersPicture.get(position)).into(holder.postOwnerPicture);

        holder.postOwnerName.setText(postOwnersNames.get(position));
        holder.postContent.setText(postContents.get(position));
    }

    @Override
    public int getItemCount() {
        return postOwnersNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView postOwnerName;
        TextView postContent;
        CircleImageView postOwnerPicture;
        ConstraintLayout individualPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postOwnerName = itemView.findViewById(R.id.postOwnerName);
            postOwnerPicture = itemView.findViewById(R.id.postOwnerPicture);
            postContent = itemView.findViewById(R.id.postContent);
            individualPost = itemView.findViewById(R.id.individualPost);
        }
    }
}
