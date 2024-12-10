package com.app.pilotgearhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;

public class Home extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager2;
    private NavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        // Initialize views
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        navbar = findViewById(R.id.navbar);
        SearchView searchView = findViewById(R.id.searchView);

        // Set up TabLayout with ViewPager
        tabLayout.setupWithViewPager(viewPager2);

        // Set up ViewPager adapter
        MyPagerAdapter viewPagerAdapter = new MyPagerAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        viewPagerAdapter.addFragment(new Fragment1(), "Electronics");
        viewPagerAdapter.addFragment(new Fragment2(), "Pilot & Plane Supplies");
        viewPagerAdapter.addFragment(new Fragment3(), "Training and Sim");
        viewPager2.setAdapter(viewPagerAdapter);

        // Navigation buttons
        ImageButton showMenu = findViewById(R.id.showMenu);
        showMenu.setOnClickListener(v -> showMenu());

        ImageButton hideMenu = findViewById(R.id.hideMenu);
        hideMenu.setOnClickListener(v -> hideMenu());

        Button profile = findViewById(R.id.profileBtn);
        profile.setOnClickListener(v -> openProfile());

        Button logout = findViewById(R.id.navbar_logout);
        logout.setOnClickListener(v -> logout());

        // Set up SearchView listeners
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query); // Handle search query submission
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText); // Handle real-time text changes
                return true;
            }
        });
    }

    private void performSearch(String query) {
        if (!query.isEmpty()) {
            // Add your search logic here (e.g., filter a list, query a database, etc.)

        } else {

        }
    }

    public void showMenu() {
        navbar.setVisibility(View.VISIBLE);
        navbar.setTranslationX(-navbar.getWidth()); // Start from off-screen

        navbar.animate()
                .translationX(0f) // Slide into place
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator(1f))
                .start();
    }

    public void hideMenu() {
        navbar.animate()
                .translationX(-navbar.getWidth()) // Slide out to the left
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator(1f))
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        navbar.setVisibility(View.GONE); // Hide after animation
                    }
                })
                .start();
    }

    public void openProfile() {
        startActivity(new Intent(this, Profile.class));
        finish();
    }

    public void logout() {
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
