package com.example.recipe_app.Adapter;

import static android.content.Context.MODE_PRIVATE;
import static com.example.recipe_app.LoginActivity.my_shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeAllAdapter extends RecyclerView.Adapter<RecipeAllAdapter.ViewHolder> {

    Context context;
    List<RecipeModel> recipeModels;
    String userid;

    public RecipeAllAdapter(Context context, List<RecipeModel> recipeModels) {
        this.context = context;
        this.recipeModels = recipeModels;
        // Mengambil username dan user_id menggunakan sharedpreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id", null);
    }


    @NonNull
    @Override
    public RecipeAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data_recipe_all, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAllAdapter.ViewHolder holder, int position) {
        holder.tv_duration.setText(recipeModels.get(position).getDuration());
        holder.tv_title.setText(recipeModels.get(position).getTitle());
        holder.tv_username.setText(recipeModels.get(position).getUsername());
        holder.tv_like.setText(recipeModels.get(position).getLikes());
        Integer like = Integer.parseInt(recipeModels.get(position).getLikes());

        // set image profile
        Glide.with(context)
                .load(recipeModels.get(position).getPhoto_profile())
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.template_img)
                .override(1024, 768)
                .into(holder.img_profile);


        // Set recipe image
        Glide.with(context)
                .load(recipeModels.get(position).getImage())
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .placeholder(R.drawable.template_img)
                .override(1024, 768)
                .fitCenter()
                .centerCrop()
                .into(holder.img_recipe);

        // check apa user sudah save atau belum
        InterfaceRecipe interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        Call<List<RecipeModel>> call = interfaceRecipe.getSavedRecipe(userid);
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).getRecipe_id().equals(recipeModels.get(position).getRecipe_id())) {

                            holder.btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.btn_favorite));
                        }

                    }
                }
            }
            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar.make(holder.itemView, "Something went wrong", Snackbar.LENGTH_SHORT).show();
            }
        });

        // check apa user sudah like atau belum

        interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        Call<List<RecipeModel>> call2 = interfaceRecipe.getLikeRecipe(userid);
        call2.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {

                        // jika response terdapat recipe id maka akan dicocokan dengan
                        // recipe id yang ada di list recipe model
                        if (response.body().get(i).getRecipe_id().equals(recipeModels.get(position).getRecipe_id())) {

                            // akan mengubah backgorund button telah di save
                            holder.btnLikes.setBackground(context.getResources().getDrawable(R.drawable.ic_loved));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar.make(holder.itemView, "Something went wrong", Snackbar.LENGTH_SHORT).show();
            }
        });

        // saat button like di klik

        holder.btnLikes.setOnClickListener(view -> {
            // jika di unklik maka akan menghapus resep yang sudah di save
            if (holder.btnLikes.getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_loved).getConstantState()) {
                deleteLikeRecipe(recipeModels.get(position).getRecipe_id(), userid);

                // memanggil method untuk mengurangi likes jika button unlike
                countLike(recipeModels.get(position).getRecipe_id(), 2);

                // mengubah background button menjadi belum di unlike
                holder.btnLikes.setBackground(context.getResources().getDrawable(R.drawable.ic_love));

                // mengurangi jumlah likes jika button di unlike
                holder.tv_like.setText(String.valueOf(like - 1));

            }

            //jika di klik maka akan menyimpan resep
            else {
                likedRecipe(recipeModels.get(position).getRecipe_id(), userid);

                // memanggil method untuk menambah likes jika button di like
                countLike(recipeModels.get(position).getRecipe_id(), 1);

                // mengubah background button jika di like
                holder.btnLikes.setBackground(context.getResources().getDrawable(R.drawable.ic_loved));

                // menambah jumlah likes jika button di like
                // mengurangi jumlah likes jika button di unlike
                holder.tv_like.setText(String.valueOf(like));
            }
        });





    }

    @Override
    public int getItemCount() {
        // Limit data to show in recycler view
        return recipeModels.size() > 4 ? 4 : recipeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_like, tv_duration, tv_title, tv_username;
        ImageView img_recipe, img_profile;
        ImageButton btnFavorite, btnLikes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_recipe = itemView.findViewById(R.id.img_recipe);
            img_profile = itemView.findViewById(R.id.img_profile);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_username = itemView.findViewById(R.id.tv_recipe_username);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
            btnLikes = itemView.findViewById(R.id.btn_like);
            tv_like = itemView.findViewById(R.id.tv_likes);


            // saat button save di klik
            btnFavorite.setOnClickListener(view -> {
                // jika di unklik maka akan menghapus resep yang sudah di save
                if (btnFavorite.getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.btn_favorite).getConstantState()) {
                    deleteSavedRecipe(recipeModels.get(getAdapterPosition()).getRecipe_id(), userid);
                    btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.btn_favorite_netral));
                }

                //jika di klik maka akan menyimpan resep
                else {
                    saveRecipe(recipeModels.get(getAdapterPosition()).getRecipe_id(), userid);
                    btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.btn_favorite));
                }
            });




            // saat image recipe di klik akan menuju detail recipe
            img_recipe.setOnClickListener(view -> {
                // get position of item clicked
                Fragment fragment = new DetailRecipeFragment();

                // Send data to detailrecipe fragment

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
                bundle.putString("notes", recipeModels.get(getAdapterPosition()).getNote());
                fragment.setArguments(bundle);

                // get Fragment

                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();

            });


        }
    }


    //method to save recipe

    private void saveRecipe(String recipe_id, String user_id) {
        DataApi.getClient().create(InterfaceRecipe.class).saveSavedRecipe(recipe_id, user_id).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Recipe saved", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Cek ur connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    private void deleteSavedRecipe(String recipeid, String useridd) {
        DataApi.getClient().create(InterfaceRecipe.class).deleteSavedRecipe(recipeid, useridd).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Recipe deleted", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Cek ur connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    // method untuk like recipe

    private void likedRecipe(String recipeid, String useridd) {
        InterfaceRecipe interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        interfaceRecipe.saveLikeRecipe(recipeid, useridd).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Recipe liked", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Cek ur connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    // method untu delete like recipe

    private void deleteLikeRecipe(String recipeid, String userid) {

        InterfaceRecipe interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        interfaceRecipe.deleteLikedRecipe(recipeid, userid).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Recipe unlike", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Cek ur connection", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void countLike(String recipe_id, Integer code) {
        InterfaceRecipe interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        interfaceRecipe.countLikeRecipe(recipe_id, code).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                if (response.isSuccessful()) {

                }
                else {
                    Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {
                Snackbar.make(((FragmentActivity) context).findViewById(android.R.id.content), "Cek ur connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }



}
