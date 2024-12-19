package com.shanks.weaving.web;

import com.shanks.weaving.entities.Post;
import com.shanks.weaving.repository.PostRepository;
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
        var posts = this.postRepository.findAllById(List.of(1,2));
        return ResponseEntity.ok(posts);
    }

    // add dummy method in controller.
    // having a private method here doesn't affect like tests.
    private Post getPost(Integer id){
        return this.postRepository.findById(id).orElseThrow();
    }
}
