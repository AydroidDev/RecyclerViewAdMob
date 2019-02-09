package br.com.alessanderleite.recyclerviewadmob;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> recyclerItems = new ArrayList<>();
    private Context context;

    private static final int ITEM_FRUIT = 0;
    private static final int ITEM_BANNER_AD = 1;

    public RecyclerAdapter(List<Object> recyclerItems, Context context) {
        this.recyclerItems = recyclerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType) {

            case ITEM_FRUIT:
                View fruitView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item_layout,
                        viewGroup, false);
                return new FruitViewHolder(fruitView);

            case ITEM_BANNER_AD:

            default:
                View bannerAdView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banner_ad_container,
                        viewGroup, false);
                return new BannerAdViewHolder(bannerAdView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        int viewType = getItemViewType(position);

        switch (viewType) {

            case ITEM_FRUIT:

                FruitViewHolder fruitViewHolder = (FruitViewHolder) viewHolder;
                Fruit fruit = (Fruit) recyclerItems.get(position);

                int imageId = context.getResources().getIdentifier(fruit.getFruitName().toLowerCase(),
                        "drawable", context.getPackageName());

                fruitViewHolder.fruitImage.setImageResource(imageId);
                fruitViewHolder.fruitName.setText(fruit.getFruitName());
                fruitViewHolder.fruitCalories.setText("Calorie : " + fruit.getFruitCalorie() + "kcal");
                break;

            case ITEM_BANNER_AD:

                BannerAdViewHolder adViewHolder = (BannerAdViewHolder) viewHolder;
                AdView adView = (AdView) recyclerItems.get(position);

                ViewGroup adCardView = (ViewGroup) adViewHolder.itemView;

                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }

                if (adCardView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }

                adCardView.addView(adView);
        }
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%MainActivity.ITEM_PER_AD == 0) {
            return ITEM_BANNER_AD;
        } else {
            return ITEM_FRUIT;
        }
    }

    public static class FruitViewHolder extends RecyclerView.ViewHolder {

        private ImageView fruitImage;
        private TextView fruitName, fruitCalories;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
            fruitCalories = itemView.findViewById(R.id.fruit_calorie);
        }
    }

    public static class BannerAdViewHolder extends RecyclerView.ViewHolder {
        public BannerAdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
