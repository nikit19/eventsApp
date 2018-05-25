package org.fossasia.openevent.general;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.fossasia.openevent.general.model.AttributesUser;
import org.fossasia.openevent.general.model.User;
import org.fossasia.openevent.general.rest.ApiClient;
import org.fossasia.openevent.general.rest.ApiInterface;
import org.fossasia.openevent.general.utils.ConstantStrings;
import org.fossasia.openevent.general.utils.JWTUtils;
import org.fossasia.openevent.general.utils.SharedPreferencesUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private static final String app="application/vnd.api+json";
    private User user;
    private String TOKEN = null;
    private long userId=-1;
    private TextView firstNameTv;
    private TextView emailTv;
    private ImageView avatarImageView;
    private CardView logout;
    private ProgressBar progressBar;
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProfileFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesUtil = new SharedPreferencesUtil(getActivity());
        TOKEN = sharedPreferencesUtil.getString(ConstantStrings.TOKEN,null);
        if(TOKEN == null)
            redirectToLogin();
        TOKEN = "JWT "+TOKEN;
        try {
            userId = JWTUtils.getIdentity(TOKEN);
            Log.d("harsimarSingh", "User id is "+ userId);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void redirectToLogin() {
        Intent i =new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firstNameTv = view.findViewById(R.id.first_name_tv);
        emailTv = view.findViewById(R.id.email_tv);
        logout = view.findViewById(R.id.logout_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesUtil.remove(ConstantStrings.TOKEN);
                redirectToLogin();

            }
        });
        avatarImageView = view.findViewById(R.id.avatar_image_view);
        progressBar= view.findViewById(R.id.progressHeaderUser);


        progressBar.setIndeterminate(true);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.getProfile(app,TOKEN,userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.GONE);
                    Log.d("harsimarSingh","Response Success ");
                    AttributesUser userAttrib = response.body().getUser().getAttributes();
                    firstNameTv.setText(userAttrib.getFirstName());
                    emailTv.setText(userAttrib.getEmail());

                    Picasso.with(view.getContext())
                            .load(userAttrib.getAvatarUrl())
                            .placeholder(R.drawable.ic_person_black_24dp)
                            .transform(new CircleTransform())
                            .into(avatarImageView);
                }else {
                    Log.d("harsimarSingh","Not Successful "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("harsimarSingh","Failure "+t.toString());
            }
        });

        return view;
    }

}