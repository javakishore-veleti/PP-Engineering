package jk.pp.engg.foundations.common.service.core.pubsub;

import java.util.List;

import org.javatuples.Pair;

import jk.pp.engg.foundations.common.core.pubsub.PubSubCallBackHandler;
import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;
import jk.pp.engg.foundations.common.core.pubsub.PubSubTopic;

public interface PubSubProducerService<K extends PubSubKey, V extends PubSubMessage, R extends PubSubResult> {

	R publishMessage(String topic, Pair<K, V> msgKeyAndVal, PubSubTopic pubSubTopic,
			PubSubCallBackHandler<K, V, R> callBackHandler) throws Exception;

	R publishMessages(String topic, List<Pair<K, V>> msgKeyAndVals, PubSubTopic pubSubTopic,
			PubSubCallBackHandler<K, V, R> callBackHandler) throws Exception;

}
