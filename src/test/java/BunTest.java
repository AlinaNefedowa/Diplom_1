import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @Test
    void testBunGetters() {
        Bun bun = new Bun("Black Bun", 2.5f);
        assertEquals("Black Bun", bun.getName());
        assertEquals(2.5f, bun.getPrice());
    }
}
