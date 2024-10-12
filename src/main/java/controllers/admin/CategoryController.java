package controllers.admin;

import entity.Category;
import ultils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import services.ICategoryService;
import services.impl.CategoryServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/categories")) {
            List<Category> list = categoryService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

        } else if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

        } else if (url.contains("/admin/category/edit")) {

            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.findById(id);

            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));

            try {
                categoryService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("/admin/category/insert")) {

            //Lay du lieu tu form
            String categoryName = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String linkImage = req.getParameter("images");

            // dua du lieu vao model
            Category category = new Category();
            category.setStatus(status);

            String fname = linkImage;
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part part = req.getPart("imageUpload");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    category.setImages(fname);
                }
            } catch (FileNotFoundException d) {

            }
            category.setImages(fname);
            category.setCategoryname(categoryName);

            categoryService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        }
        if (url.contains("/admin/category/edit")) {
            Category category = categoryService.findById(Integer.parseInt(req.getParameter("id")));
            String oldImage = category.getImages();

            String name = req.getParameter("name");
            int status = Integer.parseInt(req.getParameter("status"));
            String linkImage = req.getParameter("images");

            String fname = linkImage;
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part filePart = req.getPart("imageUpload");
                if (filePart.getSize() > 0) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    // Change file name
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    filePart.write(uploadPath + "/" + fname);
                } else if (!req.getParameter("image").isEmpty()) {
                    fname = req.getParameter("image");
                } else {
                    fname = oldImage;
                }

                File file = new File(uploadPath + "/" + oldImage);
                if (file.exists()) file.deleteOnExit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            category.setCategoryname(name);
            category.setImages(fname);
            category.setStatus(status);
            categoryService.update(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
