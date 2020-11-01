package com.baomidou.mybatisplus.generator.utils;

import java.util.Objects;

import com.baomidou.mybatisplus.generator.enums.DataSourceEnum;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-22 21:59
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataConnectionUtil {

    /**
     * 数据库名占位符
     */
    public static final String PLACE_HOLDER_DATABASE_NAME = "placeholderDataBaseName";

    /**
     * 获取数据源连接配置
     *
     * @param dataSource   数据源类型
     * @param dataBaseName 数据库名称
     * @return
     */
    public static DataSourceEnum.ConnectionConfig getDataConnectionConfig(DataSourceEnum dataSource, String dataBaseName) {
        DataSourceEnum.ConnectionConfig config = DataSourceEnum.getConnectionConfig(dataSource);
        if (Objects.nonNull(config)) {
            String url = config.getUrl().replace(PLACE_HOLDER_DATABASE_NAME, dataBaseName);
            config.setUrl(url);
            return config;
        }
        return null;
    }

}
