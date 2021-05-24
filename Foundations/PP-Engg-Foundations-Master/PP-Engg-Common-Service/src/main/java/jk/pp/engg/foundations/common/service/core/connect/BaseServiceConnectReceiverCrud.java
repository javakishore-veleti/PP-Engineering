package jk.pp.engg.foundations.common.service.core.connect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jk.pp.engg.foundations.common.core.util.AppGlobalCtxAware;
import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import jk.pp.engg.foundations.common.domain.core.DomainCrudDTO;
import jk.pp.engg.foundations.common.domain.core.connect.ServiceConnectReq;
import jk.pp.engg.foundations.common.domain.core.connect.ServiceConnectResult;

public abstract class BaseServiceConnectReceiverCrud<T extends ServiceConnectReq, DOMAIN extends BaseDomain, DTO extends DomainCrudDTO<DOMAIN>>
		implements ServiceConnectReceiver<T, ServiceConnectResult> {

	@Autowired
	private AppGlobalCtxAware ctxAware;

	private Map<String, String> domainTypeAndReceiverBeanIds = new HashMap<>();

	public void setAppCrudService(Map<String, String> domainTypeAndReceiverBeanIds) {
		this.domainTypeAndReceiverBeanIds = domainTypeAndReceiverBeanIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceConnectResult command(T request) throws Exception {
		String beanId = this.domainTypeAndReceiverBeanIds.get(request.getDomainType());
		return this.ctxAware.appCtx.getBean(beanId, ServiceConnectReceiver.class).command(request);
	}
}
