package cn.weforward.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import cn.weforward.starter.CloudPropertyImportSelector;

/**
 * 启用云配置
 * 
 * @author daibo
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CloudPropertyImportSelector.class)
public @interface EnableCloudProperty {

}
