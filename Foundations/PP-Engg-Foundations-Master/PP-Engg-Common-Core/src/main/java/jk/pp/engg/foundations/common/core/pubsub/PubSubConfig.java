package jk.pp.engg.foundations.common.core.pubsub;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PubSubConfig {

	private String name;
	private PubSubTopic[] topics;

	private Map<String, PubSubTopic> topicsAsUsedWhenMap;

	public void convertTopicsToUsedWhenMap() {
		if (topics == null || topics.length == 0) {
			return;
		}

		this.topicsAsUsedWhenMap = new HashMap<>();
		for (PubSubTopic aTopic : topics) {
			this.topicsAsUsedWhenMap.put(aTopic.getUsedWhen(), aTopic);
		}
	}

	public PubSubTopic getPubSubTopicForUsedWhen(String usedWhen) {

		if (topicsAsUsedWhenMap == null || usedWhen == null) {
			return null;
		}

		if (this.topicsAsUsedWhenMap.containsKey(usedWhen)) {
			return this.topicsAsUsedWhenMap.get(usedWhen);
		}

		if (this.topicsAsUsedWhenMap.containsKey(PubSubTopic.USED_WHEN_ALL)) {
			return this.topicsAsUsedWhenMap.get(PubSubTopic.USED_WHEN_ALL);
		}

		if (PubSubTopic.UPSERT_COMMANDS.contains(usedWhen)
				&& this.topicsAsUsedWhenMap.containsKey(PubSubTopic.USED_WHEN_ALL_DB_UPSERTS)) {

			return this.topicsAsUsedWhenMap.get(PubSubTopic.USED_WHEN_ALL_DB_UPSERTS);
		}

		return null;
	}

	@Override
	public String toString() {
		return "PubSubConfig [name=" + name + ", topics=" + Arrays.toString(topics) + ", topicsAsUsedWhenMap="
				+ topicsAsUsedWhenMap + "]";
	}

}
