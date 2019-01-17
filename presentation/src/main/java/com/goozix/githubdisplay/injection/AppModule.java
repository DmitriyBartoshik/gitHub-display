package com.goozix.githubdisplay.injection;

import android.content.Context;

import com.goozix.data.repository.UserRepositoryImpl;
import com.goozix.githubdisplay.executor.UIThread;
import com.goozix.data.net.RestService;
import com.goozix.domain.executors.PostExecutionThread;
import com.goozix.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    public static UserRepository provideUserRepository(UserRepositoryImpl userRepository) {
        return new UserRepositoryImpl(new RestService()) {
        };
    }

    @Singleton
    @Provides
    public static PostExecutionThread provideUIThread(UIThread uiThread) {
        return uiThread;
    }
}
