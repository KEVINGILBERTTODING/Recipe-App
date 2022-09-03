package com.example.recipe_app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.recipe_app.LoginActivity.TAG_USERNAME;
import static com.example.recipe_app.LoginActivity.my_shared_preferences;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recipe_app.Adapter.MyRecipeAdapter;
import com.example.recipe_app.Adapter.RecipeTrandingAdapter;
import com.example.recipe_app.Model.ProfileModel;
import com.example.recipe_app.Model.ProfileModel;
import com.example.recipe_app.Model.RecipeModel;
import com.example.recipe_app.R;
import com.example.recipe_app.Util.DataApi;
import com.example.recipe_app.Util.InterfaceProfile;
import com.example.recipe_app.Util.InterfaceRecipe;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileFragment extends Fragment implements MyRecipeAdapter.OnRecipeListener {
    String username, userid, user_idx;
    ImageView iv_profile;
    TextView tv_username, tv_email, tv_biography, tv_date, tv_time, tv_no_data;
    ImageButton btnSetting;

    List<ProfileModel> profileModelList;
    ProfileModel profileModel;
    InterfaceProfile interfaceProfile;
    RecyclerView rv_recipe;
    MyRecipeAdapter myRecipeAdapter;
    List<RecipeModel> recipeModelList;

    TabLayout tabLayout;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        // Mengambil username dan user_id menggunakan sharedpreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        username = sharedPreferences.getString(TAG_USERNAME, null);
        userid = sharedPreferences.getString("user_id", null);

        iv_profile = view.findViewById(R.id.iv_profile);
        tv_username = view.findViewById(R.id.tv_username);
        tv_email = view.findViewById(R.id.tv_email);
        tv_biography = view.findViewById(R.id.tv_biography);
        tv_date = view.findViewById(R.id.tv_date);
        tv_time = view.findViewById(R.id.tv_time);
        tabLayout = view.findViewById(R.id.tab_layout);
        rv_recipe = view.findViewById(R.id.recycler_recipe);
        btnSetting = view.findViewById(R.id.btn_setting);
        tv_no_data = view.findViewById(R.id.tv_no_data);

        btnSetting.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new SettingFragment());
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);
        });


        // Mengambil data profile dari API
        getProfile(userid);

        // mengambil data recipe dari API
        getRecipe(userid, 1);


        // create tablayout
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_layout));

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_eye));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_love2));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    getRecipe(userid, 1);
                } else if (tab.getPosition() == 1 ) {
                    getRecipe(userid, 2);
                } else if ((tab.getPosition() == 2 )){
                    getLikeRecipe(userid);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getRecipe(userid, 1);


        return view;
    }


    //get profile
    private void getProfile(String user_id) {
        DataApi.getClient().create(InterfaceProfile.class).getProfile(userid).enqueue(new retrofit2.Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, retrofit2.Response<List<ProfileModel>> response) {
                profileModelList = response.body();
                for (int i = 0; i < profileModelList.size(); i++) {
                    profileModel = profileModelList.get(i);
                    tv_username.setText(profileModel.getUsername());
                    tv_email.setText(profileModel.getEmail());
                    tv_biography.setText(profileModel.getBiography());
                    tv_date.setText(profileModel.getDate());
                    tv_time.setText(profileModel.getTime());
                    Glide.with(getContext())
                            .load(profileModel.getPhoto_profile())
                            .thumbnail(0.5f)
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .dontAnimate()
                            .fitCenter()
                            .centerCrop()
                            .placeholder(R.drawable.template_img)
                            .override(1024, 768)
                            .into(iv_profile);
                }
            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {
                Snackbar.make(getView(), "Check your connection", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void getRecipe(String user_id, Integer status) {

        DataApi.getClient().create(InterfaceRecipe.class).getMyRecipe(user_id, status).enqueue(new retrofit2.Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, retrofit2.Response<List<RecipeModel>> response) {
                recipeModelList = response.body();
                if (recipeModelList.size() > 0) {
                    recipeModelList = response.body();
                    myRecipeAdapter = new MyRecipeAdapter(getContext(), recipeModelList);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    rv_recipe.setLayoutManager(gridLayoutManager);
                    rv_recipe.setAdapter(myRecipeAdapter);
                    rv_recipe.setVisibility(View.VISIBLE);
                    rv_recipe.setHasFixedSize(true);
                    tv_no_data.setVisibility(View.GONE);
                    myRecipeAdapter.setOnRecipeListener(MyProfileFragment.this);


                } else {
                    rv_recipe.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                }
              

            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar.make(getView(), "Check your connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    private void getLikeRecipe(String user_id) {

        DataApi.getClient().create(InterfaceRecipe.class).getMyLikeRecipe(user_id).enqueue(new retrofit2.Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, retrofit2.Response<List<RecipeModel>> response) {
                recipeModelList = response.body();
                myRecipeAdapter = new MyRecipeAdapter(getContext(), recipeModelList);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                rv_recipe.setLayoutManager(gridLayoutManager);
                rv_recipe.setAdapter(myRecipeAdapter);
                rv_recipe.setHasFixedSize(true);
                myRecipeAdapter.setOnRecipeListener(MyProfileFragment.this);


            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar.make(getView(), "Check your connection", Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onRecipeClick(View view, int position) {

        RecipeModel recipeModel = recipeModelList.get(position);
        switch (view.getId()) {
            case R.id.iv_recipe:
                Fragment fragment = new DetailRecipeFragment();

                Bundle bundle = new Bundle();
                bundle.putString("recipe_id", recipeModel.getRecipe_id());
                bundle.putString("user_id", recipeModel.getUser_id());
                bundle.putString("username", recipeModel.getUsername());
                bundle.putString("title", recipeModel.getTitle());
                bundle.putString("description", recipeModel.getDescription());
                bundle.putString("category", recipeModel.getCategory());
                bundle.putString("servings", recipeModel.getServings());
                bundle.putString("duration", recipeModel.getDuration());
                bundle.putString("ingredients", recipeModel.getIngredients());
                bundle.putString("steps", recipeModel.getSteps());
                bundle.putString("upload_date", recipeModel.getUpload_date());
                bundle.putString("upload_time", recipeModel.getUpload_time());
                bundle.putString("image", recipeModel.getImage());
                bundle.putString("status", recipeModel.getStatus());
                bundle.putString("ratings", recipeModel.getRatings());
                bundle.putString("likes", recipeModel.getLikes());
                bundle.putString("photo_profile", recipeModel.getPhoto_profile());
                bundle.putString("email", recipeModel.getEmail());
                bundle.putString("notes", recipeModel.getNote());
                fragment.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }

    }
}
