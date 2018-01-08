package ru.verkhoumov.whattowatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ChooseSeriesActivity extends AppCompatActivity {
    private SeriesList work = new SeriesList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_series);
    }

    public void onClickChooseSeries(View view) {
        // Получаем ссылку на поле с текстом и выпадающий список.
        TextView genre = (TextView) findViewById(R.id.genre);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Определяем выбранный пункт списка.
        String seriesPlan = String.valueOf(spinner.getSelectedItem());
        // Подставляем выбранный пункт списка в текстовое поле.
        genre.setText(seriesPlan);

        List<String> genreList = work.getGenre(seriesPlan);
        StringBuilder genreModified = new StringBuilder();
        for (String genres: genreList) {
            genreModified.append(genres).append('\n');
        }
        genre.setText(genreModified);
    }
}
