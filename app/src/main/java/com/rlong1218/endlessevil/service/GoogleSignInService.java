package com.rlong1218.endlessevil.service;
    import android.app.Activity;
    import android.app.Application;
    import android.content.Intent;
    import android.util.Log;
    import androidx.lifecycle.LiveData;
    import androidx.lifecycle.MutableLiveData;
    import com.google.android.gms.auth.api.signin.GoogleSignIn;
    import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
    import com.google.android.gms.auth.api.signin.GoogleSignInClient;
    import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
    import com.google.android.gms.common.api.ApiException;
    import com.google.android.gms.tasks.Task;
    import com.rlong1218.endlessevil.BuildConfig;

public class GoogleSignInService {

  private static Application applicationContext;

  private GoogleSignInClient client;
  private MutableLiveData<GoogleSignInAccount> account = new MutableLiveData<>();
  private MutableLiveData<Exception> exception = new MutableLiveData<>();

  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
//        .requestIdToken(BuildConfig.CLIENT_ID)
        .build();
    client = GoogleSignIn.getClient(applicationContext, options);
  }

  public static void setApplicationContext(Application applicationContext) {
    GoogleSignInService.applicationContext = applicationContext;
  }

  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public LiveData<GoogleSignInAccount> getAccount() {
    return account;
  }

  public LiveData<Exception> getException() {
    return exception;
  }


  public Task<GoogleSignInAccount> refresh() {
    return client.silentSignIn()
        .addOnSuccessListener(this::update)
        .addOnFailureListener(this::update);
  }

  private void update(GoogleSignInAccount account) {
    this.account.setValue(account);
    this.exception.setValue(null);
  }

  private void update(Exception ex) {
    account.setValue(null);
    exception.setValue(ex);
    Log.e(getClass().getSimpleName(), ex.getMessage(), ex);
  }

  public void startSignIn(Activity activity, int requestCode) {
    update((GoogleSignInAccount) null);
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      update(task.getResult(ApiException.class));
    } catch (ApiException e) {
      update(e);
    }
    return task;
  }

//  public Task<Void> signOut() {
//    return client.signOut()
//        .addOnCompleteListener((account) -> update((GoogleSignInAccount) null));
//  }



  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();

  }

}
