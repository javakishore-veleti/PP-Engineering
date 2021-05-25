package jk.pp.engg.foundations.common.service.core;

import javax.annotation.PostConstruct;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jk.pp.engg.foundations.common.core.pubsub.PubSubConfig;
import jk.pp.engg.foundations.common.core.pubsub.PubSubConsumerInitiator;
import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;
import jk.pp.engg.foundations.common.core.pubsub.PubSubTopic;
import jk.pp.engg.foundations.common.core.util.AppDBConstants;
import jk.pp.engg.foundations.common.core.util.AppGlobalCtxAware;
import jk.pp.engg.foundations.common.core.util.AppGlobalObjects;
import jk.pp.engg.foundations.common.core.util.AppGlobalProperties;
import jk.pp.engg.foundations.common.core.util.FileUtil;
import jk.pp.engg.foundations.common.dao.core.AppCrudDAO;
import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import jk.pp.engg.foundations.common.domain.core.CrudResultDTO;
import jk.pp.engg.foundations.common.domain.core.DomainCrudDTO;
import jk.pp.engg.foundations.common.service.core.pubsub.PubSubConsumerService;
import jk.pp.engg.foundations.common.service.core.pubsub.PubSubProducerService;
import lombok.Data;

@Data
public abstract class AppCrudServiceImpl<T extends BaseDomain, DTO extends DomainCrudDTO<T>>
		implements AppCrudService<T, DTO>, PubSubConsumerInitiator {

	@Autowired
	private AppGlobalCtxAware ctxAware;

	@Autowired
	private AppGlobalProperties globalProps;

	@Autowired
	private AppGlobalObjects globalObjs;

	protected Boolean enableCrudEventsToPubSub = Boolean.FALSE;
	protected Boolean enableCrudEventsPubSubConsumer = Boolean.FALSE;

	protected String pubSubToipcsConfigJson = null;
	protected PubSubConfig pubSubConfig;
	protected String pubSubConfigJsonKey;

	private AppCrudDAO<T> crudDAO = null;

	private String crudServiceImplRefId;
	private String baseDomaniClassName;

	public void setCrudServiceImplRefId(String crudServiceImplRefId) {
		this.crudServiceImplRefId = crudServiceImplRefId;
	}

	public void setDomainClassName(String domainClassName) {
		this.baseDomaniClassName = domainClassName;
	}

	@Override
	public String getDomainClassName() {
		return this.baseDomaniClassName;
	}

	@PostConstruct
	public void postContruct() throws Exception {

		System.out.println("crudServiceImplRefId -> " + this.crudServiceImplRefId);
		System.out.println("postContruct pubSubToipcsGlobalEnabled -> " + this.globalProps.pubSubToipcsGlobalEnabled);
		System.out.println("postContruct pubSubToipcsGlobalJsonKey -> " + this.globalProps.pubSubToipcsGlobalJsonKey);
		System.out.println("postContruct crudServiceImplRefId -> " + this.crudServiceImplRefId);

		if (this.globalProps.pubSubToipcsGlobalEnabled) {

			this.enableCrudEventsToPubSub = Boolean.TRUE;
			this.pubSubToipcsConfigJson = this.globalProps.pubSubToipcsGlobalJson;

			if (this.globalProps.pubSubToipcsGlobalJsonKey == null
					|| this.globalProps.pubSubToipcsGlobalJsonKey.trim().length() == 0) {
				this.pubSubConfigJsonKey = this.crudServiceImplRefId;
			} else {
				this.pubSubConfigJsonKey = this.globalProps.pubSubToipcsGlobalJsonKey;
			}

			this.initializePubSubConfigs();
		}

		System.out.println("pubSubToipcsGlobalConsumerEnabled " + this.globalProps.pubSubToipcsGlobalConsumerEnabled);

		if (this.globalProps.pubSubToipcsGlobalConsumerEnabled) {
			this.enableCrudEventsPubSubConsumer = Boolean.TRUE;
		}

		System.out.println("enableCrudEventsPubSubConsumer " + enableCrudEventsPubSubConsumer);
		if (this.enableCrudEventsPubSubConsumer) {
			this.globalObjs.registerPubSubConsumerInitHandler(this.crudServiceImplRefId, this);
		}
	}

	public void initializePubSubConfigs() throws Exception {

		System.out.println("initializePubSubConfigs enableCrudEventsToPubSub -> " + this.enableCrudEventsToPubSub);

		if (enableCrudEventsToPubSub == Boolean.FALSE && this.enableCrudEventsPubSubConsumer == Boolean.FALSE) {

			System.out.println("initializePubSubConfigs Exiting so no messages will be published");
			return;
		}

		this.pubSubConfig = FileUtil.loadJsonAsPubSubConfig(pubSubToipcsConfigJson, pubSubConfigJsonKey);
		if (this.pubSubConfig != null) {
			this.pubSubConfig.convertTopicsToUsedWhenMap();
		}
	}

	public void setCrudDAO(AppCrudDAO<T> crudDAO) {
		this.crudDAO = crudDAO;
	}

	public AppCrudDAO<T> getCrudDAO() {
		return this.crudDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CrudResultDTO<T> create(DTO crudDTO) throws Exception {
		CrudResultDTO<T> result = this.crudDAO.create(crudDTO);
		result.setDomainName(this.baseDomaniClassName);

		this.checkPubSubAndPublish(AppDBConstants.CRUD_BIT_CREATE, result);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CrudResultDTO<T> update(DTO crudDTO) throws Exception {
		CrudResultDTO<T> result = this.crudDAO.update(crudDTO);
		result.setDomainName(this.baseDomaniClassName);

		this.checkPubSubAndPublish(AppDBConstants.CRUD_BIT_UPDATE, result);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CrudResultDTO<T> deleteByPK(Long pk) throws Exception {
		CrudResultDTO<T> result = this.crudDAO.deleteByPK(pk);
		result.setDomainName(this.baseDomaniClassName);

		this.checkPubSubAndPublish(AppDBConstants.CRUD_BIT_DELETE, result);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CrudResultDTO<T> deleteAll(DTO crudDTO) throws Exception {

		CrudResultDTO<T> result = this.crudDAO.deleteAll(crudDTO);
		result.setDomainName(this.baseDomaniClassName);

		return result;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public CrudResultDTO<T> findByPK(Long pk) throws Exception {

		CrudResultDTO<T> result = this.crudDAO.findByPK(pk);
		result.setDomainName(this.baseDomaniClassName);

		return result;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public CrudResultDTO<T> getAll(DTO crudDTO) throws Exception {

		CrudResultDTO<T> result = null;
		if (crudDTO != null) {

			PageRequest pageRequest = PageRequest.of(crudDTO.getPaginiationPageNo(), 200);
			result = this.crudDAO.getAll(crudDTO, pageRequest);
		} else {
			result = this.crudDAO.getAll(crudDTO);
		}

		if (result != null) {
			result.setDomainName(this.baseDomaniClassName);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public CrudResultDTO<T> getAllByCriteria(DTO crudDTO) throws Exception {

		CrudResultDTO<T> result = this.crudDAO.getAllByCriteria(crudDTO);
		result.setDomainName(this.baseDomaniClassName);

		return result;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long getDomainCount() throws Exception {
		return this.crudDAO.getDomainCount();
	}

	private void checkPubSubAndPublish(Integer crudEvent, CrudResultDTO<T> resultDTO) throws Exception {

		System.out.println("enableCrudEventsToPubSub -> " + enableCrudEventsToPubSub);
		System.out.println("this.pubSubConfig -> " + this.pubSubConfig);
		if (enableCrudEventsToPubSub == Boolean.FALSE || this.pubSubConfig == null) {
			return;
		}

		PubSubTopic pubSubTopic = null;
		PubSubProducerService<PubSubKey, PubSubMessage, PubSubResult> producerSvc = null;
		String eventType = null;

		System.out.println("crudEvent -> " + crudEvent);

		switch (crudEvent) {
		case 1:
			pubSubTopic = this.pubSubConfig.getPubSubTopicForUsedWhen("CREATE");
			producerSvc = this.getPubSubProducerSvc(pubSubTopic);
			eventType = "CREATE";
			break;
		case 3:
			pubSubTopic = this.pubSubConfig.getPubSubTopicForUsedWhen("UPDATE");
			producerSvc = this.getPubSubProducerSvc(pubSubTopic);
			eventType = "UPDATE";
			break;
		case 5:
			pubSubTopic = this.pubSubConfig.getPubSubTopicForUsedWhen("DELETE");
			producerSvc = this.getPubSubProducerSvc(pubSubTopic);
			eventType = "DELETE";
			break;
		case 7:
			pubSubTopic = this.pubSubConfig.getPubSubTopicForUsedWhen("DELETE_ALL");
			producerSvc = this.getPubSubProducerSvc(pubSubTopic);
			eventType = "DELETE_ALL";
			break;
		default:
			break;
		}

		System.out.println("producerSvc -> " + producerSvc);

		if (producerSvc != null) {

			PubSubKey key = new PubSubKey();
			key.setPk(resultDTO.getPk());
			key.setEventType(eventType);
			key.setDomainName(resultDTO.getDomainName());

			PubSubMessage value = new PubSubMessage();
			value.setPk(resultDTO.getPk());

			producerSvc.publishMessage(pubSubTopic.getTopic(), new Pair<PubSubKey, PubSubMessage>(key, value),
					pubSubTopic, null);
		}
	}

	@SuppressWarnings("unchecked")
	private PubSubProducerService<PubSubKey, PubSubMessage, PubSubResult> getPubSubProducerSvc(
			PubSubTopic pubSubTopic) {

		if (pubSubTopic == null) {
			return null;
		}

		return this.ctxAware.appCtx.getBean(pubSubTopic.getAdapterProducerBeanId(), PubSubProducerService.class);
	}

	@Override
	public void initiatePubSubConsumers() throws Exception {
		System.out.println("initiatePubSubConsumers Entered");

		if (this.enableCrudEventsPubSubConsumer == Boolean.FALSE || this.pubSubConfig == null) {

			System.out.println("initiatePubSubConsumers Exiting");

			return;
		}

		PubSubTopic pubSubTopic = this.pubSubConfig.getPubSubTopicForUsedWhen("MESSAGE-CONSUMER");
		if (pubSubTopic != null) {
			@SuppressWarnings("rawtypes")
			PubSubConsumerService consumer = this.ctxAware.appCtx.getBean(pubSubTopic.getAdapterConsumerBeanId(),
					PubSubConsumerService.class);

			System.out.println("initiatePubSubConsumers Invoking consumer.consumeMessages(pubSubTopic)");
			consumer.consumeMessages(pubSubTopic);
		}

		System.out.println("initiatePubSubConsumers Exiting from method");
	}

}
