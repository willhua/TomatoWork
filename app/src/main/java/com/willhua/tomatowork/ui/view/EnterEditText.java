package com.willhua.tomatowork.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by willhua on 2016/12/14.
 */

public class EnterEditText extends EditText {

    interface EnterInputted {
        void onEnterInputted(String currentText);
    }

    EnterInputted mEnterInputted;

    public EnterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnterInputted(EnterInputted enterInputted) {
        mEnterInputted = enterInputted;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text.toString().contains("\n")) {
            String str = text.toString().replaceAll("\n", "");
            mEnterInputted.onEnterInputted(str);
            super.onTextChanged(str, start, lengthBefore, lengthAfter);
            return;
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
}
