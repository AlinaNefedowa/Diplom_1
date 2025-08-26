import org.junit.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.*;

public class BunTest {

    @Test
    public void testBunGetters() {
        Bun bun = new Bun("Black Bun", 2.5f);
        assertEquals("Black Bun", bun.getName());
        assertEquals(2.5f, bun.getPrice());
    }
}
