package com.xiaoan.obd.obdproject.module.trouble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.FaultCodeBean;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.untils.Constants;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/17 15:08
 * company: xxxx
 * email：1032324589@qq.com
 */

public class TroubleCodeSearchPresenter extends BeamListActivityPresenter<TroubleCodeSearchActivity,FaultCodeBean>{

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
        }
    };

    @Override
    protected void onCreate(@NonNull TroubleCodeSearchActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        //initData();
        getView().registerReceiver(broadcastReceiver,new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }

    @Override
    protected void onCreateView(@NonNull TroubleCodeSearchActivity view) {
        super.onCreateView(view);
        initSearchView();
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getView(),TroubleCodeInfoActivity.class);
                intent.putExtra(Constants.ID,getAdapter().getItem(position).getId());
                getView().startActivity(intent);
            }
        });
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<List<FaultCodeBean>>() {
            @Override
            public void call(Subscriber<? super List<FaultCodeBean>> subscriber) {
                subscriber.onNext(APP.getInstances().dbHelper.searchTroubleCode("P1000"));
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }
    private void initSearchView(){
        SearchView searchCode = (SearchView) getView().findViewById(R.id.search_code);
        /**
         * 默认情况下, search widget是"iconified“的，只是用一个图标 来表示它(一个放大镜),
         * 当用户按下它的时候才显示search box . 你可以调用setIconifiedByDefault(false)让search
         * box默认都被显示。 你也可以调用setIconified()让它以iconified“的形式显示。
         */
        searchCode.setIconifiedByDefault(true);
        /**
         * 默认情况下是没提交搜索的按钮，所以用户必须在键盘上按下"enter"键来提交搜索.你可以同过setSubmitButtonEnabled(
         * true)来添加一个提交按钮（"submit" button)
         * 设置true后，右边会出现一个箭头按钮。如果用户没有输入，就不会触发提交（submit）事件
         */
        searchCode.setSubmitButtonEnabled(false);
        /**
         * 初始是否已经是展开的状态
         * 写上此句后searchView初始展开的，也就是是可以点击输入的状态，如果不写，那么就需要点击下放大镜，才能展开出现输入框
         */
        searchCode.onActionViewExpanded();
        // 设置search view的背景色
        searchCode.setBackgroundColor(0x22ff00ff);
        /**
         * 默认情况下, search widget是"iconified“的，只是用一个图标 来表示它(一个放大镜),
         * 当用户按下它的时候才显示search box . 你可以调用setIconifiedByDefault(false)让search
         * box默认都被显示。 你也可以调用setIconified()让它以iconified“的形式显示。
         */
        searchCode.setIconifiedByDefault(true);
        searchCode.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            private String TAG = getClass().getSimpleName();

            /*
             * 在输入时触发的方法，当字符真正显示到searchView中才触发，像是拼音，在舒服法组词的时候不会触发
             *
             * @param queryText
             *
             * @return false if the SearchView should perform the default action
             * of showing any suggestions if available, true if the action was
             * handled by the listener.
             */
            @Override
            public boolean onQueryTextChange(String queryText) {
                Observable.create(new Observable.OnSubscribe<List<FaultCodeBean>>() {
                    @Override
                    public void call(Subscriber<? super List<FaultCodeBean>> subscriber) {
                        subscriber.onNext(APP.getInstances().dbHelper.searchTroubleCode(queryText));
                        subscriber.onCompleted();
                    }
                }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
                return true;
            }

            /*
             * 输入完成后，提交时触发的方法，一般情况是点击输入法中的搜索按钮才会触发。表示现在正式提交了
             *
             * @param queryText
             *
             * @return true to indicate that it has handled the submit request.
             * Otherwise return false to let the SearchView handle the
             * submission by launching any associated intent.
             */
            @Override
            public boolean onQueryTextSubmit(String queryText) {
                Log.d(TAG, "onQueryTextSubmit = " + queryText);
                if (searchCode != null) {
                    // 得到输入管理对象
                    InputMethodManager imm = (InputMethodManager) getView().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
                        imm.hideSoftInputFromWindow(searchCode.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
                    }
                    searchCode.clearFocus(); // 不获取焦点
                }
                return true;
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getView().unregisterReceiver(broadcastReceiver);
    }
}
