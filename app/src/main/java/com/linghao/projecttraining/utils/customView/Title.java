package com.linghao.projecttraining.utils.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.linghao.projecttraining.R;

/**
 * Created by linghao on 2017/9/8.
 * QQ：782695971
 * 作用：
 */

public class Title extends LinearLayout {
    public Title(Context context) {
        super(context);
    }

    public Title(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
    }
}
