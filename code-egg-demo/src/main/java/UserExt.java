import ltd.fdsa.code.annotation.Relation;
import lombok.Data;
import ltd.fdsa.code.CodeEgg;

@Data
public class UserExt extends User {
    Integer age;
    @Relation(entity = User.class, field = "name")
    Integer id;

    private void test() {
        CodeEgg ddd = new CodeEgg();
    }
}

