package ezmart.services;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import ezmart.model.service.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class EzMartWebServices {

    public ResponseEntity<InputStreamResource> getImage(Long userId) {
        byte bytes[] = null;
        try {
            UserService service = new UserService();
            bytes = service.getImg(userId);

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(
                        new InputStreamResource(
                                new ByteInputStream(bytes, bytes.length)));
    }
}
