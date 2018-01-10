package ru.verkhoumov.sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OnItemClickListener - пложенный класс по отношению к классу AdapterView. ListView - подкласс AdapterView.
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            // Дополнительная информация об элементе списка (представление и позиция).
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Вызов новой активности CategoryActivity.
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
            }
        };

        // Назначаем слушатель для списка.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(itemClickListener);
    }
}
