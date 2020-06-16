//package com.example.groupproject_groceryapp;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.android.material.tabs.TabLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SecondActivity extends AppCompatActivity {
//
////    public TextView getName;
////    public TextView getAge;
////    public TextView getOccupation;
////    public TextView getDescription;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d("Second", "In Second Activity");
//        setContentView(R.layout.second_activity);
//
//
//        //Intent intent = getIntent();
//
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ViewPager viewPager = findViewById(R.id.viewpager);
//        setupViewPager(viewPager,db);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.addTab(tabs.newTab().setText("ShoppingLists"));
//        tabs.addTab(tabs.newTab().setText("Allergies"));
//        tabs.addTab(tabs.newTab().setText("Recipes"));
//        tabs.setupWithViewPager(viewPager);
//
//    }
//
//    private void setupViewPager(ViewPager viewPager, Bundle bundle)
//    {
//        SecondActivity.Adapter adapter = new SecondActivity.Adapter(getSupportFragmentManager());
//        adapter.addFragment(new ListsFragment(bundle),"Shopping Lists");
//        adapter.addFragment(new AllergiesFragment(),"Allergies");
//        adapter.addFragment(new RecipesFragment(),"Recipes");
//        viewPager.setAdapter(adapter);
//    }
//
//
//    static class Adapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//
//        public Adapter(FragmentManager manager)
//        {
//            super(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//
//        }
//        @Override
//        public Fragment getItem(int position){
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount(){
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String tabName){
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(tabName);
//        }
//        @Override
//        public CharSequence getPageTitle(int position){
//            return mFragmentTitleList.get(position);
//        }
//    }
//
//
//}
