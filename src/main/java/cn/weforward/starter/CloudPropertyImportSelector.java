package cn.weforward.starter;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 云配置选择器
 * 
 * @author daibo
 *
 */
public class CloudPropertyImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] { CloudPropertyConfiguration.class.getName() };
	}

}
