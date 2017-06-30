package com.xiaoan.obd.obdproject.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoan.obd.obdproject.R;

/**
 * author：Administrator on 2017/1/7 10:26
 * company: xxxx
 * email：1032324589@qq.com
 */

public class TireView extends LinearLayout{
    private int orientation;
    private Context mContext;
    private int mDefaultColor;
    private float mDefaultSize;
    private float mPressUSize ;
    private int mTempColor;
    private float mTempSize ;
    private float mTempUSize ;
    private int mNoteColor;
    private float mNoteSize;
    private float mPressSize;
    private int mPressColor;
    private String press = "0.0";
    private String temp = "25";
    private TextView tvPress;
    private TextView tvPressUnit;
    private TextView tvTemp;
    private TextView tvTempUnit;
    private LinearLayout lptemp;
    private TextView tvNote;

    public TireView(Context context) {
        super(context);
        this.mContext = context;
    }

    public TireView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.setOrientation(VERTICAL);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Tire,0,0);
        try {
            mDefaultColor = a.getColor(R.styleable.Tire_default_color,context.getResources().getColor(R.color.white));
            mDefaultSize = a.getDimension(R.styleable.Tire_default_size, 16);
            mPressColor = a.getColor(R.styleable.Tire_press_color,context.getResources().getColor(R.color.colorPrimaryDark));
            mPressSize = a.getDimension(R.styleable.Tire_press_size, 60);
            mPressUSize = a.getDimension(R.styleable.Tire_press_unit_size,13);
            mTempColor = a.getColor(R.styleable.Tire_temp_color,context.getResources().getColor(R.color.colorPrimaryDark));
            mTempSize = a.getDimension(R.styleable.Tire_temp_size,40);
            mTempUSize = a.getDimension(R.styleable.Tire_temp_unit_size,13);
            mNoteColor = a.getColor(R.styleable.Tire_note_color,context.getResources().getColor(R.color.black));
            mNoteSize = a.getDimension(R.styleable.Tire_note_size,20);
            orientation = a.getInt(R.styleable.Tire_tire_orientation,VERTICAL);
            if(orientation==VERTICAL){
                TextView staticTemp = new TextView(mContext);
                staticTemp.setText("温度");
                staticTemp.setPadding(0,0,0,5);
                staticTemp.setTextSize(TypedValue.COMPLEX_UNIT_SP,mDefaultSize);
                staticTemp.setTextColor(mDefaultColor);
                TextView staticPress = new TextView(mContext);
                staticPress.setText("胎压");
                staticPress.setPadding(0,0,0,5);
                staticPress.setTextSize(TypedValue.COMPLEX_UNIT_SP,mDefaultSize);
                staticPress.setTextColor(mDefaultColor);
                addView(staticPress);
                addView(addPressView());
                addView(staticTemp);
                addView(addTempView());
                addView(addNoteView());
            }else if(orientation==HORIZONTAL){
                addView(addHorizontal());
                addView(addNoteView());
            }
        }finally {
            a.recycle();
        }
    }

    public TireView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension(200, widthMeasureSpec);
        int height = measureDimension(200, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public int measureDimension(int defaultSize, int measureSpec){
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = defaultSize;   //UNSPECIFIED
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private  View addPressHorizontal(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,10,0,10);
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout lpress = new LinearLayout(mContext);
        lpress.setLayoutParams(lp);
        lpress.setOrientation(LinearLayout.VERTICAL);
        TextView staticTemp = new TextView(mContext);
        staticTemp.setText("温度");
        staticTemp.setPadding(0,0,0,5);
        staticTemp.setTextSize(TypedValue.COMPLEX_UNIT_SP,mDefaultSize);
        staticTemp.setTextColor(mDefaultColor);
        lpress.addView(staticTemp);
        lpress.addView(addPressView());
        return lpress;
    }
    private  View addTempHorizontal(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,10,0,10);
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout lpress = new LinearLayout(mContext);
        lpress.setLayoutParams(lp);
        lpress.setOrientation(LinearLayout.VERTICAL);
        TextView staticPress = new TextView(mContext);
        staticPress.setText("胎压");
        staticPress.setPadding(0,0,0,5);
        staticPress.setTextSize(TypedValue.COMPLEX_UNIT_SP,mDefaultSize);
        staticPress.setTextColor(mDefaultColor);
        lpress.addView(staticPress);
        lpress.addView(addTempView());
        return lpress;
    }
    private  View addHorizontal(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,10,0,10);
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout lpress = new LinearLayout(mContext);
        lpress.setLayoutParams(lp);
        lpress.setOrientation(LinearLayout.HORIZONTAL);
        lpress.addView(addPressHorizontal());
        lpress.addView(addTempHorizontal());
        return lpress;
    }
    private View addPressView(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,10,0,10);
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout lpress = new LinearLayout(mContext);
        lpress.setLayoutParams(lp);
        lpress.setOrientation(LinearLayout.HORIZONTAL);
        tvPress = new TextView(mContext);
        tvPress.setText("0.0");
        tvPress.setPadding(0,0,0,5);
        tvPress.setTextSize(TypedValue.COMPLEX_UNIT_SP,mPressSize);
        tvPress.setTextColor(mPressColor);
        tvPressUnit = new TextView(mContext);
        tvPressUnit.setText("bar");
        tvPressUnit.setPadding(0,0,0,5);
        tvPressUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP,mPressUSize);
        tvPressUnit.setTextColor(mPressColor);
        lpress.addView(tvPress,vlp);
        lpress.addView(tvPressUnit,vlp2);
        return lpress;
    }
    private View addTempView(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,10,0,10);
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout lptemp = new LinearLayout(mContext);
        lptemp.setLayoutParams(lp);
        lptemp.setOrientation(LinearLayout.HORIZONTAL);
        tvTemp = new TextView(mContext);
        tvTemp.setText("0");
        tvTemp.setPadding(0,0,0,5);
        tvTemp.setTextSize(TypedValue.COMPLEX_UNIT_SP,mTempSize);
        tvTemp.setTextColor(mTempColor);
        tvTempUnit = new TextView(mContext);
        tvTempUnit.setText("℃");
        tvTempUnit.setPadding(0,0,0,5);
        tvTempUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP,mTempUSize);
        tvTempUnit.setTextColor(mTempColor);
        lptemp.addView(tvTemp,vlp);
        lptemp.addView(tvTempUnit,vlp2);
        return lptemp;
    }
    private View addNoteView(){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.setMargins(0,10,0,10);
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams vlp2 = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lptemp = new LinearLayout(mContext);
        lptemp.setLayoutParams(lp);
//        lptemp.setBackgroundColor(mTempColor);
        lptemp.setOrientation(LinearLayout.HORIZONTAL);
        tvNote = new TextView(mContext);
        tvNote.setLayoutParams(vlp2);
        tvNote.setText("胎压过高，胎压过低，温度过高");
        tvNote.setTextSize(TypedValue.COMPLEX_UNIT_SP,mDefaultSize);
        tvNote.setTextColor(mTempColor);
        lptemp.addView(tvNote,vlp);
        return lptemp;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        float baseY = 60;
//        //
//        Paint paint = new Paint();
//        paint.setColor(mDefaultColor);
//        paint.setTextSize(mDefaultSize);
//        //
//        Paint paint1 = new Paint();
//        paint1.setColor(mPressColor);
//        paint1.setTextSize(mPressSize);
//
//        Paint paint2 = new Paint();
//        paint2.setColor(mPressColor);
//        paint2.setTextSize(mPressUSize);
//
//
//        Paint paint4 = new Paint();
//        paint4.setColor(mTempColor);
//        paint4.setTextSize(mTempSize);
//
//
//        Paint paint5 = new Paint();
//        paint5.setColor(mTempColor);
//        paint5.setTextSize(mTempUSize);
//
//
//        float pressY = baseY+getTextHeight(paint)+30;
//        float tempY = pressY+getTextHeight(paint1)-30;
//        float temp1Y = tempY+getTextHeight(paint2)+30;
//
//        canvas.drawText("胎压",0,baseY,paint);
//        canvas.drawText(press,0,pressY,paint1);
//        canvas.drawText("bar",paint1.measureText(press),pressY-getTextHeight(paint)/2,paint2);
//        canvas.drawText("温度",0,tempY,paint);
//        canvas.drawText(temp,0,temp1Y,paint4);
//        canvas.drawText("℃",paint4.measureText(press),temp1Y-getTextHeight(paint1)/2,paint5);
    }
    public void setPressText(String st,int color){
        tvPress.setText(st);
        tvPress.setTextColor(color);
//        this.invalidate();
    }
    public void showPressHightView(String st){
        tvPress.setText(st);
        tvPress.setTextColor(getResources().getColor(R.color.red));
        tvPressUnit.setTextColor(getResources().getColor(R.color.red));
    }
    public void showPressLowView(String st){
        tvPress.setText(st);
        tvPress.setTextColor(getResources().getColor(R.color.yellow));
        tvPressUnit.setTextColor(getResources().getColor(R.color.yellow));
    }
    public void setPressUnitText(String st,int color){
        tvPressUnit.setText(st);
        tvPressUnit.setTextColor(color);
//        this.invalidate();
    }
    public void setTempText(String st,int color){
        tvTemp.setText(st);
        tvTemp.setTextColor(color);
//        this.invalidate();
    }
    public void setTempUnitText(String st,int color){
        tvTempUnit.setText(st);
        tvTempUnit.setTextColor(color);
//        this.invalidate();
    }
    public void setNoteText(String st){
        if(!TextUtils.isEmpty(st)){
            tvNote.setVisibility(VISIBLE);
            tvNote.setText(st);
            tvNote.setTextColor(getResources().getColor(R.color.red));
        }else {
            tvNote.setVisibility(GONE);
        }
    }
    public void setBackColor(int backColor){
        this.setBackgroundColor(backColor);
//        this.invalidate();
    }
    private float getTextHeight(Paint paint){
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        return fontTotalHeight;
    }

}
