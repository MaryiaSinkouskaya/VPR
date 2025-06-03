package jmeter;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import us.abstracta.jmeter.javadsl.JmeterDsl;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.jsonExtractor;


public class AbnormalityLoadTest {

    @Test
    @EnabledIf(
        expression = "#{systemProperties['loadTest.test'] == 'true'}",
        reason = "Runs only if -DloadTest.test=true"
    )
    void testGetAbnormalitiesWithJwtAuth() throws Exception {
        TestPlanStats stats = JmeterDsl.testPlan(
                JmeterDsl.threadGroup(5, 10,
                        //Login to get JWT token
                        JmeterDsl.httpSampler("http://localhost:8081/api/auth/authenticate")
                                .method("POST")
                                .contentType(ContentType.APPLICATION_JSON)
                                .body("{ \"email\": \"bjksdkjvsd@mail.com\", \"password\": \"nsdn47n\" }")
                                .children(
                                        jsonExtractor("jwtToken", "access_token"),
                                        JmeterDsl.responseAssertion()
                                                .containsSubstrings("access_token")
                                ),

                        //GET ALL
                        JmeterDsl.httpSampler("http://localhost:8081/api/abnormality")
                                .method("GET")
                                .header("Authorization", "Bearer ${jwtToken}")
                                .header("Content-Type", "application/json")
                                .children(
                                        JmeterDsl.responseAssertion()
                                                .containsSubstrings("id", "name") //adjust to actual JSON fields
                                ),

                        //CREATE
                        JmeterDsl.httpSampler("http://localhost:8081/api/abnormality")
                                .method("POST")
                                .header("Authorization", "Bearer ${jwtToken}")
                                .header("Content-Type", "application/json")
                                .body("""
                                            {
                                              "name": "Test Abnormality"
                                            }
                                        """)
                                .children(
                                        jsonExtractor("abnormalityId", "id")
                                ),

                        //UPDATE
                        JmeterDsl.httpSampler("http://localhost:8081/api/abnormality")
                                .method("PATCH")
                                .header("Authorization", "Bearer ${jwtToken}")
                                .header("Content-Type", "application/json")
                                .body("""
                                            {
                                              "id": ${abnormalityId},
                                              "name": "Updated Abnormality"
                                            }
                                        """)
                                .children(
                                        JmeterDsl.responseAssertion()
                                                .containsSubstrings("Updated Abnormality")
                                ),

                        //DELETE
                        JmeterDsl.httpSampler("http://localhost:8081/api/abnormality/${abnormalityId}")
                                .method("DELETE")
                                .header("Authorization", "Bearer ${jwtToken}"),

                        //GET ALL again to verify deletion
                        JmeterDsl.httpSampler("http://localhost:8081/api/abnormality")
                                .method("GET")
                                .header("Authorization", "Bearer ${jwtToken}")
                                .children(
                                        JmeterDsl.responseAssertion()
                                                .containsSubstrings("${abnormalityId}")
                                                .invertCheck(true)
                                )//,
//                JmeterDsl.resultsTreeVisualizer()
                )
        ).run();

        assertThat(stats.overall().errorsCount()).isEqualTo(0);
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofMillis(1000));
    }
}

