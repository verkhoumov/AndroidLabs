package ru.verkhoumov.database2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void cancel(View view) {
        finish();
    }

    public void addContact(View view) {
        // Ссылки на поля формы.
        TextView firstName = (TextView) findViewById(R.id.first_name);
        TextView lastName = (TextView) findViewById(R.id.last_name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);

        // Содержимое полей.
        String _firstName = firstName.getText().toString();
        String _lastName = lastName.getText().toString();
        String _phone = phone.getText().toString();
        String _email = email.getText().toString();

        // Новый контакт.
        Contact contact = new Contact(_firstName, _lastName, _phone, _email);

        // Добавляем контакт в базу данных.
        DataBase dataBase = new DataBase(this);
        dataBase.addContact(contact);

        finish();
    }
}
