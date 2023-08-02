package ltd.fdsa.code.model;

import lombok.Builder;
import lombok.Data;
import ltd.fdsa.code.annotation.Column;

import java.io.Serializable;

@Builder
@Data
public class Association implements Serializable {
    String name;
    String code;
    String remark;
    Column.Relation type;
    Entity source;
    String primaryKey;
    Entity target;
    String foreignKey;


}
