package jk.pp.engg.foundations.common.service.core;

import javax.annotation.PostConstruct;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.dto.CrudResultDTO;
import jk.pp.engg.foundations.common.core.dto.DomainCrudDTO;
import jk.pp.engg.foundations.common.core.pubsub.PubSubConfig;
import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubTopic;
import jk.pp.engg.foundations.common.core.util.AppGlobalCtxAware;
import jk.pp.engg.foundations.common.core.util.FileUtil;
import jk.pp.engg.foundations.common.dao.core.AppCrudDAO;
import jk.pp.engg.foundations.common.service.core.pubsub.PubSubProducerService;
import lombok.Data;

@Data
public abstract class AppCrudServiceImpl<T extends IDomain, DTO extends DomainCrudDTO<T>>
		implements AppCrudService<T, DTO> {

	@Autowired
	private AppGlobalCtxAware ctxAware;

	public static final Integer CRUD_BIT_CREATE = 1;
	public static final Integer CRUD_BIT_UPDATE = 3;
	public static final Integer CRUD_BIT_DELETE = 5;
	public static final Integer CRUD_BIT_DELETE_ALL = 7;

	@Value("${pp.platform.streams.pubsub.crud.global.enabled: false}")
	private Boolean pubSubToipcsGlobalEnabled = Boolean.FALSE;

	@Value("${pp.platform.streams.pubsub.crud.global.jsonfile: ''}")
	private String pubSubToipcsGlobalJson;

	@Value("${pp.platform.streams.pubsub.crud.global.jsonkey: ''}")
	private String pubSubToipcsGlobalJsonKey;

	protected Boolean enableCrudEventsToPubSub = Boolean.FALSE;
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

		if (this.pubSubToipcsGlobalEnabled) {

			this.enableCrudEventsToPubSub = Boolean.TRUE;
			this.pubSubToipcsConfigJson = this.pubSubToipcsGlobalJson;

			if (this.pubSubToipcsGlobalJsonKey == null || this.pubSubToipcsGlobalJsonKey.trim().length() == 0) {
				this.pubSubConfigJsonKey = this.crudServiceImplRefId;
			} else {
				this.pubSubConfigJsonKey = this.pubSubToipcsGlobalJsonKey;
			}

			this.initializePubSubConfigs();
		}
	}

	public void initializePubSubConfigs() throws Exception {

		if (enableCrudEventsToPubSub == Boolean.FALSE) {
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

		this.checkPubSubAndPublish(CRUD_BIT_CREATE, result);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CrudResultDTO<T> update(DTO crudDTO) throws Exception {
		CrudResultDTO<T> result = this.crudDAO.update(crudDTO);
		result.setDomainName(this.baseDomaniClassName);

		this.checkPubSubAndPublish(CRUD_BIT_UPDATE, result);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CrudResultDTO<T> deleteByPK(Long pk) throws Exception {
		CrudResultDTO<T> result = this.crudDAO.deleteByPK(pk);
		result.setDomainName(this.baseDomaniClassName);

		this.checkPubSubAndPublish(CRUD_BIT_DELETE, result);

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

		if (enableCrudEventsToPubSub == Boolean.FALSE || this.pubSubConfig == null) {
			return;
		}

		PubSubTopic pubSubTopic = null;
		PubSubProducerService<PubSubKey, PubSubMessage> producerSvc = null;
		String eventType = null;

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

		if (producerSvc != null) {

			PubSubKey key = new PubSubKey();
			key.setPk(resultDTO.getPk());
			key.setEventType(eventType);
			key.setDomainName(resultDTO.getDomainName());

			PubSubMessage value = new PubSubMessage();
			value.setPk(resultDTO.getPk());

			producerSvc.publishMessage(new Pair<PubSubKey, PubSubMessage>(key, value), pubSubTopic);
		}
	}

	@SuppressWarnings("unchecked")
	private PubSubProducerService<PubSubKey, PubSubMessage> getPubSubProducerSvc(PubSubTopic pubSubTopic) {

		if (pubSubTopic == null) {
			return null;
		}

		return this.ctxAware.appCtx.getBean(pubSubTopic.getAdapterProducerBeanId(), PubSubProducerService.class);
	}

}
