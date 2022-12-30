package com.juanjiga.db_alumnos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    TextView textView;
    Button insertar, borrar, actualizar, consultar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        insertar = (Button)findViewById(R.id.buttonInsertar);
        borrar = (Button)findViewById(R.id.buttonBorrar);
        actualizar = (Button)findViewById(R.id.buttonActualizar);
        consultar = (Button)findViewById(R.id.buttonConsultar);

        actualizar.setOnClickListener(this);
        borrar.setOnClickListener(this);
        insertar.setOnClickListener(this);
        consultar.setOnClickListener(this);


        //Abrimos la base de datos en modo escritura
        Helper_db helper = new Helper_db(this);

        db = helper.getWritableDatabase();


        //Comrpuebo que la BD se abre correctamente
        if(db != null)
        {
            for(int i=1; i<=5; i++)
            {
                //Generar valores
                int codigo = i;
                String campo_nombre = "Alumnito" + i;

                //Datos de prueba
                db.execSQL("INSERT INTO " + Helper_db.TABLE_NAME +" (nombre) " +
                        //"VALUES (" + codigo + ", '" + campo_nombre +"')");
                        "VALUES ('" + campo_nombre +"')");
            }
            Toast.makeText(getApplicationContext(), "Se guardarón los registros",
                    Toast.LENGTH_LONG).show();
            //Cerrar BD
            //db.close();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonInsertar:
                //Insertar un registro
                db.execSQL("INSERT INTO " + Helper_db.TABLE_NAME + "(nombre,direccion) VALUES ('" + editText.getText() + "','dirección de prueba') ");
                break;

            case R.id.buttonBorrar:
                //Eliminar un registro
                db.execSQL("DELETE FROM nombre_tabla WHERE nombre='" + editText.getText() + "'");
                break;

            case R.id.buttonConsultar:
                String[] args = new String[] {editText.getText().toString()};
                Cursor c = db.rawQuery(" SELECT * FROM nombre_tabla WHERE nombre=? ", args);
                if (c.moveToFirst()) {
                    textView.setText(""); // Vacio el textview
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        Integer codigo= c.getInt(0);
                        String nombre = c.getString(1);
                        String direccion = c.getString(2);
                        textView.append("ID " + codigo + " Nombre: " + nombre + " Dirección: " + direccion + "\n");
                    } while(c.moveToNext());
                }
                break;

            case R.id.buttonActualizar:
                //Actualizar un registro
                db.execSQL("UPDATE nombre_tabla SET nombre='" + editText.getText() + "' WHERE codigo=5" );
                break;

            default:

                break;
        }
    }
}