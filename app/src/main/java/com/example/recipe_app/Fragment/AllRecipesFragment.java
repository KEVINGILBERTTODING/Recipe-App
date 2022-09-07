package com.example.recipe_app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_app.Adapter.AllUserAdapter;
import com.example.recipe_app.Adapter.RecipeShowAllAdapter;
import com.example.recipe_app.MainActivity;
import com.example.recipe_app.Model.ProfileModel;
import com.example.recipe_app.Model.RecipeModel;
import com.example.recipe_app.R;
import com.example.recipe_app.Util.DataApi;
import com.example.recipe_app.Util.InterfaceProfile;
import com.example.recipe_app.Util.InterfaceRecipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRecipesFragment extends Fragment {

    ShimmerRecyclerView shimmerRecyclerView, rv_user;
    SearchView searchView;
    TabLayout tabLayout;

    private List<RecipeModel> recipeModelList;
    private InterfaceRecipe interfaceRecipe;
    RecipeShowAllAdapter recipeShowAllAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    List<ProfileModel> profileModelList;
    AllUserAdapter allUserAdapter;
    Handler handler;
    TextView tv_banner;

    FloatingActionButton btn_scan;
    Context context;



    public AllRecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_all_recipes, container, false);

        shimmerRecyclerView = view.findViewById(R.id.recycler_recipe_all);
        searchView = view.findViewById(R.id.search_all_recipes);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        btn_scan = view.findViewById(R.id.btn_scan);
        tabLayout = view.findViewById(R.id.tab_layout);
        rv_user = view.findViewById(R.id.rv_user);
        tv_banner = view.findViewById(R.id.tv_banner);

        handler = new Handler();
        context = getContext();


        // Add tab layout
        tabLayout.addTab(tabLayout.newTab().setText("Recipe"));
        tabLayout.addTab(tabLayout.newTab().setText("User"));

        // if tab layout set tab selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {

                    setShimmer();

                    // get all recipe
                    getAllRecipe();

                    // setQuery Hint searchview
                    searchView.setQueryHint("Search recipe...");

                    //set banner text
                    tv_banner.setText("Find all recipe");


                    // hide recylerview user where tab layout recipe is selected
                    shimmerRecyclerView.setVisibility(View.VISIBLE);
                    rv_user.setVisibility(View.GONE);

                    // set query searchview
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String querry) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            filter(newText);
                            return true;
                        }
                    });

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            getAllRecipe();

                        }
                    });

                } else {

                    // get data all user
                    getUser();
                    
                    // hide recycler view recipe when tab user is selected
                    shimmerRecyclerView.setVisibility(View.GONE);
                    rv_user.setVisibility(View.VISIBLE);

                    // setQuery Hint searchview
                    searchView.setQueryHint("Search user...");

                    //set banner text
                    tv_banner.setText("Find all user");

                    // set shimmer user
                    rv_user.setItemViewType((type, position) -> {
                        switch (type) {


                            default:
                            case ShimmerRecyclerView.LAYOUT_LIST:
                                return position == 0 || position % 2 == 0
                                        ? R.layout.template_list_user_search
                                        : R.layout.template_list_user_search;
                        }
                    });

                    rv_user.showShimmer();     // to start showing shimmer
                    // To stimulate long running work using android.os.Handler

                    // set query serach bar
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String querry) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            searchUser(newText);
                            return true;
                        }
                    });

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            // get data all user

                            // set shimmer user
                            rv_user.setItemViewType((type, position) -> {
                                switch (type) {


                                    default:
                                    case ShimmerRecyclerView.LAYOUT_LIST:
                                        return position == 0 || position % 2 == 0
                                                ? R.layout.template_list_user_search
                                                : R.layout.template_list_user_search;
                                }
                            });

                            rv_user.showShimmer();     // to start showing shimmer
                            // To stimulate long running work using android.os.Handler

                            getUser();

                        }
                    });

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

        // catch data from searchbar

        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("searchText");
            searchView.setQuery(title, false);
        }

        btn_scan.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new ScannerFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        searchView.requestFocus();

        setShimmer();
        getAllRecipe();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String querry) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        // SWIPE REFRESH
        swipeRefreshLayout.setColorSchemeResources(R.color.main);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRecipe();
                setShimmer();
            }
        });





        return view;
    }

    private void refreshItem() {
        getAllRecipe();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setShimmer() {
        shimmerRecyclerView.setAdapter(recipeShowAllAdapter);
        shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shimmerRecyclerView.setItemViewType((type, position) -> {
            switch (type) {
                case ShimmerRecyclerView.LAYOUT_GRID:
                    return position % 2 == 0
                            ? R.layout.template_data_show_all
                            : R.layout.template_data_show_all;

                default:
                case ShimmerRecyclerView.LAYOUT_LIST:
                    return position == 0 || position % 2 == 0
                            ? R.layout.template_data_show_all
                            : R.layout.template_data_show_all;
            }
        });
        shimmerRecyclerView.showShimmer();     // to start showing shimmer
    }

    private void getAllRecipe() {
        interfaceRecipe = DataApi.getClient().create(InterfaceRecipe.class);
        Call<List<RecipeModel>> call = interfaceRecipe.getAllRecipe(1);
        call.enqueue(new Callback<List<RecipeModel>>() {


            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                recipeModelList = response.body();
                recipeShowAllAdapter = new RecipeShowAllAdapter(getContext(), recipeModelList);
                // Make it Horizontal recycler view
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                shimmerRecyclerView.setLayoutManager(gridLayoutManager);
                shimmerRecyclerView.setAdapter(recipeShowAllAdapter);
                shimmerRecyclerView.setHasFixedSize(true);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar snackbar = Snackbar.make(getView(), "No conection", Snackbar.LENGTH_SHORT);
                snackbar.show();
                snackbar.setBackgroundTint(getResources().getColor(R.color.main));
                swipeRefreshLayout.setRefreshing(false);

            }


        });


    }



    private void getUser() {
        InterfaceProfile interfaceProfile = DataApi.getClient().create(InterfaceProfile.class);
        interfaceProfile.getAllUser(1).enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                profileModelList = response.body();
                if (profileModelList.size() > 0 ) {

                    allUserAdapter = new AllUserAdapter(getContext(), profileModelList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rv_user.setLayoutManager(linearLayoutManager);
                    rv_user.setAdapter(allUserAdapter);
                    rv_user.setHasFixedSize(true);
                    swipeRefreshLayout.setRefreshing(false);

                } else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);

                }


            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Error no connection", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }


    // METHOD SEARCH RECIPE
    private void filter(String newText) {

        ArrayList<RecipeModel> filteredList = new ArrayList<>();

        for (RecipeModel item : recipeModelList) {
            if (item.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);

            }
        }


        recipeShowAllAdapter.filterList(filteredList);


        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "Not found", Toast.LENGTH_SHORT).show();
        } else {
            recipeShowAllAdapter.filterList(filteredList);
        }


    }


    // METHOD SEARCH USER
    private void searchUser(String newText) {
        ArrayList<ProfileModel> searchUser = new ArrayList<>();
        for (ProfileModel item : profileModelList) {
            if (item.getUsername().toLowerCase().contains(newText.toLowerCase())) {
                searchUser.add(item);
            }
        }

        allUserAdapter.filter(searchUser);

        if (searchUser.isEmpty()) {
            Toast.makeText(getContext(), "Not found", Toast.LENGTH_SHORT).show();
        } else {
            allUserAdapter.filter(searchUser);
        }
    }


}