package service.webservice.facilito.codigo.com.ejemplocfwebservices;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ChangeTextViewSizeWithAsyncActivity extends AppCompatActivity {

    Button boton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text_view_size_with_async);

        boton = (Button) findViewById(R.id.button_start_activity_change_text_view_size_with_async);
        textView = (TextView) findViewById(R.id.textview_activity_change_text_view_size_with_async);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(100); // El 100 indica el tamaño maximo del valor del textView

            }
        });
    }


    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Integer... params) { // Recibimos un Integer
            int max = params[0];

                for(int i=1; i <= max; i++){
                    //No podemos cambiar la interface en doInBackground pq es el hilo principal. Por eso podemos usar publishProgress dentro de el doInBackground
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }

            return "Fin";

        }

        @Override
        protected void onPostExecute(String result) { // result es el resultado de doInBackground
            super.onPostExecute(result);
            textView.append("\n"+result);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate();
            int contador = values[0]; // Agarramos el valor que recibimos. Al recibir solo uno, agarramos el de la posición cero.
            String texto = "Contador"+contador;
            textView.setText(texto);
            textView.setTextSize(contador);
        }
    }
}
