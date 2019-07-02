package com.example.youkumenu;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView icon_menu;
    private ImageView icon_home;
    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;

    private boolean isShowLevel1 = true;
    private boolean isShowLevel2 = true;
    private boolean isShowLevel3 = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icon_home = findViewById(R.id.icon_home);
        icon_menu = findViewById(R.id.icon_menu);
        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        MyOnClickListener myOnClickListener = new MyOnClickListener();
        icon_home.setOnClickListener(myOnClickListener);
        icon_menu.setOnClickListener(myOnClickListener);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_MENU){

            if(isShowLevel1 == true){
                isShowLevel1 = false;
                Tools.hideView(level1);

                if(isShowLevel2 == true){
                    isShowLevel2 = false;
                    Tools.hideView(level2,200);

                    if(isShowLevel3 == true){
                        isShowLevel3 = false;
                        Tools.hideView(level3,400);

                    }

                }

            }else {

                isShowLevel1 = true;
                Tools.showView(level1);

                isShowLevel2 = true;
                Tools.showView(level2,200);
            }

            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    class MyOnClickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.icon_home:

                    if(isShowLevel2 == true){
                        isShowLevel2 = false;
                        Tools.hideView(level2);

                        if(isShowLevel3 == true){
                            isShowLevel3 = false;
                            Tools.hideView(level3,200);

                        }

                    }else {

                        isShowLevel2 = true;
                        Tools.showView(level2);
                    }

                    break;
                case R.id.icon_menu:

                    if(isShowLevel3 == true){
                        isShowLevel3 = false;
                        Tools.hideView(level3);

                    }else {

                        isShowLevel3 = true;
                        Tools.showView(level3);
                    }

                    break;
            }
        }
    }
}
