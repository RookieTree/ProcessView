package com.tree.nfcdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity
        extends AppCompatActivity
{

    private StepNumView mStepNumView;
    private ProcessView mProcessView;
    private String[] mStrArr={"填写信息","保存到手表","传递名片"};
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStepNumView = (StepNumView) findViewById(R.id.step_num);
        mProcessView = (ProcessView) findViewById(R.id.process_view);

        initView();

    }
    private int step=1;
    private void initView() {
        mNext = (Button) findViewById(R.id.next);
        //初始化进度
        mStepNumView.setCompleteNum(step);
        mProcessView.setCompleteNum(step);
        mProcessView.setListText(mStrArr);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step++;
                mStepNumView.setCompleteNum(step);
                mProcessView.setCompleteNum(step);
            }
        });
    }
}
