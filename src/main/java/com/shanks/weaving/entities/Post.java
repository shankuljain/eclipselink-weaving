package com.shanks.weaving.entities;

import lombok.Data;
import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

import javax.persistence.*;

@Data
@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @BatchFetch(BatchFetchType.IN)
    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDetail postDetail;

    public void setPostDetail(PostDetail postDetail){
        this.postDetail = postDetail;
        postDetail.setPost(this);
    }

    @PostPersist
    private void fillIds(){
        this.postDetail.setId(this.id);
    }

}
