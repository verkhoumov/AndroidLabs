package ru.verkhoumov.service;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

public class URLService extends IntentService {
    public URLService() {
        super("URLService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Получение адреса сайта из формы.
        String link = intent.getStringExtra("link");

        if (link == null) {
            return;
        }

        // Разбитие строк на части.
        StringTokenizer tokenizer = new StringTokenizer(link);

        int tokenCount = tokenizer.countTokens();
        int index = 0;

        String[] targets = new String[tokenCount];

        while (tokenizer.hasMoreTokens()) {
            targets[index++] = tokenizer.nextToken();
        }

        File saveDir = getFilesDir();

        getPagesAndSave(saveDir, targets);
    }

    private void getPagesAndSave(File saveDir, String[] targets) {
        for (String target: targets) {
            URL url = null;

            try {
                url = new URL(target);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String fileName = target.replaceAll("/", "@@").replaceAll(":", "##");

            File file = new File(saveDir, fileName);

            PrintWriter writer = null;
            BufferedReader reader = null;

            try {
                String line;

                writer = new PrintWriter(file);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
