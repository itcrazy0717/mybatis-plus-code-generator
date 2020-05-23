package com.baomidou.mybatisplus.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-22 21:58
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceConfig {
    private String url;
    private String userName;
    private String passWord;
}
