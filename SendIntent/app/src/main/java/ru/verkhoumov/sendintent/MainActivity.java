package ru.verkhoumov.sendintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "hello, dude!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Обработка нажатия на кнопку.
    public void sendMessage(View view) {
        // Создание объекта Intent для вызова новой Activity.
        Intent intent = new Intent(this, DisplayMessage.class);
        // Текущее текстовое поле.
        EditText editText = (EditText) findViewById(R.id.edit_message);
        // Содержимое текстового поля.
        String message = editText.getText().toString();
        // Добавляем ключ -> значение.
        intent.putExtra(EXTRA_MESSAGE, message);
        // Запуск новой Activity.
        startActivity(intent);
    }
}
