package hr.kraljic.hydroponicmonitoringapp.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.kraljic.hydroponicmonitoringapp.R;
import hr.kraljic.hydroponicmonitoringapp.ui.login.presenter.LoginPresenter;
import hr.kraljic.hydroponicmonitoringapp.ui.login.presenter.LoginPresenterImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.navigation.view.NavigationActivity;
import hr.kraljic.hydroponicmonitoringapp.util.TokenManager;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.txtErrorMsg)
    TextView txtErrorMsg;

    @BindView(R.id.etToken_input)
    TextInputLayout etToken;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);

        loginPresenter = new LoginPresenterImpl(this, getApplicationContext());

        String token = TokenManager.getToken(getApplicationContext());
        if (token != null) {
            loginPresenter.login(token);
        }
    }


    @Override
    public void loginError(String errMsg) {
        txtErrorMsg.setText("Incorrect token");
        txtErrorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToHome() {
        txtErrorMsg.setVisibility(View.GONE);
        etToken.getEditText().setText("");

        Intent home = new Intent(getApplicationContext(), NavigationActivity.class);
        startActivity(home);
    }

    @Override
    public void onClick(View v) {
        loginPresenter.login(
                etToken.getEditText().getText().toString()
        );
    }
}
