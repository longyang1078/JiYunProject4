package base;

import java.lang.ref.WeakReference;

import interfaces.IBasePresenter;
import interfaces.IBaseView;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    public V mView;
    private WeakReference<V> weakReference;
    @Override
    public void onAttachView(V view) {
        weakReference = new WeakReference(view);
        mView = weakReference.get();
    }
    @Override
    public void unAttachView() {
        if(mView !=null){
            mView = null;
        }
    }
}
