package service.webservice.facilito.codigo.com.ejemplocfwebservices;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button buttonJSONparse, buttonXMLparse, buttonGETandPOST, buttonGETandPOSTSecure,
            buttonDataInListView, buttonDataInRecyclerView, buttonThreadChangesInterface,
            buttonChangeTextViewSizeWithAsync, buttonTaskInParallelMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonJSONparse = (Button) findViewById(R.id.button_jsonparse);
        buttonXMLparse = (Button) findViewById(R.id.button_xmlparse);
        buttonGETandPOST = (Button) findViewById(R.id.button_getandpost);
        buttonGETandPOSTSecure = (Button) findViewById(R.id.button_getandpost_seguridad);
        buttonDataInListView = (Button) findViewById(R.id.button_datos_en_listview) ;
        buttonDataInRecyclerView = (Button) findViewById(R.id.button_datos_en_recyclerview) ;
        buttonThreadChangesInterface = (Button) findViewById(R.id.button_cambiar_textview_con_un_hilo) ;
        buttonChangeTextViewSizeWithAsync = (Button)findViewById(R.id.button_cambiar_tamaño_textview_con_async);
        buttonTaskInParallelMode = (Button)findViewById(R.id.button_crear_hilos_paralelos);


        buttonGETandPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GetAndPostActivity.class);
                startActivity(intent);
            }
        });

        buttonXMLparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), XmlActivity.class);
                startActivity(intent);
            }
        });

        buttonJSONparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JsonActivity.class);
                startActivity(intent);
            }
        });

        buttonGETandPOSTSecure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JsonSecureActivity.class);
                startActivity(intent);
            }
        });

        buttonDataInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataInListViewActivity.class);
                startActivity(intent);
            }
        });

        buttonDataInRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataInRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        buttonThreadChangesInterface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThreadChangesInterfaceActivity.class);
                startActivity(intent);
            }
        });

        buttonChangeTextViewSizeWithAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeTextViewSizeWithAsyncActivity.class);
                startActivity(intent);
            }
        });

        buttonTaskInParallelMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TaskInParallelModeActivity.class);
                startActivity(intent);
            }
        });


    }
}
