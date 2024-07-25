package waf.fisa.Woorizip_Requirements.mapper;

import org.springframework.stereotype.Component;
import waf.fisa.Woorizip_Requirements.dto.RequirementDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementReqDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementRespDto;
import waf.fisa.Woorizip_Requirements.entity.Requirement;

import java.util.UUID;

@Component
public class RequirementMapper {

    /**
     * RequirementReqDto에서 Requirement 엔티티로 변환
     * @param dto RequirementReqDto
     * @return Requirement 엔티티
     */
    public Requirement fromReqDtoToEntity(RequirementReqDto dto) {
        return Requirement.builder()
                .id(dto.getId() != null ? dto.getId() : UUID.randomUUID().toString())
                .accountId(dto.getAccountId())
                .phone(dto.getPhone())
                .location(dto.getLocation())
                .buildingType(dto.getBuildingType())
                .fee(dto.getFee())
                .moveInDate(dto.getMoveInDate())
                .hashtag(dto.getHashtag())
                .build();
    }

    /**
     * Requirement 엔티티에서 RequirementRespDto로 변환
     * @param requirement Requirement 엔티티
     * @return RequirementRespDto
     */
    public RequirementRespDto fromEntityToRespDto(Requirement requirement) {
        return new RequirementRespDto(
                requirement.getId(),
                requirement.getAccountId(),
                requirement.getPhone(),
                requirement.getLocation(),
                requirement.getBuildingType(),
                requirement.getFee(),
                requirement.getMoveInDate(),
                requirement.getHashtag()
        );
    }

    /**
     * RequirementReqDto에서 RequirementRespDto로 변환
     * @param dto RequirementReqDto
     * @return RequirementRespDto
     */
    public RequirementRespDto fromReqDtoToRespDto(RequirementReqDto dto) {
        Requirement requirement = fromReqDtoToEntity(dto);
        return fromEntityToRespDto(requirement);
    }

    /**
     * RequirementDto에서 RequirementRespDto로 변환
     * @param dto RequirementDto
     * @return RequirementRespDto
     */
    public RequirementRespDto fromDtoToRespDto(RequirementDto dto) {
        return new RequirementRespDto(
                dto.getId(),
                dto.getAccountId(),
                dto.getPhone(),
                dto.getLocation(),
                dto.getBuildingType(),
                dto.getFee(),
                dto.getMoveInDate(),
                dto.getHashtag()
        );
    }
}
