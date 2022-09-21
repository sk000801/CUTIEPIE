package jpa.practice.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
public class ImageController2 {
    private final ImageService imageService;

    ///admins/pManage/join/{id}
    @PostMapping("/upload") //id는 사진 id
    public ResponseEntity<?> upload(MultipartFile file,
                                    HttpServletResponse response) throws IOException {
        if(file.isEmpty()) {
            response.sendError(404, "클라이언트 오류");
        }

        String fileName = imageService.saveImage(file);

        String downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        return new ResponseEntity<>(downloadURI, HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable("fileName") String fileName,
                                      HttpServletRequest request) throws FileNotFoundException {

        Resource resource = imageService.loadFile(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch(IOException ex) {
            log.info("파일 타입을 결정할 수 없습니다!");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\\\"\" + resource.getFilename() + \"\\\"")
                .body(resource);
    }
}
