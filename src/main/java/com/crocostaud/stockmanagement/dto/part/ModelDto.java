package com.crocostaud.stockmanagement.dto.part;

import com.crocostaud.stockmanagement.model.part.Model;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link Model}
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ModelDto implements Serializable {
    private final Long id;
    private final String ref;
    private final String label;
    private final String name;
    private final Date startedAt;
    private final Date endedIn;
    private final String description;
    private final Long makerId;
}