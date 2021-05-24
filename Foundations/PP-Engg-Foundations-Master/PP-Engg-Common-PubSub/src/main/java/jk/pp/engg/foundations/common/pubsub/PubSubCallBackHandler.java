package jk.pp.engg.foundations.common.pubsub;

import jk.pp.engg.foundations.common.core.pubsub.PubSubKey;
import jk.pp.engg.foundations.common.core.pubsub.PubSubMessage;
import jk.pp.engg.foundations.common.core.pubsub.PubSubResult;

public interface PubSubCallBackHandler<KEY extends PubSubKey, MSG extends PubSubMessage, RES extends PubSubResult> {

	RES callback(KEY key, MSG message) throws Exception;
}
