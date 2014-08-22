package org.mifosplatform;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mifosplatform.infrastructure.jobs.service.JobRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

public class MifosApplicationListener implements
		ApplicationListener<ContextStoppedEvent> {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private JobRegisterService jobRegisterService;

	@Override
	public void onApplicationEvent(
			@SuppressWarnings("unused") ContextStoppedEvent event) {
		jobRegisterService.stopAllSchedulers();
		shutDowncleanUpThreadAndDeregisterJDBCDriver();
		logger.info("ServletContext destroyed");
	}

	private void shutDowncleanUpThreadAndDeregisterJDBCDriver() {
		try {
			AbandonedConnectionCleanupThread.shutdown();
		} catch (Throwable t) {
			logger.error(
					"Exception occured while shutdown of AbandonedConnectionCleanupThread",
					t);
		}

		// This manually deregisters JDBC driver, which prevents Tomcat 7 from
		// complaining about memory leaks
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				java.sql.DriverManager.deregisterDriver(driver);
			} catch (Throwable t) {
				logger.error("Exception occured while deristering jdbc driver",
						t);
			}
		}
		try {
			Thread.sleep(2000L);
		} catch (Exception e) {
		}
	}
}