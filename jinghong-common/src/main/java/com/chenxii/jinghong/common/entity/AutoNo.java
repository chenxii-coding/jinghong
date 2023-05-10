package com.chenxii.jinghong.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
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
