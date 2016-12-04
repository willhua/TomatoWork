package com.willhua.tomatowork.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.utils.LogUtil;
import com.willhua.tomatowork.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willhua on 2016/12/1.
 */

public class AddCandyPopupWindow extends PopupWindow {
    private static final String TAG = "AddCandyPopupWindow";
    private Context mContext;

    @BindView(R.id.et_add_candy)
    CandyEditText mEditText;

    /**
     * @param context
     * @param editText the edittext fo tomatoListFragment. It's position will be get to keep look same
     */
    public AddCandyPopupWindow(Context context, EditText editText) {
        super();
        mContext = context.getApplicationContext();
        setFocusable(true);
        setOutsideTouchable(true);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        setWidth(dm.widthPixels);
        setHeight(dm.heightPixels);
        setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.pw_bg_100)));
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_candy, null);
        int[] pos = new int[2];
        editText.getLocationOnScreen(pos);

        view.setPadding(pos[0], pos[1] - Utils.getStatusBarHeight(mContext), 0, 0);
        LogUtil.d(TAG, view.getClass() + "  pop");
        LogUtil.d(TAG, "X Y" + pos[0] + "*" + pos[1]);
        setContentView(view);
        ButterKnife.bind(this, view);
        mEditText.setEnterInputted(new EnterInputted() {
            @Override
            public void onEnterInputted(String currentText) {
                AddCandyPopupWindow.this.dismiss();
            }
        });
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    public String getCandyTitle() {
        return mEditText.getText().toString();
    }


    interface EnterInputted {
        void onEnterInputted(String currentText);
    }

    public static class CandyEditText extends EditText {

        EnterInputted mEnterInputted;

        public CandyEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void setEnterInputted(EnterInputted enterInputted) {
            mEnterInputted = enterInputted;
        }

        @Override
        protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            if (text.toString().contains("\n")) {
                mEnterInputted.onEnterInputted(text.toString());
                return;
            }
            super.onTextChanged(text, start, lengthBefore, lengthAfter);
        }
    }
}
