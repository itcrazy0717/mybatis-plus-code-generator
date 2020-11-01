package com.baomidou.mybatisplus.generator.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.dto.CodeTemplateInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.enums.DataSourceEnum;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2019-10-22 22:27
 * @description:代码生成工具
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeGeneratorUtil {

    /**
     * 获取自动生成器
     * by dengxin.chen
     *
     * @param codeTemplateInfo 代码模板信息
     * @return
     */
    public static AutoGenerator getAutoGenerator(CodeTemplateInfo codeTemplateInfo) {
        AutoGenerator autoGenerator = new AutoGenerator();
        // 设置全局配置
        autoGenerator.setGlobalConfig(generateGlobalConfig());
        // 设置数据库连接配置
        autoGenerator.setDataSource(generateDataSourceConfig(codeTemplateInfo.getDataSource(), codeTemplateInfo.getDataBaseName()));
        // 设置包名
        PackageConfig packageConfig = generatePackageConfig(codeTemplateInfo.getPackageName());
        autoGenerator.setPackageInfo(packageConfig);
        // 设置mapper路径
        autoGenerator.setCfg(generateInjectionConfig());
        // 其他配置
        setOtherConfig(autoGenerator, codeTemplateInfo.getTableName(), packageConfig);
        return autoGenerator;
    }

    /**
     * 生成数据源配置
     * by dengxin.chen
     *
     * @param dataSource   数据源配置
     * @param dataBaseName 数据库名
     * @return
     */
    private static DataSourceConfig generateDataSourceConfig(DataSourceEnum dataSource, String dataBaseName) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        DataSourceEnum.ConnectionConfig connectionConfig = DataConnectionUtil.getDataConnectionConfig(dataSource, dataBaseName);
        if (Objects.isNull(connectionConfig)) {
            throw new RuntimeException("数据库连接配置为空");
        }
        dataSourceConfig.setUrl(connectionConfig.getUrl());
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(connectionConfig.getUserName());
        dataSourceConfig.setPassword(connectionConfig.getPassWord());
        return dataSourceConfig;
    }

    /**
     * 生成全局配置
     * by dengxin.chen
     *
     * @return
     */
    private static GlobalConfig generateGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/code_generator/src/main/java");
        globalConfig.setAuthor("code_template");
        // 设置service接口文件的名称
        globalConfig.setServiceName("%sService");
        globalConfig.setOpen(false);
        // 删除之前模板文件
        deleteOldTemplateFile(projectPath);
        return globalConfig;
    }

    /**
     * 生成配置
     * by dengxin.chen
     *
     * @param packageName
     * @return
     */
    private static PackageConfig generatePackageConfig(String packageName) {
        PackageConfig packageConfig = new PackageConfig();
        // 暂时屏蔽模块名称
        // packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent(packageName);
        packageConfig.setEntity("domain");
        packageConfig.setMapper("dao");
        return packageConfig;
    }

    /**
     * 生成mapper文件配置信息
     * by dengxin.chen
     *
     * @return
     */
    private static InjectionConfig generateInjectionConfig() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        fileOutConfigs.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return System.getProperty("user.dir") + "/code_generator/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigs);
        return injectionConfig;
    }

    /**
     * 进行其他配置
     * by dengxin.chen
     *
     * @param autoGenerator
     * @param tableName
     * @param packageConfig
     */
    private static void setOtherConfig(AutoGenerator autoGenerator, String tableName, PackageConfig packageConfig) {
        // 使用自定义模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityTableFieldAnnotationEnable(false);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 不需要设置父类
        //        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        //        strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(tableName);
        //        strategy.setSuperEntityColumns("id");
        strategy.setEntitySerialVersionUID(false);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        autoGenerator.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！ 所以使用.ftl模板文件
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
    }

    /**
     * 删除之前模板文件
     *
     * @param path 路径
     */
    private static void deleteOldTemplateFile(String path) {
        File file = new File(path + "/code_generator/");
        boolean result = deleteDir(file);
        if (result) {
            System.out.println("旧代码模板文件删除成功！！！！！！");
            System.out.println("===========================================");
        } else {
            System.out.println("旧代码模板文件删除失败");
        }
    }

    /**
     * 删除文件
     * by dengxin.chen
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] listDir = dir.list();
            if (Objects.isNull(listDir)) {
                return true;
            }
            for (String childDir : listDir) {
                boolean success = deleteDir(new File(dir, childDir));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录空直接删除
        return dir.delete();
    }
}
