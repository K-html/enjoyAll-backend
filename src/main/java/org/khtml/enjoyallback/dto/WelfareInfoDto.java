package org.khtml.enjoyallback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WelfareInfoDto {
    @JsonProperty("WLFARE_INFO_ID")
    private String welfareInfoId;

    @JsonProperty("WLFARE_INFO_NM")
    private String welfareInfoName;

    @JsonProperty("RETURN_STR")
    private String returnStr;

    @JsonProperty("WLFARE_INFO_OUTL_CN")
    private String welfareInfoOutlCn;

    @JsonProperty("ADDR")
    private String address;

    @JsonProperty("ENFC_BGNG_YMD")
    private String enforcementBeginDate;

    @JsonProperty("ENFC_END_YMD")
    private String enforcementEndDate;

    @JsonProperty("BIZ_CHR_INST_NM")
    private String businessChargeInstitutionName;

    @JsonProperty("ONLINEYN")
    private String onlineYn;

    @JsonProperty("WLFARE_GDNC_TRGT_KCD")
    private String welfareGuidanceTargetKcd;

    @JsonProperty("RPRS_CTADR")
    private String representativeContactAddress;

    @JsonProperty("CVLWL_REG_SCD_NM")
    private String civilRegistrationStatusName;

    @JsonProperty("CVL_PROGRSS_STATUS")
    private String civilProgressStatus;

    @JsonProperty("TAG_NM")
    private String tagNm;
}
