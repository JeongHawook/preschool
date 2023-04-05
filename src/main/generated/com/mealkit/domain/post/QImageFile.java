package com.mealkit.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImageFile is a Querydsl query type for ImageFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageFile extends EntityPathBase<ImageFile> {

    private static final long serialVersionUID = 499528907L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImageFile imageFile = new QImageFile("imageFile");

    public final com.mealkit.domain.post.admin.QAdminPost adminPost;

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> imageFileNo = createNumber("imageFileNo", Long.class);

    public final StringPath originalName = createString("originalName");

    public QImageFile(String variable) {
        this(ImageFile.class, forVariable(variable), INITS);
    }

    public QImageFile(Path<? extends ImageFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImageFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImageFile(PathMetadata metadata, PathInits inits) {
        this(ImageFile.class, metadata, inits);
    }

    public QImageFile(Class<? extends ImageFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminPost = inits.isInitialized("adminPost") ? new com.mealkit.domain.post.admin.QAdminPost(forProperty("adminPost"), inits.get("adminPost")) : null;
    }

}

