package jk.pp.engg.foundations.common.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jk.pp.engg.foundations.common.core.pubsub.PubSubConsumerInitiator;

@Component
public class AppGlobalObjects {

	public static final String EXECUTOR_SVC_BEAN_ID = "PlatformCommonExecutorService";

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private Map<String, PubSubConsumerInitiator> pubSubConsumerInitHandlers = new HashMap<>();

	@Value("${pp.platform.common.executor.threads:3}")
	private Integer executeThreads = 3;

//	@Bean(name = EXECUTOR_SVC_BEAN_ID)
//	public ExecutorService createConsumerExecutorService() {
//		return Executors.newFixedThreadPool(executeThreads);
//	}

	public void registerPubSubConsumerInitHandler(String handlerRefId, PubSubConsumerInitiator initiator) {
		System.out.println("registerPubSubConsumerInitHandler handlerRefId " + handlerRefId);
		this.pubSubConsumerInitHandlers.put(handlerRefId, initiator);
	}

	public void invokeConsumerInitHandlers() {
		this.pubSubConsumerInitHandlers.values().forEach(aConsumerInitHandler -> {
			try {
				aConsumerInitHandler.initiatePubSubConsumers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
