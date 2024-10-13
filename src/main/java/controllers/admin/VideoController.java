package controllers.admin;

import entity.Video;
import entity.Category;
import services.IVideoService;
import services.ICategoryService;
import services.impl.VideoServiceImpl;
import services.impl.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit", "/admin/video/update", "/admin/video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public IVideoService videoService = new VideoServiceImpl();
    public ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/videos")) {
            // List all videos
            List<Video> list = videoService.findAll();
            req.setAttribute("listVideos", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);

        } else if (url.contains("/admin/video/add")) {
            // Show add video form with category selection
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

        } else if (url.contains("/admin/video/edit")) {
            // Show edit video form
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            List<Category> categories = categoryService.findAll();

            req.setAttribute("video", video);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);

        } else if (url.contains("/admin/video/delete")) {
            // Delete video
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                videoService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/video/insert")) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String categoryIdStr = req.getParameter("categoryId");
            String activeStr = req.getParameter("active");
            String poster = req.getParameter("poster");

            int categoryId = 0;
            boolean active = false;

            try {
                if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                    categoryId = Integer.parseInt(categoryIdStr);
                }
                if (activeStr != null && !activeStr.isEmpty()) {
                    active = Integer.parseInt(activeStr) == 1;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active);
            video.setPoster(poster);

            // Set category
            Category category = categoryService.findById(categoryId);
            video.setCategory(category);

            // Insert video into the database
            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");

        } else if (url.contains("/admin/video/update")) {
            // Get existing video to update
            Video video = videoService.findById(Integer.parseInt(req.getParameter("id")));

            // Update video properties
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String categoryIdStr = req.getParameter("categoryId");
            String activeStr = req.getParameter("active");
            String poster = req.getParameter("poster");

            // Add null and empty string checks
            int categoryId = 0;
            boolean active = false;

            try {
                if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                    categoryId = Integer.parseInt(categoryIdStr);
                }
                if (activeStr != null && !activeStr.isEmpty()) {
                    active = Integer.parseInt(activeStr) == 1;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active);
            video.setPoster(poster);

            Category category = categoryService.findById(categoryId);
            video.setCategory(category);

            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
