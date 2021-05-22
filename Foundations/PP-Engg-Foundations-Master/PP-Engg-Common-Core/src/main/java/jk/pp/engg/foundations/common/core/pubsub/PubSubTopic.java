package jk.pp.engg.foundations.common.core.pubsub;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PubSubTopic {

	public static final String USED_WHEN_ALL = "ALL";
	public static final String USED_WHEN_ALL_DB_UPSERTS = "ALL-DB-UPSERTS";
	public static final List<String> UPSERT_COMMANDS = new ArrayList<>();

	static {
		UPSERT_COMMANDS.add("CREATE");
		UPSERT_COMMANDS.add("UPDATE");
		UPSERT_COMMANDS.add("DELETE");
		UPSERT_COMMANDS.add("DELETE_ALL");
	}

	private String name;
	private String topic;
	private String adapterProducerBeanId;
	private String adapterConsumerBeanId;
	private String adapterProducerConfig;
	private String adapterConsumerConfig;

	// CREATE, UPDATE, DELETE, DELETE_ALL, ALL
	private String usedWhen;

	@Override
	public String toString() {
		return "PubSubTopic [name=" + name + ", topic=" + topic + ", adapterProducerBeanId=" + adapterProducerBeanId
				+ ", adapterConsumerBeanId=" + adapterConsumerBeanId + ", adapterProducerConfig="
				+ adapterProducerConfig + ", adapterConsumerConfig=" + adapterConsumerConfig + ", usedWhen=" + usedWhen
				+ "]";
	}

}
