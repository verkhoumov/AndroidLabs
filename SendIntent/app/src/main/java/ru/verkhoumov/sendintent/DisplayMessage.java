package ru.verkhoumov.sendintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Получаем содержимое поля из объекта Intent.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Создание текстового поля.
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Установка текстового поля в системе компоновки Activity.
        setContentView(textView);
    }
}
