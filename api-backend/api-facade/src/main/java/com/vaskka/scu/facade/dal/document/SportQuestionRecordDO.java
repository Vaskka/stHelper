package com.vaskka.scu.facade.dal.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

/**
 * @author rusheng
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportQuestionRecordDO {

    @Id
    @Field("_id")
    private Long id;

    @Field("raw_text")
    private String rawText;

    @Field("raw_text_md5")
    private String rawTexMmd5;

    @Field("gmt_create")
    private Instant gmtCreate;

    @Field("gmt_modified")
    private Instant gmtModified;

    @Field("question_type")
    private String questionType;

    @Field("question_body")
    private String questionBody;

    @Field("question_option")
    private List<String> questionOption;

    @Field("question_answer")
    private String questionAnswer;

}
