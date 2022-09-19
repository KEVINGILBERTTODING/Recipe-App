package com.example.recipe_app.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.swipe.SwipeLayout;
import com.example.recipe_app.Admin.Interface.InterfaceVerified;
import com.example.recipe_app.Admin.Model.VerificationModel;
import com.example.recipe_app.R;
import com.example.recipe_app.Util.DataApi;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReqVerifiedAdapter extends RecyclerView.Adapter<ReqVerifiedAdapter.ViewHolder> {
    Context context;
    List<VerificationModel> verificationModelList;

    public ReqVerifiedAdapter(Context context, List<VerificationModel> verificationModelList) {
        this.context = context;
        this.verificationModelList = verificationModelList;
    }

    @NonNull
    @Override
    public ReqVerifiedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_req_verified, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReqVerifiedAdapter.ViewHolder holder, int position) {

        holder.tvUsername.setText(verificationModelList.get(position).getUsername());
        holder.tvEmail.setText(verificationModelList.get(position).getEmail());
        holder.tvDate.setText(verificationModelList.get(position).getDate());
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);


        // load image profile
        Glide.with(context)
                .load(verificationModelList.get(position).getPhoto_profile())
                .override(300, 300)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .dontAnimate()
                .centerCrop()
                .fitCenter()
                .into(holder.ivProfile);

        holder.btnDelete.setOnClickListener(view -> {
            InterfaceVerified iv = DataApi.getClient().create(InterfaceVerified.class);
            iv.deleteRequestVerified(verificationModelList.get(position).getId()).enqueue(new Callback<VerificationModel>() {
                @Override
                public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {

                    if (response.body().getStatus().equals("1")) {
                        Toasty.success(context, "Success deleted!", Toasty.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, verificationModelList.size());
                        notifyItemChanged(position);
                        notifyItemChanged(position);
                        verificationModelList.remove(position);
                    } else {
                        Toasty.error(context, "Something went wrong", Toasty.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<VerificationModel> call, Throwable t) {

                    Toasty.error(context, "Please check your connection", Toasty.LENGTH_SHORT).show();

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return verificationModelList.size();
    }

    public void filterlist(ArrayList<VerificationModel> filteredList) {
        verificationModelList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivProfile;
        TextView tvUsername, tvEmail, tvDate;
        SwipeLayout swipeLayout;
        ImageButton btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.iv_user);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvDate = itemView.findViewById(R.id.tv_date);
            swipeLayout = itemView.findViewById(R.id.swipe_verified);
            btnDelete = itemView.findViewById(R.id.btn_delete);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Toast.makeText(context, verificationModelList.get(getAdapterPosition()).getUsername(), Toast.LENGTH_SHORT).show();

        }
    }
}
