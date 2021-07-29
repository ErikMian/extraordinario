package com.erikmian.extraordinario;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class reproducirvideo extends AppCompatActivity {
    TextView textnombre,textedad;
    String guardarnombre,guardaredad,guardargenero;
    VideoView reproducirpelicula;
    Button btplay,btpausa,btregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproducirvideo);
        textnombre = (TextView) findViewById(R.id.textViewnombre);
        textedad = (TextView) findViewById(R.id.textViewedad);


        btplay =(Button) findViewById(R.id.buttonplay);
        btpausa =(Button) findViewById(R.id.buttonpausa);
        btregresar =(Button) findViewById(R.id.buttonregresar);

        guardarnombre="Nombre";
        guardaredad="Edad";
        guardargenero="Genero";
        LeerNombre();
        LeerEdad();

        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anabelle3vieneacasa);
        reproducirpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        reproducirpelicula.setVideoURI(myuri);
        reproducirpelicula.setMediaController(new MediaController(this));
        reproducirpelicula.requestFocus();

        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirpelicula.start();;
            }
        });

        btpausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirpelicula.pause();;
            }
        });
        btregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "usted a regresado al menu ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
    //Leer los nombres
    private void LeerNombre() {
        String textoffilenombre = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(guardarnombre)));
            textoffilenombre = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffilenombre != null) {
                textnombre.setText(textoffilenombre);

            } else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio", Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    //Leer las edades
    private void LeerEdad() {
        String textoffileedad = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(guardaredad)));
            textoffileedad = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffileedad != null) {
                textedad.setText(textoffileedad);
                //Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio", Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

}