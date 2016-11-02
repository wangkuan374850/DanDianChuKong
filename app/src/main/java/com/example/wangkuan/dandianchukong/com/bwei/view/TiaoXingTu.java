package com.example.wangkuan.dandianchukong.com.bwei.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.wangkuan.dandianchukong.R;

/**
 * Created by wangkuan on 2016/11/1.
 */
public class TiaoXingTu extends View {

    private Paint mPaint;
    private int mCount;
    private int mWidth;
    private int mRectHeight;
    private int mRectWidth;
    private LinearGradient mLinearGradient;
    private double mRandom;
    private float mcurrentHeight;
    public static final int OFFSET = 5;

    public TiaoXingTu(Context context) {
        this(context, null);
    }

    public TiaoXingTu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TiaoXingTu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //写一个方法来初始化
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //new画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);//设置画笔为绿色
        mPaint.setStyle(Paint.Style.FILL);//设置风格
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.volumneView);
        if (ta != null) {
            //得到自定义属性
            mCount = ta.getInt(R.styleable.volumneView_count, 0);

            String string = ta.getString(R.styleable.volumneView_counta);
            Log.i("aaa",string+"------------------------------------------");
            ta.recycle();//回收利用
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //得到测出的宽
        mWidth = getMeasuredWidth();
        //得到测出的高
        mRectHeight = getMeasuredHeight();
        //设置宽
        mRectWidth = (int) (mWidth * 0.8 / mCount);
        //线性渲染器
        mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight,
                Color.GREEN, Color.YELLOW, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);//设置
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (int i = 0; i < mCount; i++) {
            mRandom = Math.random();//随机数的类
            mcurrentHeight = (float) (mRectHeight * mRandom);//生成随机数的高
            float width = (float) (mWidth * 0.4 / 2 + OFFSET);//宽

            canvas.drawRect(width + i * mRectWidth, mcurrentHeight, width
                    + (i + 1) * mRectWidth, mRectHeight, mPaint);
        }
        postInvalidateDelayed(300);
    }
}
