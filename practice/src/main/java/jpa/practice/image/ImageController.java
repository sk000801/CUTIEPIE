package jpa.practice.image;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@WebServlet("/admins/pManage/join/{id}")
@Data
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;

    @PostMapping("/admins/pManage/join/{id}") //이 id는 사진 고유
    public void getFile(@PathVariable("id") String id, HttpServletRequest request,
                        HttpServletResponse response)  throws ServletException, Throwable {

        request.setCharacterEncoding("utf-8");
        int fileSize = 10 * 1024 * 1024;
        MultipartRequest multi = null;
        String filePath = request.getSession().getServletContext().getRealPath("/images");
        System.out.println("파일 경로: " + filePath);
        
        try {
            multi = new MultipartRequest(request, filePath, fileSize, "utf-8", new DefaultFileRenamePolicy());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filename = multi.getFilesystemName(imageRepository.findById(id).getStoreFilename());
    }
}
