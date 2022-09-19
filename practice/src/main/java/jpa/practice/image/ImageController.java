package jpa.practice.image;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageController {

    @PostMapping("/admins/pManage/join/{id}") //이 id는 사진 고유
    public void getFile(@PathVariable("id") String id, HttpServletRequest request,
                        HttpServletResponse response)  throws ServletException, Throwable {
        request.setCharacterEncoding("utf-8");
        MultipartRequest multi = null;
        int fileSize = 10*1024*1024;
        String filePath = request.getSession().getServletContext().getRealPath("/images");
        System.out.println("파일 경로: " +filePath);

        try {
            multi = new MultipartRequest(request, filePath, fileSize, "utf-8", new DefaultFileRenamePolicy());
        } catch(Exception e) {
            e.printStackTrace();;
        }

        String filename = multi.getFilesystemName()
    }
}
