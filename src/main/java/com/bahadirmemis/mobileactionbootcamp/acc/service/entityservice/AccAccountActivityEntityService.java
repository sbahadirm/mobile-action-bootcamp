package com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.acc.dao.AccAccountActivityDao;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccountActivity;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class AccAccountActivityEntityService extends BaseEntityService<AccAccountActivity, AccAccountActivityDao> {

    public AccAccountActivityEntityService(AccAccountActivityDao dao) {
        super(dao);
    }
}
