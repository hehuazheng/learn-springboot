package com.hzz.controller;

import com.hzz.App;
import com.hzz.model.Tb1;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * HelloController Tester.
 *
 * @author <hezz>
 * @since <pre>05/09/2020</pre>
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
public class HelloControllerTest {
    @Resource
    private HelloController helloController;

    @Before
    public void before() throws Exception {
    }

    /**
     * Method: sayHi(String name)
     */
    @Test
    public void testSayHi() throws Exception {
    }

    /**
     * Method: select(Long id)
     */
    @Test
    @Sql(value="classpath:sql/tb1_data.sql")
    public void testSelect() throws Exception {
        Tb1 tb1 = helloController.select(3L);
        assertEquals("col3", tb1.getCol1());
    }

    /**
     * Method: selectMany()
     */
    @Test
    public void testSelectMany() throws Exception {
    }

    /**
     * Method: testTransaction(String val1, String val2, String rollback)
     */
    @Test
    public void testTestTransaction() throws Exception {
        helloController.testTransaction("tb1c2", "tb2c2", "false");
    }

    /**
     * Method: testMannualTrac(String val1, String val2, String rollback)
     */
    @Test
    public void testTestMannualTrac() throws Exception {
    }

}
