package jk.pp.engg.foundations.common.pubsub;

import java.util.List;
import java.util.Map;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;

public interface AppPubSubPublisher<KEY extends PubSubKey, MSG extends PubSubMessage, RES extends PubSubResult> {

	default RES publish(String topicName, KEY key, MSG message, PubSubCallBackHandler<KEY, MSG, RES> callBackHandler)
			throws Exception {
		return null;
	}

	default RES publishBatch(String topicName, Map<KEY, List<MSG>> keysMsgs,
			PubSubCallBackHandler<KEY, MSG, RES> callBackHandler) throws Exception {
		return null;
	}

	default void stopPublishing(String topic) throws Exception {
		return;
	}

	default void initializePublisher(String topic) throws Exception {

	}

}
