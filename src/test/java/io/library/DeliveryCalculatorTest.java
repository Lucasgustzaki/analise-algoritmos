package io.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryCalculatorTest {

    private DeliveryCalculator calculator;

    @BeforeEach
    void setup() {
        calculator = new DeliveryCalculator(null);
    }

    @Nested
    class PACDeliveryStrategySuite {

        @BeforeEach
        void setup() {
            calculator.setDeliveryStrategy(new PACDeliveryStrategy());
        }

        @Test
        void givenProductOfWeightZeroThenCostIsTen() {
            Product book = makeProduct("Self-Help Book", ofPrice(0), 0.0f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(10), deliveryCost);
        }

        @Test
        void givenOneProductBelowOnePoundThenDeliveryCostIsTen() {
            Product book = makeProduct("Clean Code", ofPrice(120), 0.5f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(10), deliveryCost);
        }

        @Test
        void givenOrderOfTotalWeightBelowOnePoundDeliveryCostIsTen() {
            Order order = makeOrder(
                    makeProduct("Design Patters", ofPrice(210), 0.4f),
                    makeProduct("The Pragmatic Programmer", ofPrice(98), 0.3f)
            );

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(10), deliveryCost);
        }

        @Test
        void givenOrderFromOnePoundUpToTwoPoundsThenCostIsFifteen() {
            Order order = makeOrder(
                    makeProduct("Design Patters", ofPrice(210), 0.6f),
                    makeProduct("The Pragmatic Programmer", ofPrice(98), 0.5f)
            );

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(15), deliveryCost);
        }
        
        @Test
        void givenTotalWeightAboveTwoPoundsThenThrowOrderNotAcceptedDueToWeight() {
            Order order = makeOrder(
                    makeProduct("Design Patters", ofPrice(210), 1.4f),
                    makeProduct("The Pragmatic Programmer", ofPrice(98), 0.8f)
            );

            assertThrows(OrderNotAcceptedException.class, () -> {
                calculator.calculateDelivery(order);
            });
        }
    }

    @Nested
    class PickupStrategySuite {

        @BeforeEach
        void setup() {
            calculator.setDeliveryStrategy(new PickupStrategy());
        }

        @Test
        void shouldReturnZeroForAnyOrderWeight() {
            Product book = makeProduct("Management 3.0", ofPrice(100), anyWeight());

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(Money.zero(), deliveryCost);
        }
    }

    @Nested
    class SEDEXDeliveryStrategySuite {

        @BeforeEach
        void setup() {
            calculator.setDeliveryStrategy(new SedexDeliveryStrategy());
        }

        @Test
        void givenProductOfWeightZeroThenCostIsTwelveAndHalf() {
            Product book = makeProduct("Self-Help Book", ofPrice(0), 0.0f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(12.50), deliveryCost);
        }

        @Test
        void givenOrderUpToFiveHundredGramsThenCostIsTwelveAndHalf() {
            Product book = makeProduct("Clean Code", ofPrice(120), 0.5f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(12.50), deliveryCost);
        }

        @Test
        void givenOrderFromFiveHundredAndOneGramsToOneKiloThenCostIsTwenty() {
            Product book = makeProduct("Design Patterns", ofPrice(210), 0.8f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(20.00), deliveryCost);
        }

        @Test
        void givenOrderExactlyOneKiloThenCostIsTwenty() {
            Product book = makeProduct("The Pragmatic Programmer", ofPrice(98), 1.0f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            assertEquals(ofPrice(20.00), deliveryCost);
        }

        @Test
        void givenOrderAboveOneKiloThenCostIsFortysixAndHalfPlusAdditionalFees() {
            Product book = makeProduct("Introduction to algorithms", ofPrice(300), 1.2f);

            Order order = makeOrder(book);

            Money deliveryCost = calculator.calculateDelivery(order);

            // 46.50 + 3.00 (200g additional = 2 * 100g * 1.50)
            assertEquals(ofPrice(49.50), deliveryCost);
        }

        @Test
        void givenOrderOfOneAndHalfKiloThenCostIncludesAdditionalFees() {
            Order order = makeOrder(
                    makeProduct("Crystal Clear", ofPrice(200), 0.8f),
                    makeProduct("Grokking Algorithms", ofPrice(150), 0.7f)
            );

            Money deliveryCost = calculator.calculateDelivery(order);

            // 46.50 + 7.50 (500g additional = 5 * 100g * 1.50)
            assertEquals(ofPrice(54.00), deliveryCost);
        }
    }

    private Order makeOrder(final Product... products) {
        return new Order(products);
    }

    private Product makeProduct(final String name, final Money price, final float weight) {
        return new Product(name, price, weight);
    }

    private static Money ofPrice(double price) {
        return new Money(BigDecimal.valueOf(price));
    }

    private static float anyWeight() {
        return 1.0f; // Any positive weight for testing
    }
}
