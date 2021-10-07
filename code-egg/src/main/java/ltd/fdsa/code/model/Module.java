package ltd.fdsa.code.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Module  implements Serializable {
    String name;
    String description;
    Entity[] entities;
    RelationDefine[] relations;
}
