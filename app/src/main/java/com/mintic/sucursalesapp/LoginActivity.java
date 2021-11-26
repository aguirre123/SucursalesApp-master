package com.mintic.sucursalesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.mintic.sucursalesapp.ado.UsuarioADO;
import com.mintic.sucursalesapp.modelos.Usuario;

public class LoginActivity extends AppCompatActivity {
UsuarioADO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button btnIngresar = (Button) findViewById(R.id.btnIngresar);

        EditText txtEmail = (EditText) findViewById(R.id.main_txtEmail);
        EditText txtClave = (EditText) findViewById(R.id.main_txtClave);
        dao= new UsuarioADO(this);

        btnIngresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String clave = txtClave.getText().toString();

                if(email.isEmpty()||clave.isEmpty()) {

                    AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
                    mensaje.setTitle(R.string.main_btnIngresar_errorTitulo);
                    mensaje.setMessage(R.string.main_btnIngresar_errorMensaje);
                    mensaje.create();
                    mensaje.show();
                }else if (dao.login(email,clave)==1){
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
                    mensaje.setTitle("login Exitoso");
                    mensaje.setMessage("el usuario y la contraseña ingresados son correctos");
                    mensaje.create();
                    mensaje.show();
                    Intent i =new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(i);
                }
                else {
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(view.getContext());
                    mensaje.setTitle("ERROR");
                    mensaje.setMessage("el usuario y la contraseña ingresados son incorrectos");
                    mensaje.create();
                    mensaje.show();

                }
            }
        });
        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                    Intent i =new Intent(LoginActivity.this, registro_usuario.class);
                    startActivity(i);

            }
        });
    }
}