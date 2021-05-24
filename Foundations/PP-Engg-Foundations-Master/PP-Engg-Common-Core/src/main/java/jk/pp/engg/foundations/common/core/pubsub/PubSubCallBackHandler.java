package jk.pp.engg.foundations.common.core.pubsub;

import java.util.List;

import org.javatuples.Pair;

public interface PubSubCallBackHandler<KEY extends PubSubKey, MSG extends PubSubMessage, RES extends PubSubResult> {

	default RES callback(Pair<PubSubKey, PubSubMessage> msgKeyAndVal, PubSubResult result) throws Exception {
		return null;
	}

	default RES callback(List<Pair<PubSubKey, PubSubMessage>> msgKeyAndVals, PubSubResult result) throws Exception {
		return null;
	}

}
