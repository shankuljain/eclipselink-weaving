package com.shanks.weaving.web;

import com.shanks.weaving.entities.Post;
import com.shanks.weaving.repository.PostRepository;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<Post>> test(){
        QueryCountHolder.clear();
        var posts = this.postRepository.findAllById(List.of(1,2));
        var count = QueryCountHolder.getGrandTotal().getSelect();
        System.out.println(count);
        return ResponseEntity.ok(posts);
    }
}
