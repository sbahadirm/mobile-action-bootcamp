package com.bahadirmemis.mobileactionbootcamp.log.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import com.bahadirmemis.mobileactionbootcamp.log.dao.LogErrorDao;
import com.bahadirmemis.mobileactionbootcamp.log.entity.LogError;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class LogErrorEntityService extends BaseEntityService<LogError, LogErrorDao> {

    public LogErrorEntityService(LogErrorDao dao) {
        super(dao);
    }
}
