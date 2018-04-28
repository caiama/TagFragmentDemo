package com.dxngxhl.tagfragmentdemo;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dxngxhl on 2017/9/18.
 * title-label
 */

public class TitleLabelView extends LinearLayout{
    TextView titleName;
    ImageView exitButton;
    public TitleLabelView(Context context) {
        super(context);
        initView(context);
    }

    public TitleLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        View.inflate(context,R.layout.title_label_view,this);
        findViewById(R.id.label_layout).setBackgroundResource(R.drawable.bg_label);
        titleName = (TextView) findViewById(R.id.label_name);
        exitButton = (ImageView) findViewById(R.id.label_button);
    }
    public void setText(String labelName){
        titleName.setText(labelName);
    }
    public String getText(){
        return titleName.getText().toString();
    }
    /***
     *
     * */
    @Override
    public void setTag(Object tag) {
        super.setTag(tag);
        exitButton.setTag(tag);
    }

    /**
     *
     * */
    public void setCloseListener(OnClickListener listener){
        exitButton.setOnClickListener(listener);
    }
}
