//package com.yundong.milk.user.adapter;
//
//import android.app.Dialog;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.yundong.milk.R;
//import com.yundong.milk.user.activity.ReceiptAddressActivity;
//import com.yundong.milk.user.model.AddressListModel;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by lj on 2016/11/17.
// */
//public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.GoodsHolder> implements View.OnClickListener
//{
//
////    private ArrayList<AddressListModel> mList;
//////    private Context mContext;
////    private RequestQueue volleyQueue;
////    private int mGetAddress;
////    private ReceiptAddressActivity mActivity;
////
////    public AddressListAdapter(ReceiptAddressActivity activity, int getAddress) {
////        this.mActivity = activity;
////        mList = new ArrayList<>();
////        this.mGetAddress = getAddress;
////        volleyQueue = Volley.newRequestQueue(mActivity);
////    }
////
////    public void addData(ArrayList<AddressListModel> list) {
////        mList.addAll(list);
////        notifyDataSetChanged();
////    }
////
//////    public String getTaskId(int index){
//////    return mList.get(index).taskId;
//////}
////
////    public void removeData(){
////        mList.clear();
////        notifyDataSetChanged();
////    }
////
////    @Override
////    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receipt_address, parent, false);
////        return new GoodsHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(GoodsHolder holder, int position) {
////
////        AddressListModel model = mList.get(position);
////        if (model.is_default.equals("0")) {
////            holder.txtIsDef.setText("收货人信息(默认)");
////        }else if (model.is_default.equals("1")){
////            holder.txtIsDef.setText("收货人信息");
////        }
////        holder.txtConsignee.setText("收货人: " + model.name);
////        holder.txtPhone.setText("手机号: " + model.mobile);
////        holder.txtAddress.setText("收货地址: " + model.cityinfo + model.areainfo);
////        holder.txtEdit.setOnClickListener(this);
////        holder.txtDelete.setOnClickListener(this);
//////        holder.itemView.setOnClickListener(this);
////        holder.txtEdit.setTag(position);
////        holder.txtDelete.setTag(position);
//////        holder.itemView.setTag(position);
////    }
////
////    @Override
////    public int getItemCount() {
////        return mList.size();
////    }
////
////    @Override
////    public void onClick(View view) {
////        final int index = (Integer) view.getTag();
////        switch (view.getId()){
////            case R.id.txtEdit:
////                AddressListModel model = mList.get(index);
////                Intent intent = new Intent(mActivity, AddAddressActivity.class);
////                intent.putExtra("type", 0);
////                intent.putExtra("consignee", model.name);
////                intent.putExtra("phone", model.mobile);
////                intent.putExtra("area", model.cityinfo);
////                intent.putExtra("street", model.areainfo);
////                intent.putExtra("isDef", model.is_default);
////                intent.putExtra("address_id", model.address_id);
//////                intent.putExtra("detail", );
////                mActivity.startActivity(intent);
////                break;
////            case R.id.txtDelete: // 删除
////                final Dialog dialog = new Dialog(mActivity);
////                View view1 = View.inflate(mActivity, R.layout.dialog_select_address, null);
////                ((TextView) view1.findViewById(R.id.txtSelectAddress)).setText("你确认要删除吗?");
////                view1.findViewById(R.id.txtNo).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        dialog.dismiss();
////                    }
////                });
////                view1.findViewById(R.id.txtYes).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        deleteAddress(index);
////                        dialog.dismiss();
////                    }
////                });
////                dialog.setTitle("删除地址");
////                dialog.setContentView(view1);
////                dialog.show();
////                break;
//////            default:
//////                if (mGetAddress == 100){
//////                    Toast.makeText(mActivity, mList.get(index).cityinfo + mList.get(index).areainfo, Toast.LENGTH_SHORT).show();
//////                    Intent intentAddress = new Intent();
//////                    intentAddress.putExtra("address", mList.get(index).cityinfo + mList.get(index).areainfo);
//////                    mActivity.setResult(200, intentAddress);
//////                    mActivity.finish();
//////                }
//////                break;
////        }
////    }
////
////    public static class GoodsHolder extends RecyclerView.ViewHolder {
////
////        private TextView txtIsDef;
////        private TextView txtEdit;
////        private TextView txtConsignee;
////        private TextView txtPhone;
////        private TextView txtAddress;
////        private TextView txtDelete;
////
////        public GoodsHolder(View itemView) {
////            super(itemView);
////            txtIsDef = (TextView) itemView.findViewById(R.id.txtIsDef);
////            txtEdit = (TextView) itemView.findViewById(R.id.txtEdit);
////            txtConsignee = (TextView) itemView.findViewById(R.id.txtConsignee);
////            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
////            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
////            txtDelete = (TextView) itemView.findViewById(R.id.txtDelete);
////        }
////    }
////
////    private void deleteAddress(final int position){
////        Map<String, String> map = new HashMap<>();
////        map.put("address_id", mList.get(position).address_id);
////        final JSONObject ob = new JSONObject(map);
////        JsonObjectRequest request = new JsonObjectRequest(UserApi.DELETE_ADDRESS, ob, new Response.Listener<JSONObject>() {
////
////            @Override
////            public void onResponse(JSONObject jsonObject) {
////                Log.e("删除地址返回", jsonObject.toString());
////                try {
////                    JSONObject object = new JSONObject(jsonObject.toString());
////                    if (object.getString("code").equals("2000")) {
////                        mList.remove(position);
////                        notifyDataSetChanged();
////                        Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////        }, errorListener);
////        volleyQueue.add(request);
////    }
////
////
////    Response.ErrorListener errorListener = new Response.ErrorListener() {
////
////        @Override
////        public void onErrorResponse(VolleyError volleyError) {
////            Toast.makeText(mActivity, "网络出现异常，请稍后再试", Toast.LENGTH_SHORT).show();
////        }
////    };
//
////    public static interface GetReturnAddressInterface{
////        public String getAddress(String address);
////    };
//}