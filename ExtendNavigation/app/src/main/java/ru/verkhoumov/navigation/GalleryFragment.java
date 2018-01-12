package ru.verkhoumov.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GalleryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView systemRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_gallery, container, false);

        // Получение названий и изображений.
        String[] systemNames = new String[System.systems.length];
        for (int i = 0; i < systemNames.length; i++) {
            systemNames[i] = System.systems[i].getName();
        }

        int[] systemImages = new int[System.systems.length];
        for (int i = 0; i < systemImages.length; i++) {
            systemImages[i] = System.systems[i].getImageResourceId();
        }

        // Передаём данные адаптеру.
        CardViewAdapter adapter = new CardViewAdapter(systemNames, systemImages);
        systemRecycler.setAdapter(adapter);

        // Используем LinearLayoutManager, чтобы карточки отображались в линейном списке.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        systemRecycler.setLayoutManager(layoutManager);

        return systemRecycler;
    }
}
