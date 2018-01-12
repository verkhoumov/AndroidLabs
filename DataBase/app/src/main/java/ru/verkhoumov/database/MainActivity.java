package ru.verkhoumov.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name, phone;
    Button insert, show, delete;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Поля ввода данных и кнопки.
        name = (EditText) findViewById(R.id.nameEdit);
        phone = (EditText) findViewById(R.id.phoneEdit);
        insert = (Button) findViewById(R.id.insertBtn);
        show = (Button) findViewById(R.id.showBtn);
        delete = (Button) findViewById(R.id.deleteBtn);

        // База данных.
        dataBase = new DataBase(this);
        final SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        // Обработчики кнопок.
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name = name.getText().toString();
                String _phone = phone.getText().toString();

                // Добавляем новую запись в базу данных.
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", _name);
                contentValues.put("phone", _phone);
                sqLiteDatabase.insert("Contact", null, contentValues);

                // Логгируем событие.
                Log.d("log", "Data inserted: name = " + _name + ", phone = " + _phone);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = sqLiteDatabase.query("Contact", null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    Log.d("log", "Data selected:");

                    do {
                        int _id = cursor.getInt(cursor.getColumnIndex("id"));
                        String _name = cursor.getString(cursor.getColumnIndex("name"));
                        String _phone = cursor.getString(cursor.getColumnIndex("phone"));

                        Log.d("log", "id = " + _id + ", name = " + _name + ", phone = " + _phone);
                    } while (cursor.moveToNext());
                } else {
                    Log.d("log", "Data not found!");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase.delete("Contact", null, null);

                Log.d("log", "Data deleted");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
