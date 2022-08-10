package com.example.recipe_app.Adapter;

import static android.content.Context.MODE_PRIVATE;
import static com.example.recipe_app.LoginActivity.my_shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recipe_app.Fragment.DetailRecipeFragment;
import com.example.recipe_app.Model.RecipeModel;
import com.example.recipe_app.R;
import com.example.recipe_app.Util.DataApi;
import com.example.recipe_app.Util.InterfaceRecipe;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeTrandingAdapter extends RecyclerView.Adapter<RecipeTrandingAdapter.ViewHolder> {

    Context context;
    List<RecipeModel> recipeModels;
    String userid;

    public RecipeTrandingAdapter(Context context, List<RecipeModel> recipeModels) {
        this.context = context;
        this.recipeModels = recipeModels;

        // Mengambil username dan user_id menggunakan sharedpreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id", null);
    }


    @NonNull
    @Override
    public RecipeTrandingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data_recipe_tranding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeTrandingAdapter.ViewHolder holder, int position) {
        holder.tv_duration.setText(recipeModels.get(position).getDuration());
        holder.tv_title.setText(recipeModels.get(position).getTitle());
        holder.tv_username.setText(recipeModels.get(position).getUsername());
        holder.tv_rating.setText(recipeModels.get(position).getRatings());
        String recipe_idd = recipeModels.get(position).getRecipe_id();

        // set image profile
        Glide.with(context)
                .load(recipeModels.get(position).getPhoto_profile())
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .centerCrop()
                .fitCenter()
                .placeholder(R.drawable.template_img)
                .override(1024, 768)
                .into(holder.img_profile);



        Glide.with(context)
                .load(recipeModels.get(position).getImage())
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.template_img)
                .dontAnimate()
                .override(1024, 768)
                .centerCrop()
                .into(holder.img_recipe);



        // if recipe is favorite than set botton fav
        InterfaceRecipe interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        Call<List<RecipeModel>> call = interfaceRecipe.getSavedRecipe(userid);
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).getRecipe_id().equals(recipeModels.get(position).getRecipe_id())) {

                           holder.btn_save.setBackground(context.getResources().getDrawable(R.drawable.btn_favorite));
                        }

                    }
                }
            }
            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar.make(holder.itemView, "Something went wrong", Snackbar.LENGTH_SHORT).show();
            }
        });















    }

    @Override
    public int getItemCount() {
        return recipeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rating, tv_duration, tv_title, tv_username;
        ImageView img_recipe, img_profile;
        ImageButton btn_save;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_recipe = itemView.findViewById(R.id.img_recipe);
            img_profile = itemView.findViewById(R.id.img_profile);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_username = itemView.findViewById(R.id.tv_recipe_username);
            btn_save = itemView.findViewById(R.id.btn_favorite);



            // move to detail fragment when image recipe is click
            img_recipe.setOnClickListener(View -> {

                Fragment fragment = new DetailRecipeFragment();

                Bundle bundle = new Bundle();
                bundle.putString("recipe_id", recipeModels.get(getAdapterPosition()).getRecipe_id());
                bundle.putString("user_id", recipeModels.get(getAdapterPosition()).getUser_id());
                bundle.putString("username", recipeModels.get(getAdapterPosition()).getUsername());
                bundle.putString("title", recipeModels.get(getAdapterPosition()).getTitle());
                bundle.putString("description", recipeModels.get(getAdapterPosition()).getDescription());
                bundle.putString("category", recipeModels.get(getAdapterPosition()).getCategory());
                bundle.putString("servings", recipeModels.get(getAdapterPosition()).getServings());
                bundle.putString("duration", recipeModels.get(getAdapterPosition()).getDuration());
                bundle.putString("ingredients", recipeModels.get(getAdapterPosition()).getIngredients());
                bundle.putString("steps", recipeModels.get(getAdapterPosition()).getSteps());
                bundle.putString("upload_date", recipeModels.get(getAdapterPosition()).getUpload_date());
                bundle.putString("upload_time", recipeModels.get(getAdapterPosition()).getUpload_time());
                bundle.putString("image", recipeModels.get(getAdapterPosition()).getImage());
                bundle.putString("status", recipeModels.get(getAdapterPosition()).getStatus());
                bundle.putString("ratings", recipeModels.get(getAdapterPosition()).getRatings());
                bundle.putString("likes", recipeModels.get(getAdapterPosition()).getLikes());
                bundle.putString("photo_profile", recipeModels.get(getAdapterPosition()).getPhoto_profile());
                bundle.putString("email", recipeModels.get(getAdapterPosition()).getEmail());
                fragment.setArguments(bundle);
                // get Fragment

                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();


            });


        }
    }

    private void saveRecipe(String recipe_id, String user_id) {
        DataApi.getClient().create(InterfaceRecipe.class).saveSavedRecipe(recipe_id, user_id).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Recipe saved", Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Cek ur connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }




}

