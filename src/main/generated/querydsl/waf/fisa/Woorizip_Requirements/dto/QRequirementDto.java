package waf.fisa.Woorizip_Requirements.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * waf.fisa.Woorizip_Requirements.dto.QRequirementDto is a Querydsl Projection type for RequirementDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRequirementDto extends ConstructorExpression<RequirementDto> {

    private static final long serialVersionUID = -245097656L;

    public QRequirementDto(com.querydsl.core.types.Expression<String> id, com.querydsl.core.types.Expression<String> accountId, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> location, com.querydsl.core.types.Expression<String> buildingType, com.querydsl.core.types.Expression<Integer> fee, com.querydsl.core.types.Expression<java.time.LocalDate> moveInDate, com.querydsl.core.types.Expression<String> hashtag) {
        super(RequirementDto.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, int.class, java.time.LocalDate.class, String.class}, id, accountId, phone, location, buildingType, fee, moveInDate, hashtag);
    }

}

