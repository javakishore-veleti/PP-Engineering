package jk.pp.engg.foundations.common.core.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import jk.pp.engg.foundations.common.core.pubsub.PubSubConfig;

public class FileUtil {

	public static Properties loadProperties(String fileName) throws Exception {

		InputStream reader = null;

		try {
			reader = FileUtil.class.getClass().getClassLoader().getResourceAsStream(fileName);

			Properties props = new Properties();
			props.load(reader);

			return props;
		} catch (IOException exception) {
			throw new Exception(exception);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public static Map<String, String> loadJsonFileAsMap(String fileName) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) new ObjectMapper().readValue(new File(fileName),
				HashMap.class);

		return result;
	}

	public static PubSubConfig[] loadJsonAsPubSubConfig(String fileName) throws Exception {
		PubSubConfig[] result = new ObjectMapper().readValue(new File(fileName), PubSubConfig[].class);

		Map<String, PubSubConfig> pubSubConfigs = new HashMap<>();

		if (result != null && result.length > 0) {

			Stream.of(result).forEach(aConfig -> {
				pubSubConfigs.put(aConfig.getName(), aConfig);
			});
		}

		return result;
	}

	public static PubSubConfig loadJsonAsPubSubConfig(String fileName, String specificCategory) throws Exception {

		InputStream fileStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
		PubSubConfig[] result = new ObjectMapper().readValue(fileStream, PubSubConfig[].class);

		if (result != null && result.length > 0) {

			for (PubSubConfig aConfig : result) {

				if (specificCategory.equalsIgnoreCase(aConfig.getName())) {
					return aConfig;
				}
			}
		}

		return null;
	}

}
