package com.mintic.sucursalesapp.ado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mintic.sucursalesapp.clases.SqliteConex;
import com.mintic.sucursalesapp.modelos.Usuario;

import java.util.ArrayList;

public class UsuarioADO extends SqliteConex {

        private Context contexto;

public UsuarioADO(@Nullable Context c)
        {
        super(c);
        this.contexto = c;
        }

public long insertar(Usuario us)
        {
        long id = 0;

        SqliteConex dbc = new SqliteConex(this.contexto);
        SQLiteDatabase db = dbc.getWritableDatabase();
        try
        {

        ContentValues valores = new ContentValues();
        valores.put("nombres", us.getNombres());
        valores.put("apellidos", us.getApellidos());
        valores.put("email", us.getEmail());
        valores.put("clave", us.getClave());

        id = db.insert("usuarios", null, valores);
        }
        catch (Exception ex)
        {

        }
        finally {
        db.close();
        }

        return id;
        }

public Usuario obtenerUsuario(long id)
        {
        Usuario us = null;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
        Cursor cregistros = db.rawQuery("select id, nombres, apellidos, email, clave from usuarios where id = " + String.valueOf(id), null);
        cregistros.moveToFirst();
        us = new Usuario();
        us.setId(cregistros.getInt(0));
        us.setNombres(cregistros.getString(1));
        us.setApellidos(cregistros.getString(2));
        us.setEmail(cregistros.getString(3));
        us.setClave(cregistros.getString(4));

        }
        catch (Exception ex)
        {

        }
        finally {
        db.close();
        }
        return us;
        }
public int login (String Email, String contraseña)
{
        Usuario us = null;
        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();


        int a= 0;
        Cursor cregistros = db.rawQuery("select * from usuarios ", null);
        if (cregistros!=null&&cregistros.moveToFirst()){
                do {
                        if(cregistros.getString(3).equals(Email)&&cregistros.getString(4).equals(contraseña)){
                                a++;
                        }

                }while (cregistros.moveToNext());

        }return a;

}
public ArrayList<Usuario> listar()
        {
        ArrayList<Usuario> registros = new ArrayList<>();
        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try {
        Cursor cregistros = db.rawQuery("select id, nombres, apellidos, email, clave from usuarios", null);

        if (cregistros.moveToFirst())
        do {
        Usuario us = new Usuario();
        us.setId(cregistros.getInt(0));
        us.setNombres(cregistros.getString(1));
        us.setApellidos(cregistros.getString(2));
        us.setEmail(cregistros.getString(3));
        us.setClave(cregistros.getString(4));

        registros.add(us);
        } while (cregistros.moveToNext());
        }
        catch (Exception ex)
        {

        }
        finally {
        db.close();
        }

        return registros;
        }

public boolean editar(Usuario us)
        {
        boolean editado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();
        try
        {

        db.execSQL("UPDATE usuarios" +
        "   SET nombres = '" + us.getNombres() + "'," +
        "       apellidos = '" + us.getApellidos() + "'," +
        "       email = '" + us.getEmail() + "'," +
        "       clave = '" + us.getClave() + "'" +
        " WHERE id = '" + String.valueOf(us.getId()) + "'");
        editado=true;

        }
        catch (Exception ex)
        {

        }
        finally {
        db.close();
        }

        return editado;
        }

public boolean eliminar(long id)
        {
        boolean eliminado = false;

        SqliteConex conexion = new SqliteConex(this.contexto);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try
        {
        db.execSQL("DELETE FROM usuarios\n" +
        "      WHERE id = '" + String.valueOf(id) + "'");
        eliminado=true;
        }
        catch (Exception ex)
        {

        }
        finally {
        db.close();
        }

        return eliminado;
        }

public boolean validarUsuario(Usuario us)
        {
        boolean validado = false;

        String emailFijo = "user@gmail.com";
        String claveFija = "root";

        if(us.getEmail().equals(emailFijo) && us.getClave().equals(claveFija))
        validado=true;

        return validado;
        }

public double obtenerArea(double base, double altura)
        {
        double resultado = (base * altura) / 2;

        return resultado;
        }
        }
