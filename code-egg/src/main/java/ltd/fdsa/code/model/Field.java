package ltd.fdsa.code.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Field implements Serializable {
    String name;
    String type;
    String code;
    String display;
    String remark;
    Boolean nullable;
    Boolean primary;
    int length;
    int scale;
}
