package com.mealkit.service;

import com.mealkit.domain.post.ImageFile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {
    public static List<ImageFile> parseFile(List<MultipartFile> multipartFiles) throws Exception {
        List<ImageFile> iamgeFileList = new ArrayList<>();
        if (multipartFiles.isEmpty())
            return iamgeFileList;

        if (!CollectionUtils.isEmpty(multipartFiles)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String today = now.format(dateTimeFormatter);

       //     E:\ProJectS\MealKitProject\MealKit\src\main\resources
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = "E:\\ProJectS\\MealKitProject\\MealKit\\src\\main\\resources\\static\\img";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            for (MultipartFile multipartFile : multipartFiles) {
                String originalFileExtension;
                String contentType = multipartFile.getContentType();
                if (ObjectUtils.isEmpty(contentType))
                    break;
                else {
                    if (contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if (contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else break;
                }
                String fileName = System.nanoTime() + originalFileExtension;
                ImageFile imageFile = ImageFile.builder()
                        .originalName(multipartFile.getOriginalFilename())
                        .filePath(fileName)
                        .fileSize(multipartFile.getSize())
                        .build();

                iamgeFileList.add(imageFile);
                file = new File(path + File.separator + fileName);
                file.setWritable(true);
                file.setReadable(true);
                multipartFile.transferTo(file);
                System.out.println(file);
            }

        }

        return iamgeFileList;
    }

}