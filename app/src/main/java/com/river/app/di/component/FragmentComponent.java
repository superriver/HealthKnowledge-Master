package com.river.app.di.component;

import android.app.Activity;
import com.river.app.annotation.FragmentScope;
import com.river.app.di.module.FragmentModule;
import com.river.app.module.ui.fragment.HealthDetailFragment;
import com.river.app.module.ui.fragment.HealthPagerFragment;
import com.river.app.module.ui.fragment.MainFragment;
import dagger.Component;

/**
 * Created by Administrator on 2017/6/16.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
  Activity activity();
  void inject(HealthDetailFragment healthDetailFragment);
  void inject(MainFragment mainFragment);
  void inject(HealthPagerFragment healthPagerFragment);
}
