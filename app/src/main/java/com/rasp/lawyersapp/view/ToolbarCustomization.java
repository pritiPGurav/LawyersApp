package com.rasp.lawyersapp.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.rasp.lawyersapp.R;

/**
 * Created by root on 12/7/16.
 */
public class ToolbarCustomization extends Toolbar {
    private TextView tvTitle;
    public ToolbarCustomization(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ToolbarCustomization(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToolbarCustomization(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(@NonNull Context context) {

        View rootView = inflate(context, R.layout.toolbar, this);
        tvTitle = (TextView) rootView.findViewById(R.id.toolbarTitle);
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "HelveticaNeue.ttf");
        tvTitle.setTypeface(myTypeface);

       // tvTitle = (TextViewCustomize) rootView.findViewById(R.id.toolbarTitle);
       /* TextView textView = new TextView(context);
        Toolbar toolbar = new Toolbar(context);
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "helvathica.TTF");
        textView.setTypeface(myTypeface, Typeface.BOLD);
        textView.setAllCaps(true);
        for (int i = 0; i < toolbar.getChildCount(); ++i) {
            View child = toolbar.getChildAt(i);
            if (child instanceof TextView) {
                textView = (TextView) child;
                break;
            }
        }*/
    }

    public void setup(String title){
        tvTitle.setText(title);
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(24);
    }
}
