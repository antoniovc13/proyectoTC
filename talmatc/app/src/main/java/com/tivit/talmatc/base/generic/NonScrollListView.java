package com.tivit.talmatc.base.generic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NonScrollListView extends ListView {

    private Integer maxHeight;
    private MaxHeightListener listener;

    public NonScrollListView(Context context) {
        super(context);
    }

    public NonScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setListener(MaxHeightListener listener) {
        this.listener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);

        if (maxHeight != null && getMeasuredHeight() > maxHeight) {
            setMeasuredDimension(getMeasuredWidth(), maxHeight);
            if (listener != null) {
                listener.onChangeHeight(true);
            }
        }else{
            if (listener != null) {
                listener.onChangeHeight(false);
            }
        }
    }

    public interface MaxHeightListener {
        public void onChangeHeight(boolean maxHeightPassed);
    }
}
