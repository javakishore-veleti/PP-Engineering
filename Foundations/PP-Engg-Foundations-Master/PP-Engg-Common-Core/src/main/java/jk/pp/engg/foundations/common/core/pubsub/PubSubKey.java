package jk.pp.engg.foundations.common.core.pubsub;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PubSubKey {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	static {
		OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
	}

	private String domainName;
	private String eventType;
	private Long pk;
	private String key;
	private Integer partition;
	private String partitoinVal;
	private Map<String, Object> addlData;

	public String generateKeyString() {
		try {
			return OBJECT_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}

}
