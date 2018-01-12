package ru.verkhoumov.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ImportFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView mediaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_import, container, false);

        // Получение названий и изображений.
        String[] mediaNames = new String[Media.medias.length];
        for (int i = 0; i < mediaNames.length; i++) {
            mediaNames[i] = Media.medias[i].getName();
        }

        int[] mediaImages = new int[Media.medias.length];
        for (int i = 0; i < mediaImages.length; i++) {
            mediaImages[i] = Media.medias[i].getImageResourceId();
        }

        // Передаём данные адаптеру.
        CardViewAdapter adapter = new CardViewAdapter(mediaNames, mediaImages);
        mediaRecycler.setAdapter(adapter);

        // Используем LinearLayoutManager, чтобы карточки отображались в линейном списке.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mediaRecycler.setLayoutManager(layoutManager);

        return mediaRecycler;
    }
}
