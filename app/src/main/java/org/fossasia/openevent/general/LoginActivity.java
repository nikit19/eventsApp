package org.fossasia.openevent.general;

import android.app.ProgressDialog;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import org.fossasia.openevent.general.model.LoginResponse;
import org.fossasia.openevent.general.utils.ConstantStrings;
import org.fossasia.openevent.general.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static org.fossasia.openevent.general.utils.AuthUtil.VALID;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username_et)
    protected EditText usernameET;
    @BindView(R.id.password_et)
    protected EditText passwordET;
    @BindView(R.id.login_btn)
    protected Button loginBtn;
    @BindView(R.id.email_wrapper)
    protected TextInputLayout emailWrapper;
    @BindView(R.id.password_wrapper)
    protected TextInputLayout passwordWrapper;

    ProgressDialog progressDialog;
    public static String TOKEN=null;
    private LoginActivityViewModel loginActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = SharedPreferencesUtil.getString(ConstantStrings.TOKEN,null);
        Timber.d("Token is "+token);
        if(token != null)
            redirectToMain();

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Intent i = getIntent();
        String temp = i.getStringExtra("LOGOUT");
        if(temp != null && temp.equals("TRUE")){
            TOKEN = null;
        }

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging you in...");

        loginActivityViewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);

        loginBtn.setOnClickListener(v -> {
            progressDialog.show();
            String email = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            loginUser(email, password);
        });
    }

    public void redirectToMain(){
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
        this.finish();
    }

    public void loginUser(String title, String body) {

        // Reset errors.
        emailWrapper.setError(null);
        passwordWrapper.setError(null);
        loginActivityViewModel.loginUser(title.trim(),body.trim(),emailWrapper,passwordWrapper,getApplicationContext(),progressDialog).observe(this, (LoginResponse loginResponse) ->{
            //All logic implemented in ViewModel
            if(loginResponse.getResponse() == VALID){
                redirectToMain();
            }
        });
    }
}