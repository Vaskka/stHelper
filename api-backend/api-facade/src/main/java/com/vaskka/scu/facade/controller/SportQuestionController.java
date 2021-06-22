package com.vaskka.scu.facade.controller;

import com.vaskka.scu.facade.controller.trans.SearchRequestAO;
import com.vaskka.scu.facade.processor.model.SportQuestionRecordDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

/**
 * @author rusheng
 */
@RequestMapping("/v1/api")
public interface SportQuestionController {

    /**
     * 根据关键字搜索
     * @param body body json
     * @return Flux
     */
    @PostMapping(value = "/search")
    Flux<SportQuestionRecordDTO> searchQuestionLikely(@RequestBody SearchRequestAO body);

}
