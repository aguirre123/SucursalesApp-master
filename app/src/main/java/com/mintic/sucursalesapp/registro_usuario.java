package com.mintic.sucursalesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mintic.sucursalesapp.ado.UsuarioADO;
import com.mintic.sucursalesapp.clases.Mensajes;
import com.mintic.sucursalesapp.modelos.Usuario;

public class registro_usuario extends AppCompatActivity {
    private Usuario registro=null;
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtEmail;
    private EditText txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);


        Button btnVolver = (Button) findViewById(R.id.btnvolverusuario);
        Button btnGuardar = (Button) findViewById(R.id.btnguradarusuario);
        txtNombres = (EditText) findViewById(R.id.EditTextName);
        txtApellidos = (EditText) findViewById(R.id.EditTextApellido);
        txtEmail = (EditText) findViewById(R.id.EditTextEmail);
        txtClave = (EditText) findViewById(R.id.EditTextclave);

      //  Bundle parametros = getIntent().getExtras();
      //  if(parametros.containsKey("id"))
        //{
          //  UsuarioADO db = new UsuarioADO(this);
            //Usuario us = db.obtenerUsuario(parametros.getLong("id"));
            //this.registro = us;
            //cargarDatos();
        //}

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = txtNombres.getText().toString();
                String apellidos = txtApellidos.getText().toString();
                String email = txtEmail.getText().toString();
                String clave = txtClave.getText().toString();

                if(validarCampos(nombres, apellidos, email, clave))
                {
                    if(registro!=null)
                    {
                        registro.setNombres(nombres);
                        registro.setApellidos(apellidos);
                        registro.setEmail(email);
                        registro.setClave(clave);

                        UsuarioADO db = new UsuarioADO(view.getContext());
                        if(db.editar(registro))
                            new Mensajes(view.getContext()).alert("Registro actualizado", "Se ha actualizado el registro correctamente.");
                        else
                            new Mensajes(view.getContext()).alert("Error", "Se ha producido un error al intentar actualizar el registro.");
                    }
                    else {
                        UsuarioADO registro = new UsuarioADO(view.getContext());
                        Usuario us = new Usuario();
                        us.setNombres(nombres);
                        us.setApellidos(apellidos);
                        us.setEmail(email);
                        us.setClave(clave);
                        long idInsercion = registro.insertar(us);
                        if (idInsercion > 0)
                            new Mensajes(view.getContext()).alert("Registro insertado", "Se ha insertado el registro correctamente con el codigo " + String.valueOf(idInsercion));
                        else
                            new Mensajes(view.getContext()).alert("Error", "Se ha producido un error al intentar insertar el registro.");
                    }
                    onBackPressed();
                }
                else
                {
                    new Mensajes(view.getContext()).alert("Advertencia", "Digite los campos en blanco.");
                }
            }
        });
    }

    public static boolean validarCampos(String nombres, String apellidos, String email, String clave)
    {
        boolean camposAceptados = false;

        if((!nombres.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !clave.isEmpty()))
            camposAceptados=true;

        return camposAceptados;
    }

    public void cargarDatos()
    {
        this.txtNombres.setText(this.registro.getNombres());
        this.txtApellidos.setText(this.registro.getApellidos());
        this.txtEmail.setText(this.registro.getEmail());
        this.txtClave.setText(this.registro.getClave());
    }
}
