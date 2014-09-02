package com.hand.hrmexp.activity;

import com.hand.R;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MenuActivity extends FragmentActivity{
	
	private ResideMenu resideMenu;
	
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_menu);
		setUpMenu();
		  changeFragment(new HomeFragment()); 
	}
	
	
    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.homebg);
        resideMenu.attachToActivity(this);

        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.home,     "Home");
        itemProfile  = new ResideMenuItem(this, R.drawable.timesheetfill,  "Profile");



        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.main_head_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

    }
    
    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
