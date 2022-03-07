package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingletonService() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // ThreadA : A사용자 10,000원 주문
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        // ThreadB : B사용자 20,000원 주문
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int userAPrice = statefulService1.order("userA",10000);
        int userBPrice = statefulService2.order("userB",20000);


        // ThreadA : 사용자A 주문 금액 조회
        System.out.println("price = " + userAPrice);

        Assertions.assertThat(userBPrice).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}