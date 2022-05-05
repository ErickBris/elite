package pro.badrobot.androiddev.nightclub;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by androiddev on 29.07.15.
 */
public interface NightClubService {
    @GET("/api/eventapi")
    void getEvents(Callback<Events> cb);

    @GET("/api/djsapi")
    void getDjs(Callback<Djs> cb);

    @GET("/api/photos")
    void getPhotos(Callback<Photos> cb);

    @GET("/api/newsapi")
    void getNews(Callback<News> cb);

    @GET("/api/itemsmix/allmix/")
    void getMixez(Callback<Mixez> cb);

    @GET("/api/aboutapi")
    void getAbout(Callback<About> cb);
}
