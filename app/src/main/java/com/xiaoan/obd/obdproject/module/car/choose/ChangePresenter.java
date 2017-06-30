package com.xiaoan.obd.obdproject.module.car.choose;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.utils.Constants;

/**
 * author：Administrator on 2017/1/13 10:10
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ChangePresenter extends Presenter<ChangeActivity>{

    private String content;

    @Override
    protected void onCreateView(@NonNull ChangeActivity view) {
        super.onCreateView(view);
        content = getView().getIntent().getStringExtra(Constants.CHANGE_CONTENT);
        if(getView().getIntent().getStringExtra(Constants.CHANGE_TYPE).equals(Constants.OIL_PRICE)){
            init1();
        }else{
            init2();
        }
    }

    private void init2() {
        getView().edText.setText("油量：");
        if(TextUtils.isEmpty(content)){
            getView().edName.setHint("请输入油量");
        } else{
            getView().edName.setText(content);
        }
    }

    private void init1() {
        getView().edText.setText("价格：");
        if(TextUtils.isEmpty(content)){
            getView().edName.setHint("请输入价格");
        } else{
            getView().edName.setText(content);
        }
    }

    public void enterChange() {
        if(TextUtils.isEmpty(getView().edName.getText().toString())){
            JUtils.Toast("内容不能为空！");
            return;
        }
        Intent intent = new Intent(getView(),ChangeActivity.class);
        intent.putExtra(Constants.CHANGE_CONTENT,getView().edName.getText().toString());
        getView().setResult(1002,intent);
        getView().finish();
    }
}
