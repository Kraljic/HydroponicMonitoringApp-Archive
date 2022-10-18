package hr.kraljic.hydroponicmonitoringapp.ui.navigation.view;

import androidx.fragment.app.Fragment;

public interface MyNavigationView {
    void swapFragment(Fragment fragment, String title);

    void closeActivity();

}
