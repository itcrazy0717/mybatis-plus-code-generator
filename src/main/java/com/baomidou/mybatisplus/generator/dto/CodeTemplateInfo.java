package com.baomidou.mybatisplus.generator.dto;

import com.baomidou.mybatisplus.generator.enums.DataSourceEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-22 22:35
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeTemplateInfo {

    /**
     * 数据源类型
     *
     * @see com.baomidou.mybatisplus.generator.enums.DataSourceEnum
     */
    private DataSourceEnum dataSource;

    /**
     * 数据表名
     */
    private String tableName;

    /**
     * 数据库名
     */
    private String dataBaseName;

    /**
     * 生成代码包名
     */
    private String packageName;
}
