package com.licrafter.cnode.api.service;

import com.licrafter.cnode.model.CollectionBody;
import com.licrafter.cnode.model.LoginBody;
import com.licrafter.cnode.model.LoginResultModel;
import com.licrafter.cnode.model.AccessTokenBody;
import com.licrafter.cnode.model.MarkResultModel;
import com.licrafter.cnode.model.NotificationModel;
import com.licrafter.cnode.model.PostTopicResultModel;
import com.licrafter.cnode.model.ReplyBody;
import com.licrafter.cnode.model.ReplyResultModel;
import com.licrafter.cnode.model.TabModel;
import com.licrafter.cnode.model.TopicDetailModel;
import com.licrafter.cnode.model.UnReadCountModel;
import com.licrafter.cnode.model.UserDetailModel;
import com.licrafter.cnode.model.entity.PostTopic;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lijx on 2017/2/24.
 */

public interface CNodeService {


    /**
     * 获取首页文章列表
     *
     * @param pageIndex
     * @param limit
     * @param mdrender
     * @return
     */
    @GET("topics")
    Observable<TabModel> getTopicPage(@Query("page") Integer pageIndex, @Query("limit") Integer limit, @Query("mdrender") Boolean mdrender);

    /**
     * 根据tab名字获取tab文章列表
     * 可用tab: ask share job good
     *
     * @param tab
     * @param pageIndex
     * @param limit
     * @param mdrender
     * @return
     */
    @GET("topics")
    Observable<TabModel> getTabByName(@Query("tab") String tab, @Query("page") Integer pageIndex, @Query("limit") Integer limit, @Query("mdrender") Boolean mdrender);

    /**
     * 根据topic Id 获取文章详情
     *
     * @param topicId
     * @param accesstoken
     * @param mdrender
     * @return
     */
    @GET("topic/{topicId}")
    Observable<TopicDetailModel> getTopicDetailById(@Path("topicId") String topicId, @Query("accesstoken") String accesstoken, @Query("mdrender") Boolean mdrender);

    /**
     * 获取用户详细信息
     *
     * @param userName
     * @return
     */
    @GET("user/{userName}")
    Observable<UserDetailModel> getUserDetailByName(@Path("userName") String userName);

    /**
     * 登录
     *
     * @param body
     * @return
     */
    @POST("accesstoken")
    Observable<LoginResultModel> login(@Body LoginBody body);

    /**
     * 获取未读消息数
     *
     * @param accesstoken
     * @return
     */
    @GET("message/count")
    Observable<UnReadCountModel> getUnReadCount(@Query("accesstoken") String accesstoken);

    /**
     * 获取所有的消息，包括已读和未读
     *
     * @param accesstoken
     * @param mdrender
     * @return
     */
    @GET("messages")
    Observable<NotificationModel> getAllNotifications(@Query("accesstoken") String accesstoken, @Query("mdrender") Boolean mdrender);

    /**
     * 标记所有消息
     *
     * @return
     */
    @POST("message/mark_all")
    Observable<MarkResultModel> markAllMsg(@Body AccessTokenBody markAllBody);

    /**
     * 创建新标题
     *
     * @param topic
     * @return
     */
    @POST("topics")
    Observable<PostTopicResultModel> createTopic(@Body PostTopic topic);

    /**
     * 收藏帖子
     *
     * @param accessToken
     * @return
     */
    @POST("topic_collect/collect")
    Observable<Void> collectPost(@Body CollectionBody accessToken);

    /**
     * 取消收藏
     *
     * @param accessToken
     * @return
     */
    @POST("topic_collect/de_collect ")
    Observable<Void> deCollectPost(@Body CollectionBody accessToken);

    /**
     * 点赞和取消点赞
     *
     * @param accessToken
     * @param reply_id
     * @return
     */
    @POST("reply/{reply_id}/ups")
    Observable<Void> makeUp(@Body AccessTokenBody accessToken, @Path("reply_id") String reply_id);

    /**
     * 发表评论
     *
     * @param topic_id
     * @param replyBody
     * @return
     */
    @POST("topic/{topic_id}/replies")
    Observable<ReplyResultModel> reply(@Path("topic_id") String topic_id, @Body ReplyBody replyBody);
}
