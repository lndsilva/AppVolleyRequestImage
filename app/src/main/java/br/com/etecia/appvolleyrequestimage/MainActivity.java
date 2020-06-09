package br.com.etecia.appvolleyrequestimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity {
    Button btnCarregar;
    ImageView imgServidor;
    String server_url = "http://192.168.100.5/projetovolleyapi/logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anuncio os objetos do xml para o java
        btnCarregar = findViewById(R.id.btnCarregarImagem);
        imgServidor = findViewById(R.id.imgServidor);

        //criando o carrega imagem
        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageRequest imageRequest = new ImageRequest(server_url,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                imgServidor.setImageBitmap(response);
                            }
                        }, 0, 0,
                        ImageView.ScaleType.CENTER_CROP, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),
                                        "Erro ao visualizar a imagem",
                                        Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
                MySingleton.getInstance(getApplicationContext()).addToRequestQue(imageRequest);
            }
        });
    }
}
