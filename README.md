# Spring Boot Demo

## 依赖

### Mysql

#### 建表SQL
```sql
CREATE TABLE `active_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `size` int(11) NOT NULL,
  `score` double(20,2) NOT NULL,
  `type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9738 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
```

#### 数据库配置：
开发环境：springboot-web/src/main/resources/config/dev/application.yml

### Redis
开发环境：springboot-web/src/main/resources/config/dev/application.yml

### 其他配置
开发环境的配置分别在
springboot-web/src/main/filters/filter-dev.properties
springboot-web/src/main/resources/bootstrap.yml
springboot-web/src/main/resources/config/dev/application.yml

请大家酌情修改

## 启动方式

在IDE中，直接运行springboot-web项目的WebApplication类即可运行


## 备注

项目项目供大家参考，正在完善当中