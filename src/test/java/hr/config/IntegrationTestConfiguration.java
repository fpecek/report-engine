package hr.config;

import hr.config.MockSecurity;
import hr.support.WithMockHexUser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for local test configuration.
 *
 * @author frano.pecek
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("testDatabase")
@Transactional
@WithMockHexUser
@SpringBootTest(classes = {TestBaseConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Import(MockSecurity.class)
public @interface IntegrationTestConfiguration {
}
