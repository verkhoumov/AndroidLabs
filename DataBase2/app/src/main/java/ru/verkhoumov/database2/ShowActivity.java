package ru.verkhoumov.database2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Достаём ID контакта, переданный через Intent.
            contactId = extras.getLong("id");

            // Соединение с базой данных.
            DataBase dataBase = new DataBase(this);

            // Получаем информацию и контакте.
            Contact contact = dataBase.getContact(contactId);

            // Если контакт найден, подставляем данные в таблицу.
            if (contact != null) {
                ((TextView) findViewById(R.id.first_name)).setText(contact.getFirstName());
                ((TextView) findViewById(R.id.last_name)).setText(contact.getLastName());
                ((TextView) findViewById(R.id.phone)).setText(contact.getPhone());
                ((TextView) findViewById(R.id.email)).setText(contact.getEmail());
            } else {
                Log.d("db", "Contact undefined");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteContact() {
        new AlertDialog
                .Builder(this)
                    .setTitle("Please confirm")
                    .setMessage("Are you sure you want to delete this contact?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            DataBase dataBase = new DataBase(getApplicationContext());
                            dataBase.deleteContact(contactId);
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
    }
}
