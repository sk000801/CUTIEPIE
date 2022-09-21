//package jpa.practice.image;
//
////import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
////import com.oreilly.servlet.MultipartRequest;
//import lombok.Data;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//@RequestMapping("/admins/pManage/join")
//@RequiredArgsConstructor
//public class ImageController {
//
//    private final ImageRepository imageRepository;
//
//    @GetMapping("/{id}") //이 id는 사진 고유
//    public ResponseEntity<byte[]> getFile(@PathVariable("id") String id, HttpServletRequest request,
//                                          HttpServletResponse response) {
//        ImageStore imageStore = imageRepository.findById(id);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", imageStore.getType());
//        headers.add("Content-Length", String.valueOf(imageStore.getData().length));
//
//        return new ResponseEntity<>(imageStore.getData(), headers, HttpStatus.OK);
//    }
//}
