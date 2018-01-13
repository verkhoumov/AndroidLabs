package ru.verkhoumov.service;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

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

        // Ссылка на выпадающий список.
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Сохранённые файлы.
        File saveDir = getFilesDir();

        if (saveDir.exists()) {
            final String[] files = saveDir.list();
            String[] _files = new String[files.length];

            for (int i = 0; i < _files.length; i++) {
                _files[i] = files[i].toString().replaceAll("@@", "/").replaceAll("##", ":");
            }

            // Используем адаптер для построения выпадающего списка.
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, _files);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);

            // При выборе пункта из списка загружаем страницу.
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    // Элемент из выбранного пункта.
                    Object itemByPosition = adapterView.getItemAtPosition(position);

                    // Загружаем файл с соответствующим именем.
                    File file = new File(getFilesDir(), files[(int) id]);

                    // Используем специальное окно для загрузки веб-страницы.
                    WebView webView = (WebView) findViewById(R.id.webview);

                    if (file.exists()) {
                        FileReader fileReader = null;
                        BufferedReader bufferedReader = null;

                        try {
                            fileReader = new FileReader(file);
                            bufferedReader = new BufferedReader(fileReader);

                            StringBuilder buffer = new StringBuilder();
                            String line;

                            while ((line = bufferedReader.readLine()) != null) {
                                buffer.append(line);
                            }

                            webView.loadDataWithBaseURL(null, buffer.toString(), "text/html", "UTF-8", null);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        webView.loadUrl(itemByPosition.toString());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // Required.
                }
            });
        }
    }
}
