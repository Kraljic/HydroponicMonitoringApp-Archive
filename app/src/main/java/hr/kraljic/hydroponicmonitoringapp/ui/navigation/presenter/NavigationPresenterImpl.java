package hr.kraljic.hydroponicmonitoringapp.ui.navigation.presenter;

import android.content.Context;

import hr.kraljic.hydroponicmonitoringapp.firebase.SubscriptionManagerImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.navigation.view.MyNavigationView;
import hr.kraljic.hydroponicmonitoringapp.util.TokenManager;
import hr.kraljic.hydroponicmonitoringapp.util.TopicKeyManager;

public class NavigationPresenterImpl implements NavigationPresenter {

    private Context mContext;
    private MyNavigationView view;

    public NavigationPresenterImpl(Context context, MyNavigationView view) {
        this.mContext = context;
        this.view = view;
    }

    @Override
    public void logout() {
        SubscriptionManagerImpl.unsubscribeFromAllNotifications(
                TopicKeyManager.getTopicKeys(mContext),
                mContext
        );
        TokenManager.removeToken(mContext);
        TopicKeyManager.clear(mContext);
        view.closeActivity();
    }
}
