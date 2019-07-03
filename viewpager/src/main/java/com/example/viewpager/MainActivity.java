package com.example.viewpager;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName() ;
    private ViewPager viewpager;
    private LinearLayout ll_point_group;
    private TextView tv_title;

    private ArrayList<ImageView> imageViews;
    private int prePosition = 0;
    private boolean isDragging = false;

    private final int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,

    };

    private final String[] imageDescriptions = {
            "aaaaaa",
            "bbbbbb",
            "cccccc",
            "dddddd",
            "eeeeee",

    };

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewpager.getCurrentItem() +1;
            viewpager.setCurrentItem(item);

            handler.sendEmptyMessageDelayed(0,4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager= findViewById(R.id.viewpager);
        ll_point_group= findViewById(R.id.ll_point_group);
        tv_title= findViewById(R.id.tv_title);


        imageViews = new ArrayList<>();
        for (int i =0; i < imageIds.length; i++){

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);

            imageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            if(i == 0){
                point.setEnabled(true);

            }else {
                point.setEnabled(false);
                params.leftMargin = 8;
            }
            point.setLayoutParams(params);

            ll_point_group.addView(point);

        }

        viewpager.setAdapter(new MyPagerAdapter());
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE /2 % imageViews.size() ;
        viewpager.setCurrentItem(item);

        tv_title.setText(imageDescriptions[prePosition]);

        handler.sendEmptyMessageAtTime(0,3000);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            int realPosition = i % imageViews.size();
            tv_title.setText(imageDescriptions[realPosition]);

            ll_point_group.getChildAt(prePosition).setEnabled(false);
            ll_point_group.getChildAt(realPosition).setEnabled(true);

            prePosition = realPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

            if(state == ViewPager.SCROLL_STATE_DRAGGING){
                isDragging =true;
                handler.removeCallbacksAndMessages(null);
            }else if(state == ViewPager.SCROLL_STATE_SETTLING){


            }else if(state == ViewPager.SCROLL_STATE_IDLE){
                isDragging =false;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }


        }
    }

    class MyPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {

            //return imageViews.size();
            return Integer.MAX_VALUE;
        }



        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            int realPosition = position % imageViews.size();

            final ImageView imageView = imageViews.get(realPosition);
            container.addView(imageView);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            Log.e(TAG, "onTouch == 手指按下");
                            handler.removeCallbacksAndMessages(null);
                        break;

                        case MotionEvent.ACTION_MOVE:
                            Log.e(TAG, "onTouch == 手指移动");
                            break;

                        case MotionEvent.ACTION_CANCEL:
                            Log.e(TAG, "onTouch == 手指取消");
                            break;

                        case MotionEvent.ACTION_UP:
                            Log.e(TAG, "onTouch == 手指松开");
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0, 4000);
                            break;

                    }

                    return false;
                }
            });

            imageView.setTag(position);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"点击事件============");
                    int position = (int) v.getTag() % imageViews.size();
                    String text = imageDescriptions[position];
                    Toast.makeText(MainActivity.this,"text = " + text, Toast.LENGTH_SHORT).show();
                }
            });


            Log.e(TAG,"instantiateItem == "+ position);

            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            Log.e(TAG,"destroyItem == "+ position + ",---- object == "+ object);
            container.removeView((View)object);

        }

    }
}
