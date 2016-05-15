package com.example.fia_lab1d6.examenlp3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.security.Principal;

public class MainActivity extends AppCompatActivity {

    private CheckBox guardarcuenta;
    Button button;
    EditText e1,e2;
    int  cont=3;
    String user ="lady", clave="123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable imagen = getResources().getDrawable(R.drawable.images);
        Bitmap originalBitmap = ((BitmapDrawable) imagen).getBitmap();
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());
        ImageView imagenes = (ImageView) findViewById(R.id.imageView3);
        imagenes.setImageDrawable(roundedDrawable);

        guardarcuenta=(CheckBox)findViewById(R.id.CBcuenta);
        e1=(EditText) findViewById(R.id.txtusuario);
        e2=(EditText) findViewById(R.id.txtclave);
        CargarPreferencia();


        final Button button = (Button) findViewById(R.id.btningresar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GuardarPreferencia();
                String u, c;
                u=e1.getText().toString();
                c=e2.getText().toString();
                if(user.equals(u) && clave.equals(c)){
                    Intent intent1=new Intent(MainActivity.this, Perfil.class);
                    intent1.putExtra("user",u);
                    startActivity(intent1);
                    Limpiar();

                }else {
                    Snackbar.make(view, "Usuario Incorrecto", Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                    cont--;
                    if(cont==0){
                        finish();
                    }
                }
            }
        });

    }
    public void CargarPreferencia(){
        SharedPreferences mispreferencias=getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        guardarcuenta.setChecked(mispreferencias.getBoolean("Checked",false));
        e1.setText(mispreferencias.getString("Nombre",""));
        e2.setText(mispreferencias.getString("Contraseña",""));
    }
    public void GuardarPreferencia(){
        SharedPreferences mispreferencias=getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mispreferencias.edit();
        boolean valor= guardarcuenta.isChecked();
        String nombre = e1.getText().toString();
        String contraseña = e2.getText().toString();
        editor.putBoolean("Checked", valor);
        editor.putString("Nombre",nombre);
        editor.putString("Contraseña",contraseña);
        editor.commit();
    }

    public void Limpiar(){
        e1.setText("");
        e2.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
