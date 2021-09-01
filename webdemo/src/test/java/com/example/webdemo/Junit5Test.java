package com.example.webdemo;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@SpringBootTest
@DisplayName("junit5功能测试类")
public class Junit5Test {



    @Autowired
    JdbcTemplate jdbcTemplate;


    /*
        断言：前面断言失败，后面的代码不会执行
     */
    @DisplayName( "测试简单断言")
    @Test
    void testSimpleAssertions(){
        int cal = cal(3,2);
      assertEquals(5,cal,"业务逻辑计算失败");
      Object obj1 = new Object();
      Object obj2 = new Object();
      assertSame(obj1,obj2,"2个对象不一样");
    }

    @Test
    @DisplayName("assert all")
    public void all() {
        assertAll("Math",
                () -> assertEquals(2, 1 + 1),
                () -> assertTrue(1 > 0)

        );
    }
    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        //断定业务逻辑一定出现异常
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> {System.out.println(1 / 0);},"数学运算居然没异常");
    }


    //------------------------------------------------
    @DisplayName("前置条件")
    public class AssumptionsTest {
        private final String environment = "DEV";

        @Test
        @DisplayName("simple")
        public void simpleAssume() {
            assumeTrue(Objects.equals(this.environment, "DEV"));
            assumeFalse(() -> Objects.equals(this.environment, "PROD"));
        }

        @Test
        @DisplayName("assume then do")
        public void assumeThenDo() {
            assumingThat(
                    Objects.equals(this.environment, "DEV"),
                    () -> System.out.println("In DEV")
            );
        }
    }


    //---------------------------------------
    @Test
    @DisplayName("fail")
    public void shouldFail() {

        if(2 == 2) {
             fail("This should fail");
        }   }




    private int cal(int i, int i1) {
        return i+i1;
    }

    @DisplayName("测试displayname注解")
    @Test
    void testDisplayName(){
        System.out.println(1);
        System.out.println(jdbcTemplate);
    }

    @Disabled
    @DisplayName("测试方法2")
    @Test
    void test2(){
        System.out.println(2);
    }
     @RepeatedTest(5)
    @Test
    void test3(){
        System.out.println(5);
    }
    /*
    规定方法超时时间。超出时间测试出异常
     */

    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(500 );
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("测试就要开始了...");
    }
    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试就要开始了");
    }

    @AfterAll
   static void testAfterAll()
    {
        System.out.println("所有测试已经结束了...");
    }




}
