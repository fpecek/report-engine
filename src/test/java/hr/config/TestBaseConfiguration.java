package hr.config;

import com.syncleus.ferma.DelegatingFramedGraph;
import com.syncleus.ferma.FramedGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This is starter class used to run spring boot tests and used as
 * base configuration class for creating timesheet spring context in test.
 * It will scan and create all beans used in timesheet project.
 * Class is used inside {@link IntegrationTestConfiguration} annotation.
 * <p>
 * Inside this class you can define beans used only inside the tests.
 * For example, you can define datasource to connect on test database.
 *
 * @author frano.pecek
 */
@Configuration
@ComponentScan(basePackageClasses = TimesheetApplication.class)
public class TestBaseConfiguration {

    @Bean
    @Profile("testDatabase")
    public FramedGraph getJanusGraphInMemory() {
        return new DelegatingFramedGraph<>(JanusGraphFactory.build().set("graph.timestamps", "NANO").set("storage.backend", "inmemory").open());
    }

}
