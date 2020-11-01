package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.dto.CodeTemplateInfo;
import com.baomidou.mybatisplus.generator.enums.DataSourceEnum;
import com.baomidou.mybatisplus.generator.utils.CodeGeneratorUtil;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class CodeGenerator {

    /**
     * RUN THIS
     */
    public static void main(String[] args) {

        /**
         * 数据源
         * 可根据情况进行添加
         */
        DataSourceEnum dataSource = DataSourceEnum.LOCAL;
        String tableName = "user";
        String dataBaseName = "my_opt";
        String packageName = "com.practice.es";

        /**
         * 构建代码模板信息
         */
        CodeTemplateInfo codeTemplate = CodeTemplateInfo.builder()
                                                        .dataSource(dataSource)
                                                        .tableName(tableName)
                                                        .dataBaseName(dataBaseName)
                                                        .packageName(packageName)
                                                        .build();

        AutoGenerator autoGenerator = CodeGeneratorUtil.getAutoGenerator(codeTemplate);
        // 执行代码生成
        autoGenerator.execute();
        System.out.println("模板代码生成成功！！！！！！");
    }

}
