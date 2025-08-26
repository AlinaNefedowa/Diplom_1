import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        ingredientMock = mock(Ingredient.class);
    }

    @Test
    void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    void testAddRemoveMoveIngredient() {
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());

        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());

        burger.addIngredient(ingredientMock);
        burger.addIngredient(mock(Ingredient.class));
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock, burger.ingredients.get(1));
    }

    @Test
    void testGetPrice() {
        when(bunMock.getPrice()).thenReturn(2.0f);
        when(ingredientMock.getPrice()).thenReturn(1.5f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        assertEquals(2*2.0f + 1.5f, burger.getPrice());
    }

    @Test
    void testGetReceipt() {
        when(bunMock.getName()).thenReturn("White Bun");
        when(bunMock.getPrice()).thenReturn(2.0f);
        when(ingredientMock.getName()).thenReturn("Cheese");
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getPrice()).thenReturn(1.5f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("White Bun"));
        assertTrue(receipt.contains("cheese"));
        assertTrue(receipt.contains("Price: 5.5"));
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 1.5, 5.5",
            "1.0, 2.0, 4.0"
    })
    void testGetPriceParameterized(float bunPrice, float ingredientPrice, float expected) {
        when(bunMock.getPrice()).thenReturn(bunPrice);
        Ingredient ing = mock(Ingredient.class);
        when(ing.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bunMock);
        burger.addIngredient(ing);

        assertEquals(expected, burger.getPrice());
    }
}

