package ltd.fdsa.code.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Project implements Serializable {
    List<Schema> modules;
}
