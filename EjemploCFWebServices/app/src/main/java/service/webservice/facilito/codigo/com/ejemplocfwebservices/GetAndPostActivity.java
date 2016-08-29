package service.webservice.facilito.codigo.com.ejemplocfwebservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GetAndPostActivity extends AppCompatActivity {

    Button botonGet, botonPost;
    TextView textView;
    ProgressBar progressBar;
    String method;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_and_post);

        botonGet = (Button) findViewById(R.id.botonget);
        botonPost = (Button) findViewById(R.id.botonpost);

        textView = (TextView) findViewById(R.id.textview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);


        botonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnLine()){
                    method = "GET";
                    pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservicesend.php"); // Web service enviando GET y POST

                }else{
                    Toast.makeText(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }

            }
        });

        botonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnLine()){
                    method= "POST";
                    pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservicesend.php"); // Web service enviando GET y POST

                }else{
                    Toast.makeText(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void cargarDatos(String datos){
        textView.append(datos+"\n"); // Unimos los datos (lo que sería la URL con los parametros GET)

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
        //task.execute(uri);
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod(method);
        requestPackage.setUri(uri);
        requestPackage.setParam("parametro1", "valor1");
        requestPackage.setParam("parametro2", "valor2");
        requestPackage.setParam("parametro3", "valor3");
        requestPackage.setParam("parametro4", "valor4");

        task.execute(requestPackage);

    }

    private class MyTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(RequestPackage... params) { // Recibimos un RequestPackage

            String content = HttpManager.getData(params[0]); //Cadena de contenido. Sin seguridad
            return content;

        }

        @Override
        protected void onPostExecute(String result) { // result es el resultado de doInBackground
            super.onPostExecute(result);

            if(result == null){

                Toast.makeText(GetAndPostActivity.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                return;

            }

            cargarDatos(result);
            progressBar.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            // nothing
        }
    }

}
