package com.yundong.milk.present;

import com.yundong.milk.interaptor.impl.CancleOrderImpl;
import com.yundong.milk.interaptor.impl.OrderListImpl;
import com.yundong.milk.model.BaseReceiveBean;
import com.yundong.milk.model.OrderListBean;
import com.yundong.milk.view.ICancleOrderView;
import com.yundong.milk.view.IOrderListView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MingweiLi on 2017/3/6.
 */

public class MineOrderFragmentPresenter {
    private static MineOrderFragmentPresenter maineOrderActivityPresenter;
    //获得我的订单
    private OrderListImpl orderList;
    private IOrderListView orderListView;

    //取消订单
    private CancleOrderImpl cancleOrder;
    private ICancleOrderView iCancleOrderView;

    private MineOrderFragmentPresenter(){
        orderList=new OrderListImpl();
        cancleOrder=new CancleOrderImpl();
    }

    public static MineOrderFragmentPresenter getInstance(){
            maineOrderActivityPresenter=new MineOrderFragmentPresenter();
        return maineOrderActivityPresenter;
    }

    public MineOrderFragmentPresenter with(IOrderListView orderListView){
        this.orderListView=orderListView;
        return maineOrderActivityPresenter;
    }
    public MineOrderFragmentPresenter with(ICancleOrderView iCancleOrderView){
        this.iCancleOrderView=iCancleOrderView;
        return maineOrderActivityPresenter;
    }

    public void orderList(String user_id, String order_state, String is_comment, String page, String page_data){
        orderList.modifyNacikName(user_id,order_state,is_comment,page,page_data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        orderListView.orderListOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(OrderListBean orderListBean) {
                        orderListView.orderList(orderListBean);
                    }
                });
    }

    //取消订单
    public void cancleOrder(String order_id){
        cancleOrder.cancleOrder(order_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseReceiveBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iCancleOrderView.cancleOrderOnError(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseReceiveBean baseReceiveBean) {
                        iCancleOrderView.cancleOrder(baseReceiveBean);
                    }
                });
    }
}
