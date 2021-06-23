package cn.weforward.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.weforward.common.util.StringUtil;
import cn.weforward.framework.ext.WeforwardService;
import cn.weforward.protocol.client.proxy.ServiceInvokerProxyFactory;

/**
 * weforward服务初始化
 * 
 * @author daibo
 *
 */
@ConditionalOnClass(value = WeforwardService.class)
@Configuration
public class WeforwardAutoConfiguration {
	@Value(value = "${weforward.name:my_weforward}")
	private String m_Name;
	@Value(value = "${weforward.host:*}")
	private String m_Host = "*";
	@Value(value = "${weforward.path:}")
	private String m_Path;
	@Value(value = "${weforward.port:15000}")
	private int m_Port;
	@Value(value = "${weforward.threads:50}")
	private int m_Threads;
	@Value(value = "${weforward.serverid:x00ff}")
	private String m_Serverid;
	@Value(value = "${weforward.gatewayUrl:}")
	private String m_GatewayUrl;
	@Value(value = "${weforward.apiUrl:}")
	private String m_ApiUrl;
	@Value(value = "${weforward.service.accessId:}")
	private String m_ServiceAccessId;
	@Value(value = "${weforward.service.accessKey:}")
	private String m_ServiceAccessKey;
	@Value(value = "${weforward.meter.url:}")
	private String m_MeterUrl;
	@Value(value = "${weforward.trace.url:}")
	private String m_TraceUrl;
	@Value(value = "${weforward.runningId:}")
	private String m_RunningId;
	@Value(value = "${weforward.innerMethodEnable:true}")
	private boolean m_InnerMethodEnable = true;
	@Value(value = "${weforward.methodsAwareEnable:true}")
	private boolean m_MethodsAwareEnable = true;
	@Value(value = "${weforward.forwardEnable:true}")
	private boolean m_ForwardEnable = true;
	@Value(value = "${weforward.version:}")
	private String m_Version;
	@Value(value = "${weforward.compatibleVersion:}")
	private String m_CompatibleVersion;
	@Value(value = "${weforward.requestMaxSize:0}")
	protected int m_RequestMaxSize;
	@Value(value = "${weforward.maxHttpSize:0}")
	protected int m_MaxHttpSize;

	@Bean(destroyMethod = "destroy")
	WeforwardService weforwardService() throws Exception {
		WeforwardService service = new WeforwardService(m_Name, m_Host, m_Port, m_Path, m_Threads);
		service.setNo(m_Serverid);
		service.setAccessId(m_ServiceAccessId);
		service.setAccessKey(m_ServiceAccessKey);
		service.setMeterRegistryUrl(m_MeterUrl);
		service.setTraceRegisterUrl(m_TraceUrl);
		service.setInnerMethodEnabled(m_InnerMethodEnable);
		service.setMethodsAwareEnabled(m_MethodsAwareEnable);
		service.setForwardEnable(m_ForwardEnable);
		if (!StringUtil.isEmpty(m_RunningId)) {
			service.setRunningId(m_RunningId);
		}
		if (!StringUtil.isEmpty(m_Version)) {
			service.setVersion(m_Version);
		}
		if (!StringUtil.isEmpty(m_CompatibleVersion)) {
			service.setCompatibleVersion(m_CompatibleVersion);
		}
		if (m_RequestMaxSize > 0) {
			service.setRequestMaxSize(m_RequestMaxSize);
		}
		if (m_MaxHttpSize > 0) {
			service.setMaxHttpSize(m_MaxHttpSize);
		}
		String url = m_GatewayUrl;
		if (StringUtil.isEmpty(url)) {
			url = m_ApiUrl;
		}
		service.setGatewayUrl(m_GatewayUrl);
		return service;
	}

	@Bean
	ServiceInvokerProxyFactory serviceInvokerProxyFactory() {
		return new ServiceInvokerProxyFactory(m_ApiUrl, m_ServiceAccessId, m_ServiceAccessKey);
	}
}
