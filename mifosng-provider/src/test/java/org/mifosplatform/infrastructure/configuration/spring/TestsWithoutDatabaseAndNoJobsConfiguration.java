package org.mifosplatform.infrastructure.configuration.spring;

import javax.sql.DataSource;

import org.mifosplatform.infrastructure.core.service.TenantDatabaseUpgradeService;
import org.mifosplatform.infrastructure.jobs.service.JobRegisterService;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring @Configuration which does not require a running database. It also does
 * not load any job configuration (as they are in the DB), thus nor starts any
 * background jobs. For some integration tests, this may be perfectly sufficient
 * (and faster to run such tests).
 */
@Configuration
// We do NOT want the testContext.xml here, but the real one!
@ImportResource("classpath*:META-INF/spring/appContext.xml")
public class TestsWithoutDatabaseAndNoJobsConfiguration {

    /**
     * Override TenantDatabaseUpgradeService binding, because the real one has a @PostConstruct
     * upgradeAllTenants() which accesses the database on start-up.
     */
    @Bean
    public TenantDatabaseUpgradeService tenantDatabaseUpgradeService() {
        return new TenantDatabaseUpgradeService(null, null) {
            @Override
            public void upgradeAllTenants() {
                // NOOP
            }
        };
    }

    /**
     * Override JobRegisterService binding, because the real
     * JobRegisterServiceImpl has a @PostConstruct loadAllJobs() which accesses
     * the database on start-up.
     */
    @Bean
    public JobRegisterService jobRegisterServiceImpl() {
        JobRegisterService mockJobRegisterService = Mockito.mock(JobRegisterService.class);
        return mockJobRegisterService;
    }

    /**
     * DataSource with Mockito RETURNS_MOCKS black magic.
     */
    @Bean
    public DataSource tenantDataSourceJndi() {
        DataSource mockDataSource = Mockito.mock(DataSource.class, Mockito.RETURNS_MOCKS);
        return mockDataSource;
    }
}