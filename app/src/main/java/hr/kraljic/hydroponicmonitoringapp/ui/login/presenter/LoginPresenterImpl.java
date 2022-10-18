package hr.kraljic.hydroponicmonitoringapp.ui.login.presenter;

import android.content.Context;

import java.util.List;
import java.util.stream.Collectors;

import hr.kraljic.hydroponicmonitoringapp.firebase.SubscriptionManagerImpl;
import hr.kraljic.hydroponicmonitoringapp.models.TopicKeyDto;
import hr.kraljic.hydroponicmonitoringapp.topic.TopicKeyService;
import hr.kraljic.hydroponicmonitoringapp.topic.TopicKeyServiceImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.login.model.LoginInteractor;
import hr.kraljic.hydroponicmonitoringapp.ui.login.model.LoginInteractorImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.login.view.LoginView;
import hr.kraljic.hydroponicmonitoringapp.util.TokenManager;
import hr.kraljic.hydroponicmonitoringapp.util.TopicKeyManager;

public class LoginPresenterImpl implements LoginPresenter {

    private final LoginInteractor loginInteracotr;
    private final LoginView view;
    private final Context context;

    public LoginPresenterImpl(LoginView view, Context context) {
        this.view = view;
        this.context = context;
        this.loginInteracotr = new LoginInteractorImpl();
    }

    @Override
    public void login(final String token) {
        loginInteracotr.login(token, context, new TopicKeyService.TopicsLoadCallback() {
            @Override
            public void onError(String errMsg) {
                view.loginError(errMsg);
            }

            @Override
            public void onSuccess(List<TopicKeyDto> topicKeyDtoList) {
                TokenManager.setToken(context, token);

                view.navigateToHome();
            }
        });
    }

}
