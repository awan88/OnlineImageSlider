package com.example.parsaniahardik.imagesliderurl;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.parsaniahardik.imagesliderurl.Apiservice.Api;
import com.example.parsaniahardik.imagesliderurl.Apiservice.UrlBase;
import com.example.parsaniahardik.imagesliderurl.Data.DataBanner;
import com.example.parsaniahardik.imagesliderurl.Data.ResBanner;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    Api mApiService;

   /* private String[] urls = new String[] {"https://demonuts.com/Demonuts/SampleImages/W-03.JPG", "https://demonuts.com/Demonuts/SampleImages/W-08.JPG", "https://demonuts.com/Demonuts/SampleImages/W-10.JPG",
                                            "https://demonuts.com/Demonuts/SampleImages/W-13.JPG", "https://demonuts.com/Demonuts/SampleImages/W-17.JPG", "https://demonuts.com/Demonuts/SampleImages/W-21.JPG"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        indicator = findViewById(R.id.indicator);
        mApiService = UrlBase.getUserService();

        init();

    }

    private void init() {


        //mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,urls));





        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);

        mApiService.getBanners().enqueue(new Callback<ResBanner>() {
            @Override
            public void onResponse(Call<ResBanner> call, Response<ResBanner> response) {
                if (response.isSuccessful()) {

                        ArrayList<DataBanner> databenner = response.body().getDataBanners();

                        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,databenner));
                        indicator.setViewPager(mPager);

                        NUM_PAGES = databenner.size();
                        // Auto start of viewpager
                        final Handler handler = new Handler();
                        final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0;
                            }
                            mPager.setCurrentItem(currentPage++, true);
                        }
                    };
                    Timer swipeTimer = new Timer();
                    swipeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 1000, 1000);


                } else {

                }
            }

            @Override
            public void onFailure(Call<ResBanner> call, Throwable t) {

            }
        });





        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

}
