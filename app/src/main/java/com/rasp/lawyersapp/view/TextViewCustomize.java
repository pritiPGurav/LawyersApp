package com.rasp.lawyersapp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by root on 29/6/16.
 */
public class TextViewCustomize extends AppCompatTextView {
    Context context;

    public TextViewCustomize(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TextViewCustomize(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public TextViewCustomize(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "HelveticaNeue.ttf");
        setTypeface(myTypeface);
    }

}
