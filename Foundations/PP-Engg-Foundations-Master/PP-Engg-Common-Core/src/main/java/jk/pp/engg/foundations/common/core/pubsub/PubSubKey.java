package jk.pp.engg.foundations.common.core.pubsub;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PubSubKey {

	static {
		PubSubContants.OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
	}

	private String topic;
	private String domainName;
	private String eventType;
	private Long pk;
	private String key;
	private Integer partition;
	private String partitoinVal;
	private Map<String, Object> addlData;

	public String generateKeyString() {
		try {
			return PubSubContants.OBJECT_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "PubSubKey [topic=" + topic + ", domainName=" + domainName + ", eventType=" + eventType + ", pk=" + pk
				+ ", key=" + key + ", partition=" + partition + ", partitoinVal=" + partitoinVal + ", addlData="
				+ addlData + "]";
	}

}
