package com.shanks.weaving.entities;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Table(name = "post_detail")
@Entity
public class PostDetail {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "detail")
    private String detail;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PROTECTED)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Post post;

}
