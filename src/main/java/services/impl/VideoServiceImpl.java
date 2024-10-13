package services.impl;

import dao.IVideoDao;
import dao.impl.VideoDaoImpl;
import entity.Video;
import services.IVideoService;

import java.util.List;

public class VideoServiceImpl implements IVideoService {
    private IVideoDao videoDao = new VideoDaoImpl();

    @Override
    public void insert(Video video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void delete(int videoId) throws Exception {
        videoDao.delete(videoId);
    }

    @Override
    public Video findById(int videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }
}
