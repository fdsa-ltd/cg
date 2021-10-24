package ltd.fdsa.code.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Association implements Serializable {
    String name;
    String code;
    String remark;
    Type type;
    Entity reference;
    String primaryKey;
    Entity entity;
    String foreignKey;

    public enum Type {
        One2One,
        One2Many,
        Many2One,
        Many2Many,
    }
}
