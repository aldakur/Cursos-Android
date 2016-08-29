package service.webservice.facilito.codigo.com.ejemplocfwebservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.List;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.Adapters.UsuariosAdapter;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.POJO.Usuario;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.Parses.UsuarioJSONParser;

public class DataInRecyclerViewActivity extends AppCompatActivity {

    List<Usuario> usuarioList;

    RecyclerView recyclerView;
    UsuariosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_in_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_activity_data_in_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(isOnLine()){
            pedirDatos("http://maloschistes.com/maloschistes.com/jose/webserviceI.php"); // Webservices con las imagenes en el apartado Twitter

        }else{
            Toast.makeText(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarDatos(){
        adapter = new UsuariosAdapter(getApplicationContext(), usuarioList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

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
            // nothing

        }

        @Override
        protected String doInBackground(String... params) { // Recibimos un String. La URL
            String content = HttpManager.getData(params[0]); // Cadena de contenido. Sin seguridad
            return content;

        }

        @Override
        protected void onPostExecute(String result) { // result es el resultado de doInBackground
            super.onPostExecute(result);
            usuarioList = UsuarioJSONParser.parser(result); // PARA PARSEAR EL JSON

            if(result == null){

                Toast.makeText(DataInRecyclerViewActivity.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
                // progressBar.setVisibility(View.INVISIBLE);
                return;

            }

            cargarDatos();

        }

        @Override
        protected void onProgressUpdate(String... values) {
            // nothing
        }
    }
}
