package waf.fisa.Woorizip_Requirements.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import waf.fisa.Woorizip_Requirements.dto.QRequirementDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementDto;
import waf.fisa.Woorizip_Requirements.entity.Requirement;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static waf.fisa.Woorizip_Requirements.entity.QRequirement.requirement;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<RequirementDto> findByBuilder(Requirement input) {
        BooleanBuilder builder = new BooleanBuilder();

        log.info(" in RepositoryCustom, input: \n location: {}, \n buildingType: {}, " +
                        "\n fee: {}, \n moveInDate: {}, \n hashTag: {}",
                input.getLocation(), input.getBuildingType()
                , input.getFee(), input.getMoveInDate(), input.getHashtag());

        if (hasText(input.getLocation())) {
            builder.and(requirement.location.eq(input.getLocation()));
        }

        if (hasText(input.getBuildingType())) {
            builder.and(requirement.buildingType.eq(input.getBuildingType()));
        }

        if (input.getFee() != 0 && hasText(String.valueOf(input.getFee()))) {
            builder.and(requirement.fee.loe(input.getFee()));
        }

        if (input.getMoveInDate() != null) {
            if (hasText(String.valueOf(input.getMoveInDate()))) {
                builder.and(requirement.moveInDate.after(input.getMoveInDate()));
            }
        }

        if (hasText(input.getHashtag())) {
            builder.and(requirement.hashtag.contains(input.getHashtag()));
        }

        log.info("** in RepositoryCustom, builder: {}", builder);

        return queryFactory
            .select(new QRequirementDto(
                    requirement.id,
                    requirement.accountId,
                    requirement.phone,
                    requirement.location,
                    requirement.buildingType,
                    requirement.fee,
                    requirement.moveInDate,
                    requirement.hashtag
            ))
            .from(requirement)
            .where(builder)
            .fetch();
    }
}
