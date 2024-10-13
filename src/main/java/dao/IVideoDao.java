package dao;

import entity.Video;

import java.util.List;

public interface IVideoDao {
    void insert(Video video);
    void update(Video video);
    void delete(int videoId) throws Exception;
    Video findById(int videoId);
    List<Video> findAll();
    List<Video> findByTitle(String title);
}
