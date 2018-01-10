package ru.verkhoumov.arraylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    // Набор данных, выводимых в списке.
    String[] food = {"Пицца", "Картофель фри", "Гамбургер", "Чизбургер", "Твистер"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Объект списка.
        ListView foodList = (ListView) findViewById(R.id.foodList);
        // Массив.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, food);
        // Передаём массив в объект списка.
        foodList.setAdapter(adapter);
    }
}
