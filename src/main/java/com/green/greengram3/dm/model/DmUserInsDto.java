package com.green.greengram3.dm.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class DmUserInsDto {
    private int idm;
    private int iuser;

}
