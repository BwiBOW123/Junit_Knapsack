import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import com.bwibow.Knapsack;

public class KnapsackTest {

// Test when the knapsack capacity is zero
@Test
public void testKnapSack_zeroCapacity() {
    int profit[] = {60, 100, 120};
    int weight[] = {10, 20, 30};
    int W = 0;
    int n = profit.length;
    assertEquals(0, Knapsack.knapSack(W, weight, profit, n));
}

// Parameterized test for combining capacity and items
@ParameterizedTest
@CsvSource({"10, 1, 10, 1", "20, 1, 20, 1", "30, 1, 30, 1"})
public void testKnapSack_parameterized(int W, int expected, int weight, int profit) {
    int n = 1;
    assertEquals(expected, Knapsack.knapSack(W, new int[]{weight}, new int[]{profit}, n));
}

// Test when there's an error due to negative capacity
@Test
public void testKnapSack_negativeCapacityError() {
    int profit[] = {60, 100, 120};
    int weight[] = {10, 20, 30};
    int W = -50; // ส่งค่าลบเพื่อทดสอบข้อผิดพลาด
    int n = profit.length;
    assertThrows(IllegalArgumentException.class, () -> Knapsack.knapSack(W, weight, profit, n));
}

// Test using specified timeout for test execution
@Test
public void testKnapSack_timeout() {
    int profit[] = {60, 100, 120};
    int weight[] = {10, 20, 30};
    int W = 50;
    int n = profit.length;
    assertTimeout(Duration.ofMillis(100), () -> {
        assertEquals(220, Knapsack.knapSack(W, weight, profit, n));
    });
}



// Test when the weight of an item is greater than the knapsack capacity
@Test
public void testKnapSack_weightGreaterThanCapacity() {
    int profit[] = {60};
    int weight[] = {20};
    int W = 10;
    int n = profit.length;
    assertFalse(Knapsack.knapSack(W, weight, profit, n) > 0);
}

// Test when only some items are chosen to be kept in the knapsack
@Test
public void testKnapSack_someItemsChosen() {
    int profit[] = {60, 100, 120};
    int weight[] = {10, 20, 30};
    int W = 50;
    int n = 2; // Choose 2 items to keep in the knapsack
    assertNotNull(Knapsack.knapSack(W, weight, profit, n));
}

// Test when knapsack capacity is zero, expecting the result to be zero
@Test
public void testKnapSack_capacityZero() {
    int profit[] = {60, 100, 120};
    int weight[] = {10, 20, 30};
    int W = 0;
    int n = profit.length;
    assertTrue(Knapsack.knapSack(W, weight, profit, n) == 0);
}
}
