package com.agusoft.trabajopractico1.Registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.trabajopractico1.Login.MainActivity;
import com.agusoft.trabajopractico1.Login.MainActivityViewModel;
import com.agusoft.trabajopractico1.Modelo.Usuario;
import com.agusoft.trabajopractico1.R;
import com.agusoft.trabajopractico1.Request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> userM;
    public RegistroViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
    public LiveData<Usuario> getUserM(){
        if(userM==null){
            userM = new MutableLiveData<>();
        }
        return userM;
    }
    public void logeado(Bundle bundle){
        Boolean log = bundle.getBoolean("logueado");
        if(log){
            Usuario us = ApiClient.leer(context);
            userM.setValue(us);
        }
    }
    public void guardar(Usuario us){
        ApiClient.guardar(context,us);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
