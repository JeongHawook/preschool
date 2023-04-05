package com.mealkit.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtag is a Querydsl query type for Hashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtag extends EntityPathBase<Hashtag> {

    private static final long serialVersionUID = -2134960470L;

    public static final QHashtag hashtag = new QHashtag("hashtag");

    public final com.mealkit.domain.constant.QAuditingFields _super = new com.mealkit.domain.constant.QAuditingFields(this);

    public final SetPath<com.mealkit.domain.post.admin.AdminPost, com.mealkit.domain.post.admin.QAdminPost> adminPosts = this.<com.mealkit.domain.post.admin.AdminPost, com.mealkit.domain.post.admin.QAdminPost>createSet("adminPosts", com.mealkit.domain.post.admin.AdminPost.class, com.mealkit.domain.post.admin.QAdminPost.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath hashtagName = createString("hashtagName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public QHashtag(String variable) {
        super(Hashtag.class, forVariable(variable));
    }

    public QHashtag(Path<? extends Hashtag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashtag(PathMetadata metadata) {
        super(Hashtag.class, metadata);
    }

}

