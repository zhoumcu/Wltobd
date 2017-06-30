package com.xiaoan.obd.obdproject.module.car.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.jude.beam.expansion.BeamBasePresenter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;
import com.xiaoan.obd.obdproject.widget.pinyin.CharacterParser;
import com.xiaoan.obd.obdproject.widget.pinyin.PinyinComparator;
import com.xiaoan.obd.obdproject.widget.pinyin.SideBar;
import com.xiaoan.obd.obdproject.widget.pinyin.SortAdapter;
import com.xiaoan.obd.obdproject.widget.pinyin.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author：Administrator on 2016/12/13 17:26
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarSelectBrandListActivityPresenter extends BeamBasePresenter<CarSelectBrandListActivity> {
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList = new ArrayList<>();
    private PinyinComparator pinyinComparator;
    private SortAdapter adapter;

    @Override
    protected void onCreate(@NonNull CarSelectBrandListActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
    }

    @Override
    protected void onCreateView(@NonNull CarSelectBrandListActivity view) {
        super.onCreateView(view);
        initView();
        initData();
        getView().recycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //保存品牌logo方便之后使用
                SharedPreferences.getInstance().putString("logo",((SortModel) adapter.getItem(position)).getEname());
                Intent intent = new Intent(getView(),CarSelectSeriesListActivity.class);
                intent.putExtra("id",((SortModel) adapter.getItem(position)).getId());
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        List<CarBean> carBrands = APP.getInstances().dbHelper.getCarList();
        SourceDateList = filledData(carBrands);
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(getView(), SourceDateList);
        getView().recycler.setAdapter(adapter);
    }

    public void initView() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        getView().sidrbar.setTextView(getView().dialog);
        //设置右侧触摸监听
        getView().sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    getView().recycler.setSelection(position);
                }
            }
        });

        //根据输入框输入值的改变来过滤搜索
        getView().filterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledData(String [] date){
        List<SortModel> mSortList = new ArrayList<SortModel>();
        int lenght = date.length;
        for(int i=0; i<lenght; i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }
    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<CarBean> date){
        List<SortModel> mSortList = new ArrayList<SortModel>();
        int size = date.size();
        for(int i=0; i<size; i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getBrandCname());
            sortModel.setEname(date.get(i).getBrandEname());
            sortModel.setId(date.get(i).getId());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getBrandCname());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }
    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
}
