package com.baomidou.mybatisplus.generator.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: dengxin.chen
 * @date: 2020-05-22 22:01
 * @description:
 */
@Getter
public enum DataSourceEnum {

    LOCAL(1,
          "本地数据库",
          new ConnectionConfig("jdbc:mysql://localhost:3306/tmpDataBaseName?useUnicode=true&characterEncoding=utf8",
                               "root",
                               "root@2020")),

    UNKOWN(999, "位置配置", null);

    DataSourceEnum(int value, String desc, ConnectionConfig connectionConfig) {
        this.value = value;
        this.desc = desc;
        this.connectionConfig = connectionConfig;
    }

    private int value;
    private String desc;
    private ConnectionConfig connectionConfig;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConnectionConfig {
        private String url;
        private String userName;
        private String passWord;
    }


    public static ConnectionConfig getConnectionConfig(int value) {
        for (DataSourceEnum source : DataSourceEnum.values()) {
            if (source.getValue() == value) {
                return source.connectionConfig;
            }
        }
        return UNKOWN.connectionConfig;
    }


}
