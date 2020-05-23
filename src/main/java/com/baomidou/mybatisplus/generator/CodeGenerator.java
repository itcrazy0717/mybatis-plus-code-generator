package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.dto.CodeTemplateInfo;
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
         * 数据源：
         * 1-本地
         * 可根据情况进行添加
         */
        int dataSource = 1;
        String tableName = "test";
        String dataBaseName = "dev-test";
        String packageName = "com.cdx.test";

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
