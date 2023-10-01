package com.example.product.Dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.product.HelperMethod.DBHelper_projection;
import com.example.product.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends MenuForAll {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    Toolbar toolBar;
    BottomNavigationView bottomNavigationView;
    private DBHelper_projection.UserRepository userRepository;
    Cursor cursor;
    private Button btn_logout;
    private boolean isBackPressedOnce = false;


    private void loadHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.addToBackStack("HomeFragment");
        fragmentTransaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        btn_logout = findViewById(R.id.btn_logout);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        loadHomeFragment();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_product:
                        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                        startActivity(intent);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_camera:
                        Intent intent2 = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(intent2);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_logout:
                        Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent3);
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_map:
                        Intent intentMap = new Intent(MainActivity.this, MapActivity.class);
                        startActivity(intentMap);
                        drawer.closeDrawers();
                        break;

                    case R.id.action_Tab:
                        Intent intentTab = new Intent(MainActivity.this, TabActivity.class);
                        startActivity(intentTab);
                        drawer.close();
                        break;
                }
                return false;
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (itemId == R.id.action_home) {
                    loadHomeFragment();
                    return true;
                } else if (itemId == R.id.action_search) {

                    fragmentTransaction.replace(R.id.fragment_container, new ProductFragment(), "ProductFragment");
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();

                } else if (itemId == R.id.action_notification) {

                    fragmentTransaction.replace(R.id.fragment_container, new NoteFragment(), "NotificationFragment");
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();

                } else if (itemId == R.id.action_profile) {

                    fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment(), "ProfileFragment");
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
//                } else if(itemId == R.id.action_map){
////                    FragmentManager fragmentManager = getSupportFragmentManager();
////                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new MapActivity()).commit();
////                    return true;
//                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
//                    startActivity(intent);
//                }

//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                return true;
            }
        });

        userRepository = new DBHelper_projection.UserRepository(this);
//        userRepository.addUser("User_02", "1234567890", "sample02@gmail.com", "password");

        cursor = userRepository.getUser();
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_NAME);
            int mobileNumberIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_MOBILE_NUMBER);
            int emailIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_EMAIL);
            int passwordIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_PASSWORD);

            String name = cursor.getString(nameIndex);
            String mobileNumber = cursor.getString(mobileNumberIndex);
            String email = cursor.getString(emailIndex);
            String password = cursor.getString(passwordIndex);

            Log.d("database", "Name: " + name + "\n"
                    + "Mobile Number: " + mobileNumber + "\n"
                    + "Email: " + email + "\n"
                    + "Password: " + password);

            cursor.close();
        }


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                 the FLAG_ACTIVITY_CLEAR_TOP flag to the intent, you ensure that all activities on
//                 top of the LoginActivity (if any) are cleared from the back stack.
//                 The FLAG_ACTIVITY_NEW_TASK flag is used to start the LoginActivity as a new task.
//                 Finally, the finish() method is called to finish the MainActivity, removing it from the back stack.
                startActivity(intent);
                finish();
            }
        });

    }


    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustomStyle);
        builder.setTitle("Exit");
        builder.setIcon(R.drawable.cancel);
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*or for above code (showExitDialog())*/
      /*  private void showExitDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setIcon(R.drawable.cancel)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }*/


    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            Log.d("backStackCount", "backStackEntryCount = " + backStackEntryCount);
            Fragment currentFragment1 = fragmentManager.findFragmentById(R.id.fragment_container);

            if (backStackEntryCount > 0 && !(currentFragment1 instanceof HomeFragment)) {
                // If there are fragments in the back stack and the current fragment is not HomeFragment,
                // pop the stack to go back to the previous fragment (HomeFragment)
                fragmentManager.popBackStackImmediate("HomeFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                isBackPressedOnce = false;
                Log.d("afterBackStackCount", "backStackEntryCount after popBackStack = " + fragmentManager.getBackStackEntryCount());
            }
            if (!isBackPressedOnce) {
                // Check if the current fragment is the HomeFragment
                Fragment currentFragment2 = fragmentManager.findFragmentById(R.id.fragment_container);
                if (currentFragment2 instanceof HomeFragment) {
                    showExitDialog();
                    // initially when isBackPressedOnce is false, context directly first come to here
                    isBackPressedOnce = true;
//                        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isBackPressedOnce = false;
                        }
                    }, 2000);    // 2 sec delay

                } else {
                    // If not the HomeFragment, navigate to the HomeFragment
                    loadHomeFragment();
                }
            } else {
                // If back button is pressed again within 2 seconds, finish the activity
                finish();
                System.exit(0);


            }
        }
    }


}






