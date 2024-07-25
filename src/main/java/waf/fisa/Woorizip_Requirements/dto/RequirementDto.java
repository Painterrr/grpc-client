package waf.fisa.Woorizip_Requirements.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import waf.fisa.Woorizip_Requirements.entity.Requirement;

import java.time.LocalDate;

@Getter
public class RequirementDto {
    private String id;

    private String accountId;

    private String phone;

    private String location;

    private String buildingType;

    private int fee;

    private LocalDate moveInDate;

    private String hashtag;

    @QueryProjection
    public RequirementDto(String id, String accountId, String phone, String location, String buildingType,
                        int fee, LocalDate moveInDate, String hashtag) {
        this.id = id;
        this.accountId = accountId;
        this.phone = phone;
        this.location = location;
        this.buildingType = buildingType;
        this.fee = fee;
        this.moveInDate = moveInDate;
        this.hashtag = hashtag;
    }
}