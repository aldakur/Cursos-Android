package service.webservice.facilito.codigo.com.ejemplocfwebservices;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TaskInParallelModeActivity extends AppCompatActivity {

    Button boton;
    TextView textView;
    ProgressBar progressBar;
    List<MyTask> taskList; //Lista de hilos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_in_parallel_mode);

        boton = (Button) findViewById(R.id.button_start_activity_task_in_parallel_mode);
        textView = (TextView) findViewById(R.id.textview_activity_task_in_parallel_mode);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_activity_task_in_parallel_mode);
        progressBar.setVisibility(View.INVISIBLE);

        textView.setMovementMethod(new ScrollingMovementMethod()); // android:scrollbars="vertical" kodeari kaso egiteko

        taskList = new ArrayList<>(); // Inicializamos la lista



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); //Para ejecutar los hilos de forma paralela
                //task.execute(); //Ejecuta los hilos de forma serial
            }
        });
    }

    public void cargarDatos(String datos){
        textView.append(datos+"\n");
    }

    private class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargarDatos("Inicio de carga");

            if(taskList.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            taskList.add(this); // Agregando el hilo actual al la lista

        }

        @Override
        protected String doInBackground(String... params) {

            for(int i=0; i<=5; i++){
                //No podemos cambiar la interface en doInBackground pq es el hilo principal. Por eso podemos usar publishProgress dentro de el
                publishProgress("Numero: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Terminamos";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargarDatos(s);
            taskList.remove(this); // Eliminar el hilo que acaba de terminar
            if(taskList.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            cargarDatos(values[0]);
        }
    }
}
