package com.fish.neihan;

import java.util.ArrayList;
import java.util.List;

import com.fish.neihan.fragments.HuoDongFragment;
import com.fish.neihan.fragments.ImageListFragment;
import com.fish.neihan.fragments.MyFragment;
import com.fish.neihan.fragments.ReviewFragment;
import com.fish.neihan.fragments.TextListFragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {
	private RadioGroup radioGroup;

	private List<Fragment> fragments;
	
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		radioGroup = (RadioGroup) findViewById(R.id.main_tab_bar);
		radioGroup.setOnCheckedChangeListener(this);
		
		manager = getSupportFragmentManager();

		fragments = new ArrayList<Fragment>();
		fragments.add(new TextListFragment());
		fragments.add(new ImageListFragment());
		fragments.add(new ReviewFragment());
		fragments.add(new HuoDongFragment());
		fragments.add(new MyFragment());

		Fragment fragment = fragments.get(0);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.main_fragment, fragment);
		transaction.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		int childCount = radioGroup.getChildCount();
		int checkIndex = 0;
		RadioButton btn = null;
		for (int i = 0; i < childCount; i++) {
			btn = (RadioButton) radioGroup.getChildAt(i);
			if (btn.isChecked()) {
				checkIndex = i;
				break;
			}
		}
		Fragment fragment = fragments.get(checkIndex);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.main_fragment, fragment);
		transaction.commit();
		// switch (checkIndex) {
		// case 0:
		//
		// break;
		// case 1:
		//
		// break;
		// case 2:
		//
		// break;
		// case 3:
		//
		// break;
		// case 4:
		//
		// break;
		//
		// default:
		// break;
		// }
	}

}
