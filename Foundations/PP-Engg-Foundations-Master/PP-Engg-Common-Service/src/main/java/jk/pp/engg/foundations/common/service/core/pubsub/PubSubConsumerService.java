package jk.pp.engg.foundations.common.service.core.pubsub;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;
import jk.pp.engg.foundations.common.core.pubsub.PubSubTopic;

public interface PubSubConsumerService<K extends PubSubKey, V extends PubSubMessage, R extends PubSubResult> {

	R consumeMessages(PubSubTopic pubSubTopic) throws Exception;

}
