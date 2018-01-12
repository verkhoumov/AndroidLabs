package ru.verkhoumov.navigation;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private String[] captions;
    private int[] imageIds;

    // Ссылка на представления.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        /**
         * В компоненте RecyclerView отображаются карточки, поэтому указываем,
         * что ViewHolder содержит представления CardView.
         */
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    // Передача данных адаптеру через конструктор.
    public CardViewAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    // Создание нового представления.
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Генерация нового представления.
        CardView view = (CardView) LayoutInflater
                .from(parent.getContext())
                // Макет, используемый для создания представления на основе полученных данных.
                .inflate(R.layout.card_view, parent, false);

        return new ViewHolder(view);
    }

    // Наполнение представления данными.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);

        // Изображение.
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);

        // Текст.
        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
    }

    // Количество вариантов в наборе данных.
    @Override
    public int getItemCount() {
        return captions.length;
    }
}
