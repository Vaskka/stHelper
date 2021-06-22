package com.vaskka.scu.facade.processor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * @author rusheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportQuestionRecordDTO implements Serializable {
    
    private Long id;

    private String rawText;

    private String rawTexMmd5;

    private Instant gmtCreate;

    private Instant gmtModified;

    private String questionType;

    private String questionBody;

    private List<String> questionOption;

    private String questionAnswer;

}
