package hr.kraljic.hydroponicmonitoringapp.ui.navigation.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import hr.kraljic.hydroponicmonitoringapp.R;
import hr.kraljic.hydroponicmonitoringapp.ui.navigation.presenter.NavigationPresenter;
import hr.kraljic.hydroponicmonitoringapp.ui.navigation.presenter.NavigationPresenterImpl;
import hr.kraljic.hydroponicmonitoringapp.ui.topic_list.view.TopicListFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MyNavigationView {

    public static final String NOTIFICATION_CHANNEL = "hr.kraljic.hydroponicmonitoringapp.notif";
    private static Context context;

    TextView txtUsername;
    TextView txtEmail;

    private NavigationPresenter navigationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Notification");
            NotificationManager man = (NotificationManager)(getSystemService(Context.NOTIFICATION_SERVICE));
            man.createNotificationChannel(channel);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);
        txtUsername = headerLayout.findViewById(R.id.txtUsername);
        txtEmail = headerLayout.findViewById(R.id.txtEmail);

        navigationPresenter = new NavigationPresenterImpl(this, this);
        this.swapFragment(new TopicListFragment(), "Topics");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            navigationPresenter.logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void swapFragment(Fragment fragment, String title) {
        getSupportActionBar().setTitle(title);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void closeActivity() {
        finish();
    }

    public static Context getAppContext() {
        return NavigationActivity.context;
    }
}
