package com.willhua.tomatowork.ui.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.willhua.tomatowork.R;
import com.willhua.tomatowork.core.CommandRunner;
import com.willhua.tomatowork.core.ICommand;
import com.willhua.tomatowork.modle.data.CandyData;
import com.willhua.tomatowork.modle.data.TomatoData;
import com.willhua.tomatowork.modle.entity.Candy;
import com.willhua.tomatowork.ui.adapter.ChooseCandyAdapter;
import com.willhua.tomatowork.utils.LogUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willhua on 2016/12/11.
 */

public class TomatoFinishPopupWindow extends PopupWindow {

    private static final String TAG = "TomatoFinishPopupWindow";

    @BindView(R.id.lv_tomato_to_choose)
    ListView mListView;
    @BindView(R.id.btn_choose_ok)
    Button mBtnOk;
    @BindView(R.id.btn_choose_cancle)
    Button mBtnCancle;

    private CandyData mCandyData = new CandyData();
    private TomatoData mTomatoData = new TomatoData();

    public TomatoFinishPopupWindow(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.tomato_finish_popup, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        fillData();
        setListener();

        setAnimationStyle(R.style.TomatoFinishPopupAnim);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        setWidth(dm.widthPixels);
        setHeight(dm.heightPixels / 2);
    }

    private void fillData(){
        CommandRunner.getRunner().runCommand(new ICommand() {
            List<Candy> mCandies;

            @Override
            public void execute() {
                mCandies = mCandyData.getAllUnfinishedCandy();
            }

            @Override
            public void updateUI() {
                mListView.setAdapter(new ChooseCandyAdapter(mCandies));
                mListView.invalidate();
            }
        });
    }

    private void setListener(){
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Candy candy = (Candy) mListView.getSelectedItem();
                if(candy == null){
                    candy = (Candy) mListView.getItemAtPosition(0);
                }
                candyChoose(candy);
                dismiss();
            }
        });

        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //candyChoose((Candy) mListView.getItemAtPosition(0));
                Toast.makeText(mBtnCancle.getContext(), "a tomato is discard.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    private void candyChoose(final Candy candy){
        if (candy != null) {
            CommandRunner.getRunner().runCommand(new ICommand() {
                @Override
                public void execute() {
                    LogUtil.d(TAG, "candyChoose  execute " + candy);
                    candy.addTomato();
                    mCandyData.updateCandy(candy);
                    Calendar calendar = Calendar.getInstance();
                    mTomatoData.insertTomato(calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.HOUR_OF_DAY));
                }

                @Override
                public void updateUI() {

                }
            });
        }
    }


}
