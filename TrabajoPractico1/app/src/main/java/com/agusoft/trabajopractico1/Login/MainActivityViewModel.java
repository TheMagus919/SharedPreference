package com.agusoft.trabajopractico1.Login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.trabajopractico1.Modelo.Usuario;
import com.agusoft.trabajopractico1.Registro.RegistroActivity;
import com.agusoft.trabajopractico1.Registro.RegistroViewModel;
import com.agusoft.trabajopractico1.Request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> error;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
    public LiveData<Boolean> getError(){
        if(error==null){
            error = new MutableLiveData<>();
        }
        return error;
    }
    public void login(String email,String password){
        if(email.equals(null) && password.equals(null)){
            Toast.makeText(context, "Ingresar valores validos.", Toast.LENGTH_SHORT).show();
        }else{
            Usuario us= ApiClient.login(context,email,password);
            if(us==null){
                Toast.makeText(context, "Email o Contraseña incorrectas.", Toast.LENGTH_SHORT).show();
                error.setValue(true);
            }else{
                Intent intent = new Intent(context, RegistroActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("logueado",true);
                context.startActivity(intent);
            }
        }
    }

    public void registrarse(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("logueado",false);
        context.startActivity(intent);
    }
}
