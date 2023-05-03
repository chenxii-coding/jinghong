package com.chenxii.jinghong.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoNo extends BaseEntity implements Serializable {

    private String id;

    private String type;

    private int no;

    public AutoNo(String type) {
        this.type = type;
    }
}
