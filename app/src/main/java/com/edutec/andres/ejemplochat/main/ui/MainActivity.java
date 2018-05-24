package com.edutec.andres.ejemplochat.main.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.edutec.andres.ejemplochat.App;
import com.edutec.andres.ejemplochat.R;
import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.lib.base.ImageLoader;
import com.edutec.andres.ejemplochat.main.MainPresenter;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements com.edutec.andres.ejemplochat.main.ui.View{

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.mensaje)
    EditText mensaje;
    @BindView(R.id.send)
    Button enviar;
    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.container)
    RelativeLayout container;

    @Inject
    MessagesAdapter adapter;
    @Inject
    MainPresenter presenter;

    private FirebaseUser user;
    private App app;

    private static final int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (App) getApplication();
        inject();
        presenter.onCreate();
        presenter.verifyUser();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        presenter.noMessages();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getMessage();
    }

    @OnClick(R.id.send)
    public void send(){
        Message message = new Message(user.getDisplayName(), user.getPhotoUrl().toString(), mensaje.getText().toString(), new Date().getTime() );
        presenter.sendMessages(message);
    }

    private  void inject() {
        app.main(this).inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void load(boolean loading){
        loader.setVisibility(loading ? android.view.View.VISIBLE : View.GONE);
    }

    @Override
    public  void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void newMessage(Message message) {
        adapter.addMessage(message);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void sentMessage(){
        mensaje.setText(null);
    }

    @Override
    public void getUser(FirebaseUser o){
        this.user = o;
        showMessage("Bienvenido " + user.getDisplayName());
    }

    @Override
    public void verifyUser(boolean o){

        if (!o){
            login();
        }else {
            presenter.getUser();
        }
    }

    private void login() {
        AuthUI.SignInIntentBuilder inIntentBuilder = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(getProviders())
                .setIsSmartLockEnabled(false);
        Intent intent = inIntentBuilder.build();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    public List<AuthUI.IdpConfig> getProviders() {
        List<AuthUI.IdpConfig> providers = new ArrayList<AuthUI.IdpConfig>();
        providers.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        providers.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        return providers;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                presenter.getUser();
            }else{
                showError("hubo un error");
            }
        }
    }
}
