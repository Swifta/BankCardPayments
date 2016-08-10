package com.ng.mats.psa.mt.flutterwave.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FlutterWavePropertyValues {

	public Properties getPropertyValues() {

		Properties prop = new Properties();
		String propFileName = "com/ng/mats/psa/mt/flutterwave/util/config.propertiesx";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {

				prop.load(inputStream);

			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

		return prop;
	}
}
