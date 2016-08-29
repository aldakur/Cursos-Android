package service.webservice.facilito.codigo.com.ejemplocfwebservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadChangesInterfaceActivity extends AppCompatActivity {

    TextView texto;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_changes_interface);

        texto = (TextView) findViewById(R.id.textview_start_activity_thread_changes_interfaces);
        boton = (Button) findViewById(R.id.button_start_activity_thread_changes_interfaces);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String txt = "This is a text";
                        // this is a way using post
                        texto.post(new Runnable() {
                            @Override
                            public void run() {
                                texto.setText(txt);
                            }
                        });
                        // this is another way using runOnUiThread instead post
                        /*
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                texto.setText(txt);
                            }
                        });*/
                    }
                }).start();
            }
        });
    }
}
