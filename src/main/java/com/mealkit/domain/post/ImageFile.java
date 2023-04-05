package com.mealkit.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mealkit.domain.post.admin.AdminPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_file_no")
    private Long imageFileNo;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="home_id")
    private AdminPost adminPost;

    public void setAdminPost(AdminPost adminPost){
        if(this.adminPost!=null){
            this.adminPost.getImageFile().remove(this);
        }
        this.adminPost=adminPost;
        if(!adminPost.getImageFile().contains(this))
            adminPost.getImageFile().add(this);
    }

    @Builder
    public ImageFile(String originalName, String filePath, Long fileSize) {
        this.originalName = originalName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
    public ImageFile() {}
}
