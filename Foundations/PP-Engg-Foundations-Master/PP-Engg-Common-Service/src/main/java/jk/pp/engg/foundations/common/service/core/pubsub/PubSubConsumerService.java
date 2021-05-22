package jk.pp.engg.foundations.common.service.core.pubsub;

import java.util.List;

import org.javatuples.Pair;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;

public interface PubSubConsumerService<K extends PubSubKey, V extends PubSubMessage> {

	void publishMessage(Pair<K, V> msgKeyAndVal) throws Exception;

	public void publishMessages(List<Pair<K, V>> msgKeyAndVals) throws Exception;

}
