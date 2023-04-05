package com.mealkit.repository;

import com.mealkit.domain.post.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageFile, Long> {

    @Query(value="select file_path from image_file where home_id = ?1 ", nativeQuery = true)
    List<String> imageResult(long home_id);


}
