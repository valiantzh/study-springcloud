package com.study.springcloud.web;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author valiantzh
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
}
