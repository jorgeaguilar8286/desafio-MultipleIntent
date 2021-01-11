package cl.jav.desafiomultipleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;




public class ResultActivity extends AppCompatActivity {
    private Button webBtn;
    private Button shareBtn;
    private String url;
    private Boolean resultado_imagen;
    private static final String TAG = "Results_Activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d(TAG,"metodo onCreate");
        url = getIntent().getStringExtra("url_desafiolatam");
        resultado_imagen = getIntent().getBooleanExtra("resultado_imagen", false);
        webBtn = findViewById(R.id.btnURL);
        shareBtn = findViewById(R.id.btnCompartir);

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirURL();
            }
        });

        //Botón Compartir
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirDatos();
            }
        });
    }
    private void compartirDatos() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartir Datos");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "URL" + url + "\n" + "Imagen se cargó " + resultado_imagen.toString());
        startActivity(Intent.createChooser(shareIntent, "Compartir Datos"));
    }

    private void abrirURL() {
        Uri uriWeb = Uri.parse(url);
        Intent intentURL = new Intent(Intent.ACTION_VIEW, uriWeb);
        startActivity(intentURL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(getApplicationContext(), "requestCode: " + requestCode, Toast.LENGTH_LONG).show();
        Log.d(TAG, "Request Code: " + requestCode);
        //Log.d(TAG, "Result Code: " + resultCode);
        /*
        if (requestCode == 1) {
            Toast.makeText(getApplicationContext(), "URL: " + url, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Resultado imágen: " + resultado_imagen, Toast.LENGTH_LONG).show();
        } */
    }


}
