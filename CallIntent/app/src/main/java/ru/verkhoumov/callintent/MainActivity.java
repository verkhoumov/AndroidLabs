package ru.verkhoumov.callintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключаем все кнопки.
        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnSend = (Button) findViewById(R.id.btnSend);

        // Обработчик нажатия на кнопку "Позвонить".
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:89216559291");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                checkIntent(callIntent);
            }
        });

        // Обработчик нажатия на кнопку "Открыть на карте".
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:59.9569097,30.3086695?z=17");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                checkIntent(mapIntent);
            }
        });

        // Обработчик нажатия на кнопку "Открыть веб-страницу".
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://verkhoumov.ru/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                checkIntent(webIntent);
            }
        });

        // Обработчик нажатия на кнопку "Отправть E-mail".
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(
                        Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", "verkhoumov@yandex.ru", null)
                );

                // Дополнительные параметры сообщения.
                emailIntent.putExtra(
                        Intent.EXTRA_EMAIL,
                        new String[]{"asel-romanova@mail.ru"}
                );
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Тестовое сообщение");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Сообщение отправлено через тестовое приложение на Android.");

                // Заупск ПО.
                checkIntent(emailIntent);
            }
        });
    }

    // Проверка, есть ли хотя бы 1 обработчик события.
    private boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);

        return activities.size() > 0;
    }

    // Проверка, установлено ли на телефоне соответствующее ПО и его запуск.
    private void checkIntent(Intent intent) {
        if (isIntentSafe(intent)) {
            startActivity(intent);
        } else {
            Toast
                    .makeText(getApplicationContext(), "На Вашем смартфоне отсутствует необходимое ПО!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
