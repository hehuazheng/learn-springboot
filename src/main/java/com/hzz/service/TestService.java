package com.hzz.service;

import com.hzz.model.Tb1;

/**
 * @author: hezz
 */
public interface TestService {
    Tb1 selectById(Long id);

    void add(String v1, String v2, boolean rollback);

    void add2(String v1, String v2, boolean rollback);
}
