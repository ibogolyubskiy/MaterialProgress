package com.materialprogress;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MaterialProgressBar extends FrameLayout {

    private static final int CIRCLE_DIAMETER = 40;
    private static final int MAX_ALPHA = 255;
    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;

    private int mCircleWidth;
    private int mCircleHeight;
    private CircleImageView mCircleView;
    private MaterialProgressDrawable mProgress;

    public MaterialProgressBar(Context context) {
        super(context);
        init();
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mCircleWidth = (int) (CIRCLE_DIAMETER * metrics.density);
        mCircleHeight = (int) (CIRCLE_DIAMETER * metrics.density);

        mCircleView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT, CIRCLE_DIAMETER / 2);
        mProgress = new MaterialProgressDrawable(getContext(), this);
        mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        mProgress.setAlpha(MAX_ALPHA);
        mCircleView.setImageDrawable(mProgress);
        addView(mCircleView);

        FrameLayout.MarginLayoutParams params = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        int margin = (int)(CircleImageView.SHADOW_RADIUS * metrics.density);
        params.setMargins(margin, margin, margin, margin);
        mCircleView.setLayoutParams(params);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.VISIBLE)
            mProgress.start();
        else
            mProgress.stop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mCircleView.measure(MeasureSpec.makeMeasureSpec(mCircleWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mCircleHeight, MeasureSpec.EXACTLY));
    }
}
