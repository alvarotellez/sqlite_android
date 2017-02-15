package es.iesnervion.atellez.sqlite_android;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edCantidad, edNota, edTipo;
    Button btnGuardar;
    Float cantidad;
    String nota, tipo, fechaCreacion;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCantidad = (EditText) findViewById(R.id.etCantidad);
        edNota = (EditText) findViewById(R.id.etNota);
        edTipo = (EditText) findViewById(R.id.etTipo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        AnotacionSQLiteHelper nuevaAnotacion = new AnotacionSQLiteHelper(this, "DBAnotacion", null, 1);

        db = nuevaAnotacion.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btnGuardar:
               cantidad =  Float.parseFloat(edCantidad.getText().toString()) ;
               nota = edNota.getText().toString();
               tipo = edTipo.getText().toString();
               //Obtenemos la fecha actual del sistema
               Calendar cc = Calendar.getInstance();
               int year=cc.get(Calendar.YEAR);
               //Ojo!!! Enero en java es 0 por eso hay que incrementarlo en 1
               int month=cc.get(Calendar.MONTH)+1;
               int mDay = cc.get(Calendar.DAY_OF_MONTH);
               fechaCreacion = mDay +":"+month+":"+year;

               if (!(cantidad>0 && nota.isEmpty() && tipo.isEmpty() && fechaCreacion.isEmpty())){

                   String inserccion = "INSERT INTO Anotaciones (cantidad, nota, tipo, fechaCreacion) VALUES('"+cantidad+"','"+nota+"','"+tipo+"','"+fechaCreacion+"')";
                   db.execSQL(inserccion);
                   db.close();
                   Toast.makeText(this, "La anotación se ha creado con exito",Toast.LENGTH_SHORT).show();
                   edCantidad.setText(" ");
                   edNota.setText(" ");
                   edTipo.setText(" ");
               }else {
                   Toast.makeText(this, "La anotación no se ha podido crear",Toast.LENGTH_SHORT).show();
               }
               break;

           //case 2:
               //Opción para actualizar un dato de la tabla
               //String sql = "UPDATE Usuarios SET nombre='" + nom +"' WHERE codigo=" + cod;
               //db.execSQL(sql);
               //break;
           //case 3:
               //Opcion para borrar de la tabla
               //db.delete("Usuarios", "codigo=" + cod, null);
               //break;
       }

    }
}
