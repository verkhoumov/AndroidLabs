package ru.verkhoumov.whattowatch;

import java.util.ArrayList;
import java.util.List;

public class SeriesList {
    List<String> getGenre(String spinner) {
        // Преобразуем название жанра в нижний регистр.
        spinner = spinner.toLowerCase();

        // Формируем список сериалов по каждому жанру.
        List<String> genre = new ArrayList<String>();

        if (spinner.equals("комедия")) {
            genre.add("Друзья");
            genre.add("Хэппи");
            genre.add("Мозгоправ");
        } if (spinner.equals("фэнтези")) {
            genre.add("Игра престолов");
            genre.add("Полуночный Техас");
            genre.add("Американские боги");
        } if (spinner.equals("ужасы")) {
            genre.add("Ходячие мертвецы");
            genre.add("Очень странные дела");
            genre.add("Американская история ужасов");
        } if (spinner.equals("фантастика")) {
            genre.add("Футурама");
            genre.add("Доктор Кто");
            genre.add("Мир Дикого запада");
        } if (spinner.equals("криминал")) {
            genre.add("Во все тяжкие");
            genre.add("Шерлок");
            genre.add("Мажор");
        }

        return genre;
    }
}
