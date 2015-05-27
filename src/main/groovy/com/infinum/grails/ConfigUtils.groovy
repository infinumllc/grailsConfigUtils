package com.infinum.grails

class ConfigUtils {
	
	/**
	 * Put this static method in your Config.groovy to print a message to stdout
	 * regarding whether your external configuration file was found
	 * 
	 * Just call:
	 * ConfigUtils.verifyExternalConfigs(grails.config.locations)
	 * 
	 * Be sure to get the Import for ConfigUtils in your Config.groovy
	 */
	static void verifyExternalConfigs(def configLocations){
		if(!configLocations){
			println "No External Config Locations Specified."
	}

		configLocations.each{String configFilePath ->
			verifyConfigExists(configFilePath)
		}
	}
	
	static boolean verifyConfigExists(String configFilePath){
		boolean exists = false
		
		if(configFilePath.startsWith('file:')){
			String fileSystemPath = configFilePath - 'file:'
			if(new File(fileSystemPath).exists()){
				println "Found external config file: $configFilePath"
				exists = true
			}else if(new File(fileSystemPath + '.txt').exists()){
				println "The file $configFilePath does not exist, but ${configFilePath}.txt DOES exist.  I'll let you decide what to do with it.  Perhaps you would like to curse windows?"
			}else{
				println "Could not find external config file: $configFilePath"
			}
		}else{
			println "Unable to verify existence of config file at location: $configFilePath.  I can only validate locations that start with 'file:'"
		}
		
		return exists
	}
	
	/**
	 * Simple method to take the Grails Configuration and nicely return it as a string
	 * @param conf The section of the config you want to stringify
	 */
	static String printableConfig(conf){
		Map props = [:]
		
		new TreeMap(conf.flatten()).collect{key, value ->
			props.put(key.toString(), value.toString())
		}
		
		return props.toString()
	}

}
