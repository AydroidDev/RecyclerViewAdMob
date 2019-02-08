package br.com.alessanderleite.recyclerviewadmod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ITEM_PER_AD = 4;
    private static final String BANNER_AD_ID = "ca-app-pub-3940256099942544/6300978111";
    private List<Object> recyclerItems = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));

        getFruits();
        getBannerAds();
        loadBannerAds();
    }

    private void getFruits() {
        List<String> fruitNames = Arrays.asList(getResources().getStringArray(R.array.fruits_names));
        List<String> fruitsCalories = Arrays.asList(getResources().getStringArray(R.array.fruits_calories));

        int count = 0;

        for (String fruitName : fruitNames) {

            recyclerItems.add(new Fruit(fruitName, fruitsCalories.get(count)));
            count++;
        }
    }

    private void getBannerAds() {

        for (int i = 0; i < recyclerItems.size(); i += ITEM_PER_AD) {

            final AdView adView = new AdView(MainActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(BANNER_AD_ID);
            recyclerItems.add(adView);
        }
    }

    private void loadBannerAds() {

        for (int i = 0; i < recyclerItems.size(); i++) {

            Object item = recyclerItems.get(i);
            if (item instanceof AdView) {
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }
}
