//package jpa.practice.image;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.client.RestTemplate;
//
//public class ImageController2 {
//    private static final RestTemplate REST_TEMPLATE;
//
//    static {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(3000);
//        factory.setReadTimeout(3000);
//        factory.setBufferRequestBody(false);
//        REST_TEMPLATE = new RestTemplate(factory);
//    }
//
//    @RequestMapping(value = "/admins/pManage/join/{id}", method = RequestMethod.POST)
//    public ResponseEntity<byte[]> upload(@PathVariable("id") String id) {
//        HttpStatus httpStatus = HttpStatus.CREATED;
//
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//            String url = "http://localhost:8080/admins/pManage/join/{id}";
//        }
//    }
//}
