package ru.verkhoumov.timer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int countdown = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем сохранённые ранее данные.
        if (savedInstanceState != null) {
            countdown = savedInstanceState.getInt("countdown");
            running = savedInstanceState.getBoolean("running");
        }

        // Запуск таймера.
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("countdown", countdown);
        savedInstanceState.putBoolean("running", running);
    }

    // Обработка запуска таймера.
    public void onClickStart(View view) {
        running = true;
    }

    // Обработка остановки таймера.
    public void onClickStop(View view) {
        running = false;
    }

    // Обработка сброса таймера.
    public void onClickReset(View view) {
        running = false;
        countdown = 0;
    }

    // Запускаем обработчик, каждую секунду обновляем данные.
    public void runTimer() {
        // Получаем ссылку на текстовое поле.
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        // Создаём новый Handler.
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                // Рассчитываем количество часов, минут и секунд, прошедших с запуска таймера.
                int hours = countdown / 3600;
                int minutes = (countdown % 3600) / 60;
                int seconds = countdown % 60;

                // Форматируем время и выводим в текстовом поле.
                String time = String.format("%d:%02d:%02d", hours, minutes, seconds);
                timeView.setText(time);

                // Увеличиваем количество прошедших секунд.
                if (running) {
                    ++countdown;
                }

                // Запланировать повторное выполнение подпрограммы через 1 секунду.
                handler.postDelayed(this, 1000);
            }
        });
    }
}
