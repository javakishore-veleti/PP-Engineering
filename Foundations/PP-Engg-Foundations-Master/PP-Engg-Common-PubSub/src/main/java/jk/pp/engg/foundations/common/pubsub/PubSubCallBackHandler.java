package jk.pp.engg.foundations.common.pubsub;

import java.util.List;
import java.util.Map;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;

public interface PubSubCallBackHandler<KEY extends PubSubKey, MSG extends PubSubMessage, RES extends PubSubResult> {

	default RES callback(KEY key, MSG message, PubSubResult result) throws Exception {
		return null;
	}

	default RES callback(Map<KEY, List<MSG>> keysMsgs, PubSubResult result) throws Exception {
		return null;
	}

}
