package jk.pp.engg.foundations.common.pubsub;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;
import jk.pp.engg.foundations.common.core.util.AppGlobalCtxAware;
import lombok.Data;

// CAUTION: Do not define this class as Spring @Component
// It is as per design that this class should be instantiated by the MicroServices for each Message topic
@Data
public class IntermittenProducerImpl<KEY extends PubSubKey, MSG extends PubSubMessage, RES extends PubSubResult>
		implements AppPubSubPublisher<KEY, MSG, RES> {

	@Autowired
	private AppGlobalCtxAware ctxAWare;

	private String techAdapterBeanId;

	@SuppressWarnings("unchecked")
	public RES publish(String topicName, KEY key, MSG message, PubSubCallBackHandler<KEY, MSG, RES> callBackHandler)
			throws Exception {

		if (this.techAdapterBeanId != null) {
			this.ctxAWare.appCtx.getBean(this.techAdapterBeanId, AppPubSubPublisher.class).publish(topicName, key,
					message, callBackHandler);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public RES publishBatch(String topicName, Map<KEY, List<MSG>> keysMsgs,
			PubSubCallBackHandler<KEY, MSG, RES> callBackHandler) throws Exception {

		if (this.techAdapterBeanId != null) {
			this.ctxAWare.appCtx.getBean(this.techAdapterBeanId, AppPubSubPublisher.class).publishBatch(topicName,
					keysMsgs, callBackHandler);
		}

		return null;
	}
}
