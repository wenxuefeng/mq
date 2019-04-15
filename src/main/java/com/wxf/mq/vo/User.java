package com.wxf.mq.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wxf
 * @date 2019/4/12
 * @description 简单描述
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -1063360907906799567L;
    private String name;
    private Integer age;
}
