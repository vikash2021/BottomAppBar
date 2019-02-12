package com.vkk.bottomappbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton floatingActionButton;
    private boolean isFabTapped=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar=findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        floatingActionButton=findViewById(R.id.fab);
        if(savedInstanceState==null){
        handleFrame(new FirstFragment());
        }
        handleFab();
        handleOnClickListeners();
    }

    private void handleOnClickListeners() {
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menuOne:handleFrame(new FirstFragment());
                    break;

                    case R.id.menuTwo:handleFrame(new SecondFragment());
                    break;

                    default:
                        return false;
                }
                return false;
            }
        });
    }

    private void handleFab() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFabTapped=!isFabTapped;
                if(isFabTapped)
                {
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                    handleFrame(new SecondFragment());
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_arrow_back_black_24dp));
                }
                else
                {
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    handleFrame(new FirstFragment());
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.
                    ic_add_black_24dp));

                }
            }
        });
    }

    private void handleFrame(Fragment fragment) {

        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame1,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu
        );
        return super.onCreateOptionsMenu(menu);
    }
}
