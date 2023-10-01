package com.example.product.Dashboard;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.product.R;

public class MenuForAll extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.first_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.download_toolbar:
                Toast.makeText(this, "first menu is selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.car_toolbar:
                Toast.makeText(this, "Second menu is selected", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
