package com.rasp.lawyersapp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import com.rasp.lawyersapp.R;


/**
 * Created by root on 27/6/16.
 */
public class EditTextDrawableCustomize extends AppCompatEditText {
    private Context context;

    public EditTextDrawableCustomize(Context context) {
        super(context);
        this.context = context;
        init();

    }

    public EditTextDrawableCustomize(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public EditTextDrawableCustomize(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init() {
        Drawable imgCloseButton = ContextCompat.getDrawable(context, R.drawable.ic_search);
        setInputType(InputType.TYPE_CLASS_TEXT);
        setImeOptions(EditorInfo.IME_ACTION_DONE);
        setSingleLine(true);
        setCompoundDrawablePadding(20);
        imgCloseButton.setBounds(0, 0, 30, 30);
        setPadding(20, 0, 0, 0);
        setCompoundDrawables(imgCloseButton, this.getCompoundDrawables()[1], this.getCompoundDrawables()[0], this.getCompoundDrawables()[3]);
        //setBackground(ContextCompat.getDrawable(context, R.drawable.bg_stroke_rounded_black));

        // setting font
        if (!isInEditMode()) {
            Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "HelveticaNeue.ttf");
            setTypeface(myTypeface);
        }
    }

}
