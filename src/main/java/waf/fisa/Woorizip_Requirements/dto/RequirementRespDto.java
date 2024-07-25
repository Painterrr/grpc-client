package waf.fisa.Woorizip_Requirements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import waf.fisa.Woorizip_Requirements.entity.Requirement;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequirementRespDto {

    private String id;

    private String accountId;

    private String phone;

    private String location;

    private String buildingType;

    private int fee;

    private LocalDate moveInDate;

    private String hashtag;
}
