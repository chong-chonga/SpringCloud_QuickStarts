package com.huanglexin.springcloud.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author LeXin Huang
 * @date 2021年06月24日 10:43
 */
@Configuration
public class MybatisPlusConfig {

    public static class MetaObjectHandlerOnInsert implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            this.setFieldValByName("createTime", new Date(), metaObject);
            this.setFieldValByName("modifyTime", new Date(), metaObject);
        }

        /**
         * 遵循标准规范设计表时用到
         * @param metaObject 元实体
         */
        @Override
        public void updateFill(MetaObject metaObject) {
            this.setFieldValByName("modifyTime", new Date(), metaObject);
        }

    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandlerOnInsert();
    }

}
