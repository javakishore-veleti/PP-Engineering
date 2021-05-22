package jk.pp.engg.foundations.common.service.core.pubsub;

import java.util.List;

import org.javatuples.Pair;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubTopic;

public interface PubSubProducerService<K extends PubSubKey, V extends PubSubMessage> {

	void publishMessage(Pair<K, V> msgKeyAndVal, PubSubTopic pubSubTopic) throws Exception;

	public void publishMessages(List<Pair<K, V>> msgKeyAndVals, PubSubTopic pubSubTopic) throws Exception;

}
