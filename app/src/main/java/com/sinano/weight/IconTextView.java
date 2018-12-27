package com.sinano.weight;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


/**
 * 字体图标TextView
 * Created by will.li on 2017/6/6.
 */
public class IconTextView extends android.support.v7.widget.AppCompatTextView {

    public IconTextView(Context context) {
        this(context, null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "icomoon.ttf");
        setTypeface(font);
    }

}
