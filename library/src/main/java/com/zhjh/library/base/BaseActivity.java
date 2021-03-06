package com.zhjh.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zhjh.library.R;
import com.zhjh.library.manager.AppManager;

import butterknife.ButterKnife;


/**
 * Created by zhjh.
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected abstract int provideLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        BaseBus.getInstance().register(this);
        mContext = this;
        initView(savedInstanceState);


    }


    @Override
    protected void onResume() {
        Log.e(this.getClass().getName() , "Resumebus");
        
        super.onResume();
    }

    public void setupToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationIcon(R.drawable.icon_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        setTitle(title);

    }
    
     public void setupToolbar(Toolbar toolbar, Int titleResId) {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_back));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
          if (titleResId != 0){
             setTitle(getText(titleResId));
          }


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(this.getClass().getName() , "Pausebus");

    }


    @Override
    protected void onDestroy() {
        Log.e(this.getClass().getName() , "Destroy");
        AppManager.getAppManager().removeActivity(this);
        BaseBus.getInstance().unregister(this);
        super.onDestroy();
    }

}
