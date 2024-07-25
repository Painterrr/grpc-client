package waf.fisa.Woorizip_Requirements.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequirement is a Querydsl query type for Requirement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequirement extends EntityPathBase<Requirement> {

    private static final long serialVersionUID = -1928244427L;

    public static final QRequirement requirement = new QRequirement("requirement");

    public final StringPath accountId = createString("accountId");

    public final StringPath buildingType = createString("buildingType");

    public final NumberPath<Integer> fee = createNumber("fee", Integer.class);

    public final StringPath hashtag = createString("hashtag");

    public final StringPath id = createString("id");

    public final StringPath location = createString("location");

    public final DatePath<java.time.LocalDate> moveInDate = createDate("moveInDate", java.time.LocalDate.class);

    public final StringPath phone = createString("phone");

    public QRequirement(String variable) {
        super(Requirement.class, forVariable(variable));
    }

    public QRequirement(Path<? extends Requirement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequirement(PathMetadata metadata) {
        super(Requirement.class, metadata);
    }

}

