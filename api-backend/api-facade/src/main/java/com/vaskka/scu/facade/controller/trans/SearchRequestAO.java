package com.vaskka.scu.facade.controller.trans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author rusheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequestAO implements Serializable {

    private String searchKey;

}
