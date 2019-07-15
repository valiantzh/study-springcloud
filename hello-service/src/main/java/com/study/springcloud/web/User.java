package com.study.springcloud.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
