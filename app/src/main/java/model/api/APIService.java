package model.api;

import io.reactivex.Flowable;
import model.bean.HomeBean;
import model.bean.TopicBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // 首页数据
    @GET("index")
    Flowable<HomeBean> getHome();

    //专题数据请求接口
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);
}
