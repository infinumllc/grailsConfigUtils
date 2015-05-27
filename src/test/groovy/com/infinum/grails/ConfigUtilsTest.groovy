package com.infinum.grails

import org.junit.Test

class ConfigUtilsTest {

	@Test
	void verifyExternalConfigs(){
		ConfigUtils.verifyExternalConfigs(['/etc/hosts','/usr/local/etc/configUtilsTest.groovy'])
	}
	
	@Test
	void verifyConfigExists(){
		assert ConfigUtils.verifyConfigExists('file:/etc/hosts')
		assert !ConfigUtils.verifyConfigExists('file:/etc/notExists')
		assert !ConfigUtils.verifyConfigExists('/etc/hosts')
	}
}
