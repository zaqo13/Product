package com.example.product.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Objects.ProfileItems;
import com.example.product.R;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private List<ProfileItems> profileList;

    public ProfileAdapter(List<ProfileItems> profileList) {
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileItems profile = profileList.get(position);

        holder.nameTextView.setText(profile.getName());
        holder.mobileNumberTextView.setText(profile.getMobileNumber());
        holder.emailTextView.setText(profile.getEmail());
        holder.passwordTextView.setText(profile.getPassword());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView mobileNumberTextView;
        TextView emailTextView;
        TextView passwordTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            mobileNumberTextView = itemView.findViewById(R.id.tv_mobile);
            emailTextView = itemView.findViewById(R.id.tv_email);
            passwordTextView = itemView.findViewById(R.id.tv_pwd);
        }
    }
}
