package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Album;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Comment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Image;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Post;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @GET("albums")
    Call<List<Album>> getAlbums();

    @GET("albums/{id}")
    Call<Album> getAlbumById(@Path("id") String id);

    @GET("photos")
    Call<List<Image>> getImage();

    @GET("photos/{id}")
    Call<Image> getImageById(@Path("id") String id);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") String id);

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") String id);

    @GET("comments")
    Call<List<Comment>> getComments();

    @GET("comments/{id}")
    Call<Comment> getCommentById(@Path("id") String id);

    @GET("todos")
    Call<List<Todo>> getTodos();

    @GET("todos/{id}")
    Call<Todo> getTodoById(@Path("id") String id);
}
