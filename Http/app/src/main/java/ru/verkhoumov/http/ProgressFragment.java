package ru.verkhoumov.http;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProgressFragment extends Fragment {
    public TextView contentView;
    public String contentText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        contentView = (TextView) view.findViewById(R.id.content);

        if (contentText != null) {
            contentView.setText(contentText);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (contentText == null) {
            new ProgressTask().execute();
        }
    }

    class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {
            String content;

            try {
                content = getContent("http://site45min.ru");
            } catch (IOException e) {
                content = e.getMessage();
            }

            return content;
        }

        @Override
        protected void onPostExecute(String content) {
            contentText = content;
            contentView.setText(content);

            Toast
                    .makeText(getActivity(), "Данные успешно загружены", Toast.LENGTH_SHORT)
                    .show();
        }

        private String getContent(String path) throws IOException {
            BufferedReader reader = null;

            try {
                // Формируем ссылку для запроса.
                URL url = new URL(path);

                // Открываем новое http-соединение.
                HttpURLConnection c = (HttpURLConnection) url.openConnection();

                // Настраиваем соединение.
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();

                // Читаем данные по ссылке.
                reader = new BufferedReader(
                        new InputStreamReader(c.getInputStream())
                );

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

               return buffer.toString();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }
}
