package com.easeschool.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity {

    private String data;
    private String reason;
    private String type;


}
