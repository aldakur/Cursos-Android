package service.webservice.facilito.codigo.com.ejemplocfwebservices;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import service.webservice.facilito.codigo.com.ejemplocfwebservices.POJO.Usuario;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.Parses.UsuarioXMLParser;

public class XmlActivity extends AppCompatActivity {

    Button buttonCargarDatos;
    TextView textView;
    ProgressBar progressBar;
    String method;
    // List<MyTask> taskList; //Lista de hilos
    List<Usuario> usuarioList;

    ListView listView;
    // MyAdapter adapter;


    //RecyclerView recyclerView;
    //UsuariosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        buttonCargarDatos = (Button) findViewById(R.id.button_cargardatos_activity_xml);
        buttonCargarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnLine()){
                    pedirDatos("http://www.harrika.net/usuarios.xml");

                }else{
                    Toast.makeText(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textView = (TextView) findViewById(R.id.textview_activity_xml);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_activity_xml);
        progressBar.setVisibility(View.INVISIBLE);

        //listView = (ListView) findViewById(R.id.listview_activity_xml);
        //recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(linearLayoutManager);




        textView.setMovementMethod(new ScrollingMovementMethod()); // Para hacer caso al código -> android:scrollbars="vertical"

        // taskList = new ArrayList<>(); // Inicializamos la lista


    }

    public void cargarDatos(){
        // textView.append(datos+"\n"); // Unimos los datos (lo que sería la URL con los parametros GET)
       if(usuarioList != null){
            for (Usuario usuario: usuarioList) { // foreach
                textView.append(usuario.getNombre()+"\n"); // para recoger el nombre del usuario. También podríamos recoger el "id" y el "twitter"

            }
        }
        // adapter = new MyAdapter(getApplicationContext(), usuarioList);
        // listView.setAdapter(adapter);

        //adapter = new UsuariosAdapter(getApplicationContext(), usuarioList);
        //recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);


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
        //RequestPackage requestPackage = new RequestPackage();
        // requestPackage.setMethod("GET");
        // requestPackage.setMethod("POST");
        //requestPackage.setMethod(method);
        //requestPackage.setUri(uri);
        //requestPackage.setParam("parametro1", "valor1");
        //requestPackage.setParam("parametro2", "valor2");
        //requestPackage.setParam("parametro3", "valor3");
        //requestPackage.setParam("parametro4", "valor4");

        //task.execute(requestPackage);

    }



    // private class MyTask extends AsyncTask<String, String, String>{
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // cargarDatos("Inicio de carga");
            progressBar.setVisibility(View.VISIBLE);

            /*
            if(taskList.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            taskList.add(this); // Agregando el hilo actual al la lista
            */

        }

        @Override
        protected String doInBackground(String... params) { // Recibimos un String. La URL
                /*
                for(int i=0; i<=5; i++){
                    //No podemos cambiar la interface en doInBackground pq es el hilo principal. Por eso podemos usar publishProgress dentro de el
                    publishProgress("Numero: "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
            String content = HttpManager.getData(params[0]); // Cadena de contenido. Sin seguridad
            return content;

        }

        @Override
        protected void onPostExecute(String result) { // result es el resultado de doInBackground
            super.onPostExecute(result);
            usuarioList = UsuarioXMLParser.parser(result); // PARA PARSEAR EL XML

            /*
            tasks.remove(this);
            if(tasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
            */

            /*
            if(result == null){

                Toast.makeText(XmlActivity.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                return;

            }
            */
            cargarDatos();
            progressBar.setVisibility(View.INVISIBLE);

            //cargarDatos(result);
            /*
            taskList.remove(this);
            if(taskList.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
            */

        }

        @Override
        protected void onProgressUpdate(String... values) {
            //cargarDatos(values[0]);
        }
    }
}