import ltd.fdsa.code.annotation.Column;
import lombok.Data;
import ltd.fdsa.code.IEntity;

@Data
public class User implements IEntity {
    @Column
    String name;
}

