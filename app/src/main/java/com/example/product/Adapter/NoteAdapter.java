package com.example.product.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Objects.ProfileItems;
import com.example.product.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<ProfileItems> profileItemsArrayList;
    MaterialCardView m_card_view_profile;
    MaterialButton material_btn_profile;
    LinearLayout ll_profile_design;
    boolean isExpandable = false;


    public NoteAdapter(List<ProfileItems> profileItems) {
        this.profileItemsArrayList = profileItems;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_note, parent, false);


        return new NoteViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        ProfileItems profileItems1 = profileItemsArrayList.get(position);

        holder.material_btn_note_text.setText(profileItems1.getName());
        holder.tv_name.setText(profileItems1.getName());
        holder.tv_mobile.setText(profileItems1.getMobileNumber());
        holder.tv_email.setText(profileItems1.getEmail());
        holder.tv_pwd.setText(profileItems1.getPassword());

        // Set visibility of ll_profile_design based on expanded state
        if (profileItems1.isExpanded()) {
            holder.ll_note_design.setVisibility(View.VISIBLE);
        } else {
            holder.ll_note_design.setVisibility(View.GONE);
        }

        holder.material_btn_note_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileItems1.setExpanded(!profileItems1.isExpanded());
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return profileItemsArrayList.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_mobile, tv_email, tv_pwd;
        MaterialButton material_btn_note_text;
        LinearLayout ll_note_design;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.note_name);
            tv_mobile = itemView.findViewById(R.id.note_mobile);
            tv_email = itemView.findViewById(R.id.note_email);
            tv_pwd = itemView.findViewById(R.id.note_pwd);
            material_btn_note_text = itemView.findViewById(R.id.material_btn_profile);
            ll_note_design = itemView.findViewById(R.id.ll_note_design);


        }
    }

}




