package com.example.scoreregisterapp.presentation.custom.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextView_HKNova_Medium extends TextView {

    public TextView_HKNova_Medium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextView_HKNova_Medium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextView_HKNova_Medium(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/HKNova-Medium.ttf");
            setTypeface(tf);
        }
    }

}