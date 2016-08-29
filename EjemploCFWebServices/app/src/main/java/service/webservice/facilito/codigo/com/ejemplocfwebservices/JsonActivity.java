package service.webservice.facilito.codigo.com.ejemplocfwebservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.POJO.Usuario;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.Parses.UsuarioJSONParser;


public class JsonActivity extends AppCompatActivity {

    Button buttonCargarDatos;
    TextView textView;
    ProgressBar progressBar;
    List<Usuario> usuarioList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        buttonCargarDatos = (Button) findViewById(R.id.button_cargardatos_activity_json);
        buttonCargarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnLine()){
                    pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php"); //JSON sin seguridad

                }else{
                    Toast.makeText(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textView = (TextView) findViewById(R.id.textview_activity_json);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_activity_json);
        progressBar.setVisibility(View.INVISIBLE);

        textView.setMovementMethod(new ScrollingMovementMethod()); // Para hacer caso al código -> android:scrollbars="vertical"


    }

    public void cargarDatos(){

        if(usuarioList != null){
            for (Usuario usuario: usuarioList) { // foreach
                textView.append(usuario.getTwitter()+"\n"); // para recoger el twitter del usuario. También podríamos recoger el "id" y el "nombre"

            }
        }

    }

    public boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    public void pedirDatos(String uri){
        MyTask task = new MyTask();
        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        task.execute(uri);

    }


    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) { // Recibimos un String. La URL
            String content = HttpManager.getData(params[0]); // Cadena de contenido. Sin seguridad
            return content;

        }

        @Override
        protected void onPostExecute(String result) { // result es el resultado de doInBackground
            super.onPostExecute(result);
            usuarioList = UsuarioJSONParser.parser(result); // PARA PARSEAR EL XML
            cargarDatos();
            progressBar.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            // Nothing
        }
    }
}
