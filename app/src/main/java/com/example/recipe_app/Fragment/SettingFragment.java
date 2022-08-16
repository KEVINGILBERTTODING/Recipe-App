package com.example.recipe_app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.recipe_app.LoginActivity.TAG_USERNAME;
import static com.example.recipe_app.LoginActivity.my_shared_preferences;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recipe_app.Model.ProfileModel;
import com.example.recipe_app.R;
import com.example.recipe_app.Util.DataApi;
import com.example.recipe_app.Util.InterfaceProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingFragment extends Fragment {

    RelativeLayout updte_pass, updt_email, contactUs;
    ImageButton btnBack;
    ImageView iv_profile;
    private List<ProfileModel> profileModelList = new ArrayList<>();
    String username, userid;
    TextView tv_username, tv_email;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        username = sharedPreferences.getString(TAG_USERNAME, null);
        userid = sharedPreferences.getString("user_id", null);

        updte_pass = view.findViewById(R.id.update_pass);
        updt_email = view.findViewById(R.id.email);
        btnBack = view.findViewById(R.id.btnBack);
        iv_profile = view.findViewById(R.id.iv_profile);
        tv_username= view.findViewById(R.id.tv_username);
        tv_email = view.findViewById(R.id.tv_email);
        contactUs = view.findViewById(R.id.rl_contact_us);
        getProfile(userid);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });



        updte_pass.setOnClickListener(view1 ->  {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new UpdatePassword());
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);

        });

        updt_email.setOnClickListener(view1 ->  {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new UpdateEmailFragment());
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);


        });

        contactUs.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.contact_us);
        });

        return view;
    }

    // method untuk load photo profile
   private void getProfile(String user_id) {
       InterfaceProfile interfaceProfile = DataApi.getClient().create(InterfaceProfile.class);
       interfaceProfile.getProfile(user_id).enqueue(new Callback<List<ProfileModel>>() {
           @Override
           public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                profileModelList = response.body();
                if (profileModelList.size() > 0) {

                    tv_username.setText(profileModelList.get(0).getUsername());
                    tv_email.setText(profileModelList.get(0).getEmail());
                     Glide.with(getActivity())
                            .load(profileModelList.get(0).getPhoto_profile())
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

           }
       });
    }
}