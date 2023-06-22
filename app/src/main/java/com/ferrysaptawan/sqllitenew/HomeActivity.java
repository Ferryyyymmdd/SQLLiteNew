package com.ferrysaptawan.sqllitenew;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ImageSliderAdapter sliderAdapter;
    private LinearLayout indicatorLayout;
    private Timer timer;
    private int currentPage = 0;
    private final long DELAY_MS = 3000, PERIOD_MS = 5000;

    Button mahasiswa, data_buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mahasiswa = findViewById(R.id.btnMahasiswa);
        data_buku = findViewById(R.id.btndatabuku);

        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MahasiswaActivity.class);
                startActivity(intent);
            }
        });

        data_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BukuActivity.class);
                startActivity(intent);
            }
        });


        viewPager = findViewById(R.id.imageSlider);
        indicatorLayout = findViewById(R.id.indicatorLayout);
        sliderAdapter = new ImageSliderAdapter(this, getImageList());
        viewPager.setAdapter(sliderAdapter);

        setupSliderIndicator();
        startSliderTimer();
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.image);
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image3);
        imageList.add(R.drawable.image4);
        imageList.add(R.drawable.image5);
        return imageList;
    }

    private void setupSliderIndicator() {
        final ImageView[] indicators = new ImageView[sliderAdapter.getCount()];

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageResource(R.drawable.nav2);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(5, 0, 5, 0);
            indicatorLayout.addView(indicators[i], layoutParams);
        }

        indicators[0].setImageResource(R.drawable.nav1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < indicators.length; i++) {
                    indicators[i].setImageResource(R.drawable.nav2);
                }
                indicators[position].setImageResource(R.drawable.nav1);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void startSliderTimer() {
        final int numPages = sliderAdapter.getCount();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentPage = (currentPage + 1) % numPages;
                        viewPager.setCurrentItem(currentPage, true);
                    }
                });
            }
        }, DELAY_MS, PERIOD_MS);
    }
}
