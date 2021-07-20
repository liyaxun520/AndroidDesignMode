package com.example.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by apple on 16/11/9.
 */

public class LoveLayout extends FrameLayout {

    ArrayList<Drawable> drawables = new ArrayList<>();
    ArrayList<Interpolator> interpolators = new ArrayList<>();
    LayoutParams params;
    Random random = new Random();

    public LoveLayout(Context context) {
        this(context, null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        initDrawable(new int[]{R.drawable.pic1, R.drawable.pic2, R.drawable.pic3});
        initInterpolator();

    }

    private void initInterpolator() {
        interpolators.add(new AccelerateDecelerateInterpolator());
        interpolators.add(new AccelerateInterpolator());
        interpolators.add(new AnticipateInterpolator());
        interpolators.add(new DecelerateInterpolator());
        interpolators.add(new FastOutLinearInInterpolator());
    }

    private void initDrawable(int[] ids) {
        if (ids == null) return;
        for (int id : ids) {
            drawables.add(getResources().getDrawable(id));
        }
    }

    public void resetDrawables(int[] ids) {
        drawables.clear();
        initDrawable(ids);
    }

    public void addInterpolator(Interpolator interpolator) {
        interpolators.add(interpolator);
    }


    public void addDrawables(int[] ids) {
        for (int id : ids) {
            addDrawable(id);
        }
    }

    public void addDrawable(int id) {
        drawables.add(getResources().getDrawable(id));
    }


    public void addLove() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables.get(random.nextInt(drawables.size())));
        imageView.setLayoutParams(params);
        addView(imageView);
        getAnimtorSet(imageView).start();
    }

    int STARTPOINT = 1, ENDPONIT = 2;

    private AnimatorSet getAnimtorSet(final ImageView imageView) {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1);
        objectAnimator1.setInterpolator(interpolators.get(random.nextInt(drawables.size())));
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1);
        objectAnimator2.setInterpolator(interpolators.get(random.nextInt(drawables.size())));
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1);
        objectAnimator3.setInterpolator(interpolators.get(random.nextInt(drawables.size())));
        AnimatorSet set = new AnimatorSet();
        set.setDuration(600);
        set.playTogether(objectAnimator1, objectAnimator2, objectAnimator3);
        set.setTarget(imageView);

        LoveEvaluator evaluator = new LoveEvaluator(getP1(imageView), getP2(imageView));
        ValueAnimator resultAnimator = ValueAnimator.ofObject(evaluator, getPoint(STARTPOINT, imageView), getPoint(ENDPONIT, imageView));
        resultAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);
                imageView.setAlpha(1 - animation.getAnimatedFraction());
            }
        });
        resultAnimator.setInterpolator(interpolators.get(random.nextInt(drawables.size())));
        AnimatorSet resuleAnimatorSet = new AnimatorSet();
        resuleAnimatorSet.playSequentially(set, resultAnimator);
        resuleAnimatorSet.setDuration(3000);
        resuleAnimatorSet.setTarget(imageView);
        resuleAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(imageView);
            }
        });
        return resuleAnimatorSet;
    }

    private PointF getP2(ImageView imageView) {
        return new PointF(random.nextInt(getWidth()), random.nextInt(getHeight() / 2));
    }

    public PointF getP1(ImageView imageView) {
        return new PointF(random.nextInt(getWidth()), random.nextInt(getHeight() / 2) + getHeight() / 2 - imageView.getDrawable().getIntrinsicHeight());
    }

    private PointF getPoint(int type, ImageView imageView) {
        if (type == STARTPOINT) {
            return new PointF(getWidth() / 2 - imageView.getDrawable().getIntrinsicWidth() / 2, getHeight() - imageView.getDrawable().getIntrinsicHeight());
        }
        return new PointF(random.nextInt(getWidth()), 0);
    }


    class LoveEvaluator implements TypeEvaluator<PointF> {
        LoveEvaluator(PointF p1, PointF p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        private PointF p1, p2;

        @Override
        public PointF evaluate(float t, PointF p0, PointF p3) {
            Log.e("zjw", p0 + "   " + p1 + "      " + p2 + "     " + p3);

            //时间因子t: 0~1
            PointF point = new PointF();
            //实现贝塞尔公式:
            point.x = p0.x * (1 - t) * (1 - t) * (1 - t) + 3 * p1.x * t * (1 - t) * (1 - t) + 3 * p2.x * (1 - t) * t * t + p3.x * t * t * t;//实时计算最新的点X坐标
            point.y = p0.y * (1 - t) * (1 - t) * (1 - t) + 3 * p1.y * t * (1 - t) * (1 - t) + 3 * p2.y * (1 - t) * t * t + p3.y * t * t * t;//实时计算最新的点Y坐标
            return point;//实时返回最新计算出的点对象
        }
    }


}