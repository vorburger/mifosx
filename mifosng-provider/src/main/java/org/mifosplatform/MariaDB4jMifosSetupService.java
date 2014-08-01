package org.mifosplatform;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;

public class MariaDB4jMifosSetupService {

	private DB db;

	@Autowired
	public MariaDB4jMifosSetupService(DB db) {
		this.db = db;
	}

	@PostConstruct
	protected void setUpMifosDB() throws ManagedProcessException {
		db.createDB(getTenantDBName());
		// TODO init
		db.createDB("mifostenant-default");
		// TODO init
	}

	public String getTenantDBName() {
		return "mifosplatform-tenants";
	}

	@PreDestroy
	protected void stop() throws ManagedProcessException {
		db = null;
	}
}