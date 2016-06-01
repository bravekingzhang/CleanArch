package com.tencent.clean.presentation.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tencent.clean.R;
import com.tencent.clean.RxBus;
import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.domain.usercase.SampleUserCase;
import com.tencent.clean.presentation.adapter.SampleAdapter;
import com.tencent.clean.presentation.event.SampleReloadEvent;
import com.tencent.clean.presentation.event.SampleRxEventClearDb;
import com.tencent.clean.presentation.presenters.MainPresenter;
import com.tencent.clean.presentation.presenters.impl.MainPresenterImpl;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hoollyzhang on 16/5/30.
 * Description :
 */
public class SampleFragment extends Fragment implements MainPresenter.View,RxBus.EventLisener {

    private ProgressDialog  sProgressDialog ;
    MainPresenter mainPresenter;

    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    SampleAdapter adapter = new SampleAdapter();

    CompositeSubscription _subscription = new CompositeSubscription();
    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_fagment, container, false);
        ButterKnife.bind(this, view);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        SampleUserCase sampleUserCase = new SampleUserCase(getActivity());
        mainPresenter = new MainPresenterImpl(this,sampleUserCase);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        sProgressDialog = ProgressDialog.show(getActivity(),"提示","加载中。。。。。");
        mainPresenter.resume();
        RxBus.getRxBusSingleton().subscribe(_subscription,this);
    }

    @Override
    public void onDestroyView() {
        if (sProgressDialog != null){
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
        mainPresenter.destroy();
        _subscription.clear();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (sProgressDialog != null){
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
        super.onDestroy();

    }

    @Override
    public void showProgress() {
        sProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        sProgressDialog.hide();
    }

    @Override
    public void showError(String message) {
        if (getActivity()!=null){
            Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showSampleData(List<SampleModel> sampleModels) {
        adapter.setImages(sampleModels);
    }

    @Override
    public void dealRxEvent(Object event) {
        if (event instanceof SampleRxEventClearDb){
            clearDb();
        }else if (event instanceof SampleReloadEvent){
            reloadData();
        }
    }

    private void clearDb() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(SampleModel.class);
        realm.commitTransaction();
        adapter.setImages(new LinkedList<SampleModel>());
        Toast.makeText(getActivity(), "缓存清除成功", Toast.LENGTH_LONG).show();
    }

    private void reloadData(){
        mainPresenter.resume();
    }
}
