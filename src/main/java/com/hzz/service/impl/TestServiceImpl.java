package com.hzz.service.impl;

import com.hzz.mapper.Tb1Mapper;
import com.hzz.mapper.Tb2Mapper;
import com.hzz.model.Tb1;
import com.hzz.model.Tb2;
import com.hzz.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author: hezz
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private Tb1Mapper tb1Mapper;
    @Resource
    private Tb2Mapper tb2Mapper;

    @Resource
    private PlatformTransactionManager transactionManager;

    public Tb1 selectById(Long id) {
        return tb1Mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 3, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void selectManyTimes() {
        tb1Mapper.selectByPrimaryKey(1L);
        tb1Mapper.selectByPrimaryKey(1L);
        return;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 3, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void add(String v1, String v2, boolean rollback) {
        Tb1 tb = tb1Mapper.selectByPrimaryKey(1L);
        System.out.println(tb);

        Tb1 tb1 = new Tb1();
        tb1.setCol1(v1);
        tb1Mapper.insert(tb1);
        if(rollback) {
            throw new RuntimeException("force rollback");
        }
        Tb2 tb2 = new Tb2();
        tb2.setCol1(v2);
        tb2Mapper.insert(tb2);
    }

    @Override
    public void add2(String v1, String v2, boolean rollback) {
        TransactionStatus status = null;

        TransactionDefinition definition = new DefaultTransactionDefinition();
        ((DefaultTransactionDefinition) definition).setTimeout(5);
        try {
            status = transactionManager.getTransaction(definition);
            Tb1 tb1 = new Tb1();
            tb1.setCol1(v1);
            tb1Mapper.insert(tb1);
            if (rollback) {
                throw new RuntimeException("force rollback");
            }
            Tb2 tb2 = new Tb2();
            tb2.setCol1(v2);
            tb2Mapper.insert(tb2);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }
}
