package com.river.app.di.module;

import android.content.Context;
import com.river.app.module.model.Remote.HealthDataSource;
import com.river.app.module.model.Remote.HealthRemoteDataSource;
import com.river.app.module.model.db.BaseDao;
import com.river.app.module.model.db.DBHelper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/5/9.
 */
@Module public class ApplicationModule {
  private final Context mContext;

  public ApplicationModule(Context context) {
    mContext = context;
  }

  @Provides @Singleton Context provideContext() {
    return mContext;
  }

  @Provides @Singleton HealthDataSource provideHealthDataSource(HealthRemoteDataSource dataSource) {
    return dataSource;
  }
  @Provides @Singleton BaseDao provideBaseDao(DBHelper dbHelper) {
    return dbHelper;
  }
}
