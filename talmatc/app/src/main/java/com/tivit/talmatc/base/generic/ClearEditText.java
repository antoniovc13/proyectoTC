package com.tivit.talmatc.base.generic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

    public interface Listener {
        void didClearText();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private Drawable btnClear;
    private Listener listener;

    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.l = l;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.f = f;
    }

    private OnTouchListener l;
    private OnFocusChangeListener f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null && btnClear!=null) {
            boolean tappedClear = event.getX() > (getWidth() - getPaddingRight() - btnClear.getIntrinsicWidth());
            if (tappedClear) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (listener != null) {
                        listener.didClearText();
                    }else {
                        setText("");
                    }
                }
                return true;
            }
        }

        if (l != null) {
            return l.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        setClearIconVisible(getText().length()>0);

        if (f != null) {
            f.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public void onTextChanged(EditText view, String text) {
        setClearIconVisible((text.length() != 0));
    }

    private void init() {
        btnClear = getResources().getDrawable(android.R.drawable.presence_offline);
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
    }

    protected void setClearIconVisible(boolean isvisible) {
        btnClear = isvisible ? getResources().getDrawable(android.R.drawable.presence_offline):null;
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], btnClear, getCompoundDrawables()[3]);
    }
}