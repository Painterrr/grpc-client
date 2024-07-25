package waf.fisa.Woorizip_Requirements.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import waf.fisa.Woorizip_Requirements.dto.RequirementDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementReqDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementRespDto;
import waf.fisa.Woorizip_Requirements.entity.Requirement;
import waf.fisa.Woorizip_Requirements.mapper.RequirementMapper;
import waf.fisa.Woorizip_Requirements.repository.Repository;
import waf.fisa.Woorizip_Requirements.repository.RepositoryCustom;

import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
@NoArgsConstructor
public class Service {

    private Repository repository;
    private RepositoryCustom repositoryCustom;
    private GrpcService grpcService;
    private RequirementMapper mapper;

    @Autowired
    public Service (Repository repository, RepositoryCustom repositoryCustom,
                    GrpcService grpcService, RequirementMapper mapper) {
        this.repository = repository;
        this.repositoryCustom = repositoryCustom;
        this.grpcService = grpcService;
        this.mapper = mapper;
    }

    /**
     * isRegistered: 조건 등록 여부
     * @param accountId
     * @return 현 계정에 등록된 조건 존재 여부
     * 존재하면 true
     * 부재하면 false
     */
    public Boolean isRegistered (String accountId) {
        log.info("[in service]: {}", accountId);

        boolean isRegistered = repository.existedByAccountId(accountId);

        return isRegistered;
    }

    /**
     * save: 조건 등록
     * @param requirementReqDto
     * @return 등록된 조건 반환
     */
    public RequirementRespDto save(RequirementReqDto requirementReqDto) {
        log.info("[in service]: {}", requirementReqDto.toString());

        String phone = grpcService.getPhone(requirementReqDto.getAccountId());

        if (phone == null) {
            throw new RuntimeException("Failed to retrieve phone for accountId: " + requirementReqDto.getAccountId());

        } else {
            RequirementReqDto input = RequirementReqDto.builder()
                    .accountId(requirementReqDto.getAccountId())
                    .phone(phone)
                    .location(requirementReqDto.getLocation())
                    .buildingType(requirementReqDto.getBuildingType())
                    .fee(requirementReqDto.getFee())
                    .moveInDate(requirementReqDto.getMoveInDate())
                    .hashtag(requirementReqDto.getHashtag())
                    .build();

            Requirement requirement = repository.save(mapper.fromReqDtoToEntity(input));

            return mapper.fromEntityToRespDto(requirement);
        }
    }

    /**
     * readMine: 내 조건 읽기
     * @param accountId
     * @return 내 조건 반환
     */
    public RequirementRespDto readMine(String accountId) {
        log.info("[in Service]: {}", accountId);

        Requirement requirement = repository.readMine(accountId);

        return mapper.fromEntityToRespDto(requirement);
    }

    /**
     * readAll: 중개사 전제 조건 조회
     * @return 조건 전체 반환
     */
    public List<RequirementRespDto> readAll() {
        log.info("[in service]: ");

        List<Requirement> list = repository.findAll();

        return convertToRespDto(list);
    }

    private List<RequirementRespDto> convertToRespDto(List<Requirement> list){
        return list.stream()
                .map(mapper::fromEntityToRespDto)
                .toList();
    }

    /**
     * readFilter: 중개사 조건 필터 검색
     * @param requirementReqDto
     * @return 필터 검색 결과 반환
     */
    public List<RequirementRespDto> readFilter(RequirementReqDto requirementReqDto) {
        log.info("[in service]: {}", requirementReqDto.toString());

        Requirement requirement = mapper.fromReqDtoToEntity(requirementReqDto);

        List<RequirementDto> list = repositoryCustom.findByBuilder(requirement);

        if (!list.isEmpty()) {
            log.info("** list has ele");
        } else {
            log.info("** list is empty");
        }

        return fromDtoToRespDto(list);
    }

    private List<RequirementRespDto> fromDtoToRespDto(List<RequirementDto> list){
        return list.stream().map(
                mapper::fromDtoToRespDto
        ).toList();
    }

    /**
     * updateRequirement: 조건 수정
     * @param requirementReqDto
     * @return 수정된 조건 반환
     */
    @Transactional
    public RequirementRespDto update(RequirementReqDto requirementReqDto) {
        log.info("[in service]: {}", requirementReqDto.toString());

        Requirement target = repository.findById(requirementReqDto.getId()).orElseThrow(EntityNotFoundException::new);

        log.info("[in Service][target]: {}", target.toString());

        if (!requirementReqDto.getPhone().equals(target.getPhone())) {
            target.updatePhone(requirementReqDto.getPhone());
        }

        if (!requirementReqDto.getLocation().equals(target.getLocation())) {
            target.updateLocation(requirementReqDto.getLocation());
        }

        if (!requirementReqDto.getBuildingType().equals(target.getBuildingType())) {
            target.updateBuildingType(requirementReqDto.getBuildingType());
        }

        if (requirementReqDto.getFee() != 0) {
            target.updateFee(requirementReqDto.getFee());
        }

        if (!requirementReqDto.getMoveInDate().isEqual(target.getMoveInDate())) {
            target.updateMoveInDate(requirementReqDto.getMoveInDate());
        }

        if (!requirementReqDto.getHashtag().equals(target.getHashtag())) {
            target.updateHashtag(requirementReqDto.getHashtag());
        }

        log.info("[in Service][target]: " + target.toString());

        return mapper.fromEntityToRespDto(target);
    }

    /**
     * delete: 조건 삭제
     * @param id
     * @return 삭제 여부 반환
     */
    public boolean delete(String id) {
        log.info("[in service]: {}", id);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.info("** target was deleted.");
            return true;
        } else {
            log.info("** target didnt existed.");
            return false;
        }
    }
}
