package jk.pp.engg.foundations.common.core.pubsub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "topic", "pk", "success", "ack", "message", "messages", "domain", "domains", "ctxdata" })
@JsonRootName(value = "pub-sub-result")
@JsonInclude(Include.NON_NULL)
public class PubSubResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonRawValue
	private String json;

	private Boolean success = Boolean.FALSE;

	private String pubSubAck;
	private String topic;

	private Long pk;

	private IDomain domain;
	private List<IDomain> domains;

	private PubSubMessage message;
	private List<PubSubMessage> messages;

	private Map<String, Object> ctxData = null;

	@JsonGetter("ctxdata")
	public Map<String, Object> getCtxData() {
		return this.ctxData;
	}

	public void addCtxData(String key, Object val) {

		if (this.ctxData == null) {
			this.ctxData = new HashMap<>();
		}

		this.ctxData.put(key, val);
	}

	@JsonGetter("ack")
	public String getPubSubAck() {
		return this.pubSubAck;
	}

	@JsonGetter("topic")
	public String getTopic() {
		return this.topic;
	}

	public String generateResultAsString() {
		try {
			return PubSubContants.OBJECT_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "PubSubResult [json=" + json + ", success=" + success + ", pubSubAck=" + pubSubAck + ", topic=" + topic
				+ ", pk=" + pk + ", domain=" + domain + ", domains=" + domains + ", message=" + message + ", messages="
				+ messages + ", ctxData=" + ctxData + "]";
	}

}
