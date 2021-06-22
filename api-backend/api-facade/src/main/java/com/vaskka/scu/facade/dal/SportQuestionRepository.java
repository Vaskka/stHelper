package com.vaskka.scu.facade.dal;

import com.vaskka.scu.facade.dal.document.SportQuestionRecordDO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rusheng
 */
@Repository
public interface SportQuestionRepository extends ReactiveMongoRepository<SportQuestionRecordDO, Long> {


}
