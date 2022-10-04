package com.timesheet.drinkinggame;

import androidx.appcompat.app.AppCompatActivity;

public class example extends AppCompatActivity {

    private int mImageResource;
    private String mText1;

    //    private String mText2;
    public example(int imageResource, String text1) {
        mImageResource = imageResource;
        mText1 = text1;
//        mText2 = text2;

    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }
//    public String getText2() {
//        return mText2;
//    }

}
