package waf.fisa.Woorizip_Requirements.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import waf.fisa.Woorizip_Requirements.dto.RequirementReqDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementRespDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Requirement {

    /**
     * 조건 ID
     */
    @Id
    private String id;

    /**
     * Requirements 등록한 의뢰인
     */
    private String accountId;

    /**
     * 의뢰인 폰(gRPC로 받아옴)
     */
    @Column(nullable = false)
    private String phone;

    /**
     * 소재지
     */
    @Column(nullable = false)
    private String location;

    /**
     * 건물 유형
     */
    @Column(nullable = false)
    private String buildingType;

    /**
     * 월세 상한가
     */
    @Column(nullable = false)
    private int fee;

    /**
     * 입주 가능일
     */
    @Column(nullable = false)
    private LocalDate moveInDate;

    /**
     * 부가 옵션
     */
    private String hashtag;

    @Builder
    public Requirement(String id, String accountId, String phone, String location, String buildingType, int fee, LocalDate moveInDate,
                     String hashtag) {
        this.id = id;
        this.accountId = accountId;
        this.phone = phone;
        this.location = location;
        this.buildingType = buildingType;
        this.fee = fee;
        this.moveInDate = moveInDate;
        this.hashtag = hashtag;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateLocation(String location) {
        this.location = location;
    }

    public void updateBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public void updateFee(int fee) {
        this.fee = fee;
    }

    public void updateMoveInDate(LocalDate moveInDate) {
        this.moveInDate = moveInDate;
    }

    public void updateHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

}