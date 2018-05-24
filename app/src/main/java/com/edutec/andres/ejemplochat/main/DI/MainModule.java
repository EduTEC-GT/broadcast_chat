package com.edutec.andres.ejemplochat.main.DI;

import android.content.Context;

import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.firebase.FirebaseHelper;
import com.edutec.andres.ejemplochat.lib.base.EventBus;
import com.edutec.andres.ejemplochat.lib.base.ImageLoader;
import com.edutec.andres.ejemplochat.main.MainInteractor;
import com.edutec.andres.ejemplochat.main.MainInteractorImpl;
import com.edutec.andres.ejemplochat.main.MainPresenter;
import com.edutec.andres.ejemplochat.main.MainPresenterImpl;
import com.edutec.andres.ejemplochat.main.MainRepo;
import com.edutec.andres.ejemplochat.main.MainRepoImpl;
import com.edutec.andres.ejemplochat.main.ui.MessagesAdapter;
import com.edutec.andres.ejemplochat.main.ui.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.MembersInjector;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Andres on 19/05/2018.
 */
@Module
public class MainModule {
    private View view;

    public  MainModule(View view) { this.view = view; }

    @Singleton
    @Provides
    View providesView() {return this.view; }

    @Singleton
    @Provides
    MainPresenter providesMainPresenter(EventBus bus, Context context, View view, MainInteractor interactor){
        return new MainPresenterImpl(bus, context, view, interactor);
    }

    @Singleton
    @Provides
    MainInteractor providesMainInteractor(MainRepo repo, Context context, EventBus bus){
        return new MainInteractorImpl(repo, context, bus);
    }

    @Singleton
    @Provides
    MainRepo provideMainRepo(Context context, EventBus bus, FirebaseHelper helper){
        return new MainRepoImpl(context, bus, helper);
    }

    @Singleton
    @Provides
    List<Message> providesMessageList(){return new ArrayList<Message>(); }

    @Singleton
    @Provides
    MessagesAdapter providesMessagesAdapter(ImageLoader imageLoader, Context context, List<Message> messages){
        return new MessagesAdapter(imageLoader, context, messages);
    }
}
