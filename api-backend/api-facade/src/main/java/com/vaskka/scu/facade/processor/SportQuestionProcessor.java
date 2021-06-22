package com.vaskka.scu.facade.processor;

import com.vaskka.scu.facade.processor.model.SportQuestionRecordDTO;
import reactor.core.publisher.Flux;

/**
 * @author rusheng
 */
public interface SportQuestionProcessor {

    /**
     * 根据关键字搜索
     * @param searchKey 搜索关键字
     * @return flux
     */
    Flux<SportQuestionRecordDTO> searchLikely(String searchKey);

}
