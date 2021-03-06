package com.example.scoreregisterapp.presentation.custom.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextView_ProximaNova_Regular extends TextView {

    public TextView_ProximaNova_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextView_ProximaNova_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextView_ProximaNova_Regular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanovaregular.ttf");
            setTypeface(tf);
        }
    }

}