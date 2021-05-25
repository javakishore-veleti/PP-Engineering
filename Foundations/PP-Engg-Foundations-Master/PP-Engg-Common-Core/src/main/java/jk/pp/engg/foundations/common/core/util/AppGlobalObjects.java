package jk.pp.engg.foundations.common.core.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jk.pp.engg.foundations.common.core.pubsub.PubSubConsumerInitiator;

@Component
public class AppGlobalObjects {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppGlobalObjects.class);

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private Map<String, PubSubConsumerInitiator> pubSubConsumerInitHandlers = new HashMap<>();

	@Value("${pp.platform.common.executor.threads:3}")
	private Integer executeThreads = 3;

	public void registerPubSubConsumerInitHandler(String handlerRefId, PubSubConsumerInitiator initiator) {

		LOGGER.debug("PubSubConsumerInitHandler RefId " + handlerRefId);
		this.pubSubConsumerInitHandlers.put(handlerRefId, initiator);
	}

	public void invokeConsumerInitHandlers() {
		this.pubSubConsumerInitHandlers.values().forEach(aConsumerInitHandler -> {
			try {
				aConsumerInitHandler.initiatePubSubConsumers();
			} catch (Exception e) {
				LOGGER.error("Exception occured", e);
			}
		});
	}

}
