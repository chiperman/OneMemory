package com.example.onememory.app_item;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.onememory.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class AppItem extends LinearLayout {
    private Context mContext;
    private View mView;
    private ConstraintLayout cl_main;
    private ImageView iv_app_icon;
    private TextView tv_app_name;
    private TextView tv_cost;
    private TextView state;
    private int iconResID;
    private float cost;
    private String app_name;
    private int bg_tint;
    private int font_color;


    public int getBg_tint() {
        return bg_tint;
    }

    public void setBg_tint(int bg_tint) {
        this.bg_tint = bg_tint;
        if (cl_main != null) {
            cl_main.setBackgroundTintList(ColorStateList.valueOf(bg_tint));
        }
    }

    public void setGb_tint(String gb_tint) {
        if (cl_main != null) {
            cl_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(gb_tint)));
        }
    }

    public int getFont_color() {
        return this.font_color;
    }

    public void setFont_color(int font_color) {
        this.font_color = font_color;
        if (tv_cost != null && tv_app_name != null) {
            tv_cost.setTextColor(font_color);
            tv_app_name.setTextColor(font_color);
        }
    }

    public void setFont_color(String font_color) {
        if (tv_cost != null && tv_app_name != null) {
            tv_cost.setTextColor(Color.parseColor(font_color));
            tv_app_name.setTextColor(Color.parseColor(font_color));
        }
    }


    public void setCost(float cost) {
        this.cost = cost;
        if (tv_cost != null) {
            tv_cost.setText(String.valueOf(this.cost));
        }
    }

    public float getCost() {
        return cost;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
        if (tv_app_name != null) {
            tv_app_name.setText(this.app_name);
        }
    }

    public String getApp_name() {
        return this.app_name;
    }

    public void setIconResID(int iconResID) {
        this.iconResID = iconResID;
        if (iv_app_icon != null) {
            iv_app_icon.setImageResource(this.iconResID);
        }
    }

    public AppItem(Context context) {

        super(context);
    }

    public AppItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public AppItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.app_item, this, true);

        cl_main = mView.findViewById(R.id.cl_main);
        tv_app_name = mView.findViewById(R.id.tv_app_name);
        tv_cost = mView.findViewById(R.id.tv_cost);
        iv_app_icon = mView.findViewById(R.id.riv_app_icon);
        state = mView.findViewById(R.id.state);

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.AppItem);

        setApp_name(ta.getString(R.styleable.AppItem_app_name));
        setCost(ta.getFloat(R.styleable.AppItem_cost, 0));
        setIconResID(ta.getResourceId(R.styleable.AppItem_icon, 0));
        setFont_color(ta.getResourceId(R.styleable.AppItem_font_color, Color.parseColor("#000000")));
        setBg_tint(ta.getResourceId(R.styleable.AppItem_bg_tint, Color.parseColor("#ffffff")));
    }


    public TextView getState() {
        return state;
    }

}
