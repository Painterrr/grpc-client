package waf.fisa.Woorizip_Requirements.dto;

import lombok.*;
import waf.fisa.Woorizip_Requirements.entity.Requirement;


import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RequirementReqDto {

    private String id;

    private String accountId;

    private String phone;

    private String location;

    private String buildingType;

    private int fee;

    private LocalDate moveInDate;

    private String hashtag;

    public RequirementReqDto(String id, String accountId, String location, String buildingType, int fee, LocalDate moveInDate, String hashtag) {
        this.id = id;
        this.accountId = accountId;
        this.location = location;
        this.buildingType = buildingType;
        this.fee = fee;
        this.moveInDate = moveInDate;
        this.hashtag = hashtag;
    }
}