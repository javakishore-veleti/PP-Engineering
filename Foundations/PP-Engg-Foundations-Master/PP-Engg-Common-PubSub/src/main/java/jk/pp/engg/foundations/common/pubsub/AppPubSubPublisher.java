package jk.pp.engg.foundations.common.pubsub;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;

public interface AppPubSubPublisher<KEY extends PubSubKey, MSG extends PubSubMessage, RES extends PubSubResult> {

	RES publish(String topicName, KEY key, MSG message, PubSubCallBackHandler<KEY, MSG, RES> callBackHandler)
			throws Exception;
}
