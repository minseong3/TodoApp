package todo.todominsung.module.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jrebel")
public class JRebelController {
    @PostMapping("/leases")
    public ResponseEntity<Void> handleJRebleRequest() {
        return ResponseEntity.noContent().build();
    }
}
