package com.baomidou.mybatisplus.generator.enums;

import java.util.Arrays;
import java.util.Objects;

import com.baomidou.mybatisplus.generator.utils.DataConnectionUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-22 22:01
 * @description:数据源枚举值
 */
@Getter
public enum DataSourceEnum {

    /**
     * 本地数据源配置
     */
    LOCAL(1,
          "本地数据库",
          new ConnectionConfig("jdbc:mysql://localhost:3306/" + DataConnectionUtil.PLACE_HOLDER_DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8",
                               "root",
                               "123456")),

    /**
     * 未知配置，可根据具体场景进行添加
     */
    UNKOWN(999, "未知配置", null);

    DataSourceEnum(int value, String desc, ConnectionConfig connectionConfig) {
        this.value = value;
        this.desc = desc;
        this.connectionConfig = connectionConfig;
    }

    /**
     * 枚举value
     */
    private int value;

    /**
     * 描述
     */
    private String desc;

    /**
     * 数据库连接配置
     */
    private ConnectionConfig connectionConfig;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConnectionConfig {
        /**
         * 数据库连接url
         */
        private String url;

        /**
         * 用户名
         */
        private String userName;

        /**
         * 密码
         */
        private String passWord;
    }

    /**
     * 通过枚举值获取数据库连接
     * by dengxin.chen
     *
     * @param dataSourceEnum 数据库源枚举
     * @return
     */
    public static ConnectionConfig getConnectionConfig(DataSourceEnum dataSourceEnum) {
        DataSourceEnum result = Arrays.stream(DataSourceEnum.values())
                                      .filter(e -> Objects.equals(e, dataSourceEnum))
                                      .findFirst()
                                      .orElseGet(null);
        return Objects.nonNull(result) ? result.connectionConfig : null;
    }
}
