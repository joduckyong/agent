package kr.or.lx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        
        return "index";
    }
    
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }    
    
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
//        postService.delete(id);
        return id;
    }    
}
