package com.xiaoan.obd.obdproject.module.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.utils.AppManager;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.imageload.GlideImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 15:51
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(PersonInfoPresenter.class)
public class PersonInfoActivity extends BeamDataActivity<PersonInfoPresenter, User> {
    private static final int REQUEST_CAMERA_CODE = 1001;
    private static final int REQUEST_PREVIEW_CODE = 1002;

    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_changeName)
    LinearLayout btnChangeName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.btn_changeIcon)
    LinearLayout btnChangeIcon;
    @BindView(R.id.btn_changePhone)
    LinearLayout btnChangePhone;
    @BindView(R.id.btn_quitAccount)
    Button btnQuitAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_personinfo);
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnChangeName.setOnClickListener(view -> getPresenter().changeName());
        btnChangeIcon.setOnClickListener(view -> changeIcon());
        btnChangePhone.setOnClickListener(view -> getPresenter().changePhone());
        btnQuitAccount.setOnClickListener(view -> getPresenter().quitAccount());
        if(getIntent().getStringExtra("type").equals(Constants.BusinessCard)){
            btnChangePhone.setVisibility(View.GONE);
            btnQuitAccount.setVisibility(View.GONE);
            btnChangeIcon.setEnabled(false);
            btnChangeName.setEnabled(false);
        }
    }

    private void changeIcon() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this);
        intent.setSelectModel(SelectModel.SINGLE);
        intent.setShowCarema(true); // 是否显示拍照， 默认false
        // intent.setImageConfig(config);
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setData(@Nullable User data) {
        super.setData(data);
        tvAccount.setText(data.getUserCode());
        tvName.setText(data.getUserName());
        tvPhone.setText(data.getPhone());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
//                    if(captureManager.getCurrentPhotoPath() != null) {
//                        captureManager.galleryAddPic();
//                        // 照片地址
//                        String imagePaht = captureManager.getCurrentPhotoPath();
//                        // ...
//                    }
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
            }
        }
    }
    private void refreshAdpater(ArrayList<String> paths) {
        // 处理返回照片地址
        new GlideImageLoader().loadGridItemView(tvIcon, paths.get(0), R.id.tv_icon, 50, 50);
    }
}
