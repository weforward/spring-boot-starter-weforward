package cn.weforward.starter;

import org.springframework.context.annotation.Bean;

import cn.weforward.boot.CloudPropertyPlaceholderConfigurer;

/**
 * 云配置初始化
 * 
 * @author daibo
 *
 */
public class CloudPropertyConfiguration {
	@Bean
	CloudPropertyPlaceholderConfigurer cloudProperty() {
		return new CloudPropertyPlaceholderConfigurer();
	}
}
