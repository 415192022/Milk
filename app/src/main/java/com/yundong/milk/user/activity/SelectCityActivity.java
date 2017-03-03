package com.yundong.milk.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yundong.milk.R;
import com.yundong.milk.api.MainApi;
import com.yundong.milk.base.BaseActivity;
import com.yundong.milk.net.VolleyUtil;
import com.yundong.milk.user.adapter.HotCityAdapter;
import com.yundong.milk.user.adapter.SelectCityAdapter;
import com.yundong.milk.user.adapter.SortGroupMemberAdapter;
import com.yundong.milk.user.model.GroupMemberBean;
import com.yundong.milk.user.model.HotCityModel;
import com.yundong.milk.user.model.SelectProvinceModel;
import com.yundong.milk.util.CharacterParser;
import com.yundong.milk.util.JsonUtil;
import com.yundong.milk.util.PinyinComparator;
import com.yundong.milk.util.ToastUtil;
import com.yundong.milk.widget.SideBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lj on 2016/12/5.
 * 选择城市
 */
public class SelectCityActivity extends BaseActivity implements SectionIndexer, View.OnClickListener{

    private ListView sortListView;
    private SideBarView sideBar;
    private LinearLayout titleLayout;
    private TextView title;

    private SortGroupMemberAdapter adapter;
    private int lastFirstVisibleItem = -1;
    private List<GroupMemberBean> mSourceDateList = new ArrayList<>();
    private PinyinComparator mPinyinComparator;
    private List<SelectProvinceModel> mList;

    private int mSelectedType = 1;//1-省 2-市 3-区

    private int mProvinceIndex = -1;
    private int mCityIndex = -1;

    private GridView mGridHotCity;
    private ArrayList<String> mProvinceIdList = new ArrayList<>();
    private SelectCityAdapter mSelectCityAdapter = null;
    private GridView mGridCity;
    private HotCityAdapter mHotCityAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        initTitle(R.string.city_select, true);
//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < mCities.length; i++){
//            list.add(i, mCities[i]);
//        }
         mGridHotCity = (GridView) findViewById(R.id.gridHotCity);
//        gridHotCity.setAdapter(adapter);
        initViews();
        getHotCityList();
//        getProvinceList();
        mGridHotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("cityId", mHotCityAdapter.getHotCityId(i));
                intent.putExtra("cityName", mHotCityAdapter.getHotCityName(i));
                setResult(112, intent);
                finish();
            }
        });
    }

    private void initViews() {
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title_layout_catalog);
        mPinyinComparator = new PinyinComparator();
        sideBar = (SideBarView) findViewById(R.id.sideBarView);
        sideBar.setOnTouchingLetterChangedListener(new SideBarView.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });
        sortListView = (ListView) findViewById(R.id.address_list);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                getCityList(mProvinceIdList.get(position));
//
//                final Dialog dialog = new Dialog(SelectCityActivity.this);
//                View dialogView = View.inflate(SelectCityActivity.this, R.layout.dialog_city, null);
//                mGridCity = (GridView) dialogView.findViewById(R.id.gridCity);
//                dialog.setTitle("请选择城市");
//                dialog.setContentView(dialogView);
//                dialog.show();
//                mGridCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Intent intent = new Intent();
//                        intent.putExtra("cityId", mSelectCityAdapter.getCityId(i));
//                        intent.putExtra("cityName", mSelectCityAdapter.getCityName(i));
//                        setResult(112, intent);
//                        dialog.dismiss();
//                        finish();
//                    }
//                });

//                Toast.makeText(SelectCityActivity.this, adapter.getSelectItem(position), Toast.LENGTH_SHORT).show();
                if(mSelectedType <= 3) {
//                    int index = ((GroupMemberBean) adapter.getItem(position)).getIndex();
//                    selectAddress(index);
                }
            }
        });
        adapter = new SortGroupMemberAdapter(this, mSourceDateList);
        sortListView.setAdapter(adapter);
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(mSourceDateList.size() <= 1) {
                    return;
                }
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(mSourceDateList.get(getPositionForSection(section)).getSortLetters());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    /**
     * 为ListView填充数据
     * @return
     */
    private List<GroupMemberBean> filledData(ArrayList<String> list) {
        List<GroupMemberBean> mSortList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String data = list.get(i).trim();
            GroupMemberBean sortModel = new GroupMemberBean();
            sortModel.setName(data);
            sortModel.setIndex(i);
            String pinyin = CharacterParser.getInstance().getSelling(data);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return mSourceDateList.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < mSourceDateList.size(); i++) {
            String sortStr = mSourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View v) {
        String content = "";
        if (v.getId() != R.id.imageLeft) {
            content = ((TextView) v).getText().toString();
        }
        switch (v.getId()){
//            case R.id.province:
//                if(!content.equals("省")) {
//                    mSelectedType = 1;
//                    changeData(mProvinceIndex);
//                    txtProvince.setText("省");
//                    txtCity.setText("市");
//                    txtZone.setText("区");
//                    txtProvince.setCompoundDrawables(null, null, null, null);
//                    txtCity.setCompoundDrawables(null, null, null, null);
//                    txtZone.setCompoundDrawables(null, null, null, null);
//                }
//                break;
//            case R.id.city:
//                if(!content.equals("市")) {
//                    mSelectedType = 2;
//                    changeData(mProvinceIndex);
//                    txtCity.setText("市");
//                    txtZone.setText("区");
//                    txtCity.setCompoundDrawables(null, null, null, null);
//                    txtZone.setCompoundDrawables(null, null, null, null);
//                }
//                break;
//            case R.id.zone:
//                if(!content.equals("区")) {
//                    mSelectedType = 3;
//                    changeData(mCityIndex);
//                    txtZone.setText("区");
//                    txtZone.setCompoundDrawables(null, null, null, null);
//                }
//                break;
//            case R.id.txtRight:
//                String province = txtProvince.getText().toString();
//                String city = txtCity.getText().toString();
//                String zone = txtZone.getText().toString();
////                String street = ((EditText)findViewById(R.id.edit_address)).getText().toString();
//                if(province.equals("省")){
//                    Toast.makeText(SelectCityActivity.this, "请选择所在的省份", Toast.LENGTH_SHORT).show();
//                } else if(city.equals("市")) {
//                    Toast.makeText(SelectCityActivity.this, "请选择所在城市", Toast.LENGTH_SHORT).show();
//                } else if(zone.equals("区")) {
//                    Toast.makeText(SelectCityActivity.this,"请选择所在的区域", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent();
//                    intent.putExtra("province", province);
//                    intent.putExtra("city", city);
//                    intent.putExtra("zone", zone);
////                    intent.putExtra("street", street);
//                    setResult(200, intent);
//                    finish();
//                }
//                break;
//            case R.id.imageLeft:
//                finish();
//                break;
        }
    }

//    private void setTextRightIcon(TextView textView) {
//        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.photo_delete_icon);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        textView.setCompoundDrawables(null, null, drawable, null);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList.clear();
        mSourceDateList.clear();
        mList = null;
        mSourceDateList = null;
    }

    private void getHotCityList(){
        HashMap<String, String> map = new HashMap<>();
        VolleyUtil.SendVolleyPostBean(MainApi.GET_HOT_CITY, map, new VolleyUtil.volleyInterface() {
            @Override
            public void ResponseResult(Object jsonObject) {
                ArrayList<HotCityModel> list = JsonUtil.jsonToBeanList(jsonObject.toString(), HotCityModel.class);
                mHotCityAdapter = new HotCityAdapter(list, SelectCityActivity.this);
                mGridHotCity.setAdapter(mHotCityAdapter);
            }

            @Override
            public void ResponseError(Object volleyError) {
                ToastUtil.showShortToast(volleyError.toString());
            }
        });
    }

//    private void getProvinceList(){
//        HashMap<String, String> map = new HashMap<>();
//        JSONObject ob = new JSONObject(map);
//        JsonObjectRequest request = new JsonObjectRequest(MainApi.GET_PROVINCE, ob, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                try {
//                    JSONObject object = new JSONObject(jsonObject.toString());
//                    if (object.getString("code").equals("2000")) {
//                        Log.e("获取省的返回码---", jsonObject.toString());
//                        mList = JsonUtil.jsonToBeanList(object.getString("data"), SelectProvinceModel.class);
//                        ArrayList<String> list = new ArrayList<>();
//                        for(int i = 0; i < mList.size(); i++){
//                            for (int j = 0; j <mList.get(i).data.size(); j++) {
//                                list.add(mList.get(i).data.get(j).area_name);
//                                mProvinceIdList.add(mList.get(i).data.get(j).area_id);
//                            }
//                        }
//                        mSourceDateList = filledData(list);
//                        Collections.sort(mSourceDateList, mPinyinComparator);
//                        adapter.updateListView(mSourceDateList);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, errorListener);
//        volleyQueue.add(request);
//    }
//
//    private void getCityList(String provinceId){
//        HashMap<String, String> map = new HashMap<>();
//        map.put("id", provinceId);
//        JSONObject ob = new JSONObject(map);
//        JsonObjectRequest request = new JsonObjectRequest(MainApi.GET_CITY, ob, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                try {
//                    JSONObject object = new JSONObject(jsonObject.toString());
//                    if (object.getString("code").equals("2000")) {
//                        Log.e("获取市的返回码---", jsonObject.toString());
//                        ArrayList<SelectCityModel> list = JsonUtil.jsonToBeanList(object.getString("data"), SelectCityModel.class);
//                        mSelectCityAdapter = new SelectCityAdapter(list, SelectCityActivity.this);
//                        mGridCity.setAdapter(mSelectCityAdapter);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, errorListener);
//        volleyQueue.add(request);
//    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(SelectCityActivity.this, "网络出现异常，请稍后再试", Toast.LENGTH_SHORT).show();
        }
    };
}
