package io.library;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryCalculatorTest {

    @Nested
    class PACDeliveryStrategySuite {

        @Test
        void givenProductOfWeightZeroThenCostIsTen() {
            Product book = makeProduct("Self-Help Book", Money.zero(), Weight.zero());

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new PACDeliveryStrategy());

            assertEquals(Money.of(10), deliveryCost);
        }

        @Test
        void givenOneProductBelowOneKiloThenDeliveryCostIsTen() {
            Product book = makeProduct("Clean Code", Money.of(120), Weight.grams(500));

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new PACDeliveryStrategy());

            assertEquals(Money.of(10), deliveryCost);
        }

        @Test
        void givenOrderOfTotalWeightBelowOneKiloDeliveryCostIsTen() {
            Order order = makeOrder(
                    makeProduct("Design Patters", Money.of(210), Weight.grams(400)),
                    makeProduct("The Pragmatic Programmer", Money.of(98), Weight.grams(300))
            );

            Money deliveryCost = order.calculateDelivery(new PACDeliveryStrategy());

            assertEquals(Money.of(10), deliveryCost);
        }

        @Test
        void givenOrderFromOneKiloUpToTwoKilosThenCostIsFifteen() {
            Order order = makeOrder(
                    makeProduct("Design Patters", Money.of(210), Weight.grams(600)),
                    makeProduct("The Pragmatic Programmer", Money.of(98), Weight.grams(500))
            );

            Money deliveryCost = order.calculateDelivery(new PACDeliveryStrategy());

            assertEquals(Money.of(15), deliveryCost);
        }
        
        @Test
        void givenTotalWeightAboveTwoKilosThenThrowOrderNotAcceptedDueToWeight() {
            Order order = makeOrder(
                    makeProduct("Design Patters", Money.of(210), Weight.grams(1200)),
                    makeProduct("The Pragmatic Programmer", Money.of(98), Weight.grams(850))
            );

            assertThrows(OrderNotAcceptedException.class, () -> order.calculateDelivery(new PACDeliveryStrategy()));
        }
    }

    @Nested
    class PickupStrategySuite {

        @Test
        void shouldReturnZeroForAnyOrderWeight() {
            Product book = makeProduct("Management 3.0", Money.of(100), anyWeight());

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new PickupStrategy());

            assertEquals(Money.zero(), deliveryCost);
        }
    }

    @Nested
    class SEDEXDeliveryStrategySuite {

        @Test
        void givenProductOfWeightZeroThenCostIsTwelveAndHalf() {
            Product book = makeProduct("Self-Help Book", Money.of(0), Weight.zero());

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new SedexDeliveryStrategy());

            assertEquals(Money.of(12.50), deliveryCost);
        }

        @Test
        void givenOrderUpToFiveHundredGramsThenCostIsTwelveAndHalf() {
            Product book = makeProduct("Clean Code", Money.of(120), Weight.grams(500));

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new SedexDeliveryStrategy());

            assertEquals(Money.of(12.50), deliveryCost);
        }

        @Test
        void givenOrderFromFiveHundredAndOneGramsToOneKiloThenCostIsTwenty() {
            Product book = makeProduct("Design Patterns", Money.of(210), Weight.grams(800));

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new SedexDeliveryStrategy());

            assertEquals(Money.of(20.00), deliveryCost);
        }

        @Test
        void givenOrderExactlyOneKiloThenCostIsTwenty() {
            Product book = makeProduct("The Pragmatic Programmer", Money.of(98), Weight.grams(1000));

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new SedexDeliveryStrategy());

            assertEquals(Money.of(20.00), deliveryCost);
        }

        @Test
        void givenOrderAboveOneKiloThenCostIsFortySixAndHalfPlusAdditionalCost() {
            Product book = makeProduct("Introduction to algorithms", Money.of(300), Weight.grams(1200));

            Order order = makeOrder(book);

            Money deliveryCost = order.calculateDelivery(new SedexDeliveryStrategy());

            Money baseCost = Money.of(46.50);
            Money additionalCost = Money.of(1.50 * 2);

            assertEquals(baseCost.add(additionalCost), deliveryCost);
        }

        @Test
        void givenOrderOfOneAndHalfKiloThenCostIncludesAdditionalCost() {
            Order order = makeOrder(
                    makeProduct("Crystal Clear", Money.of(200), Weight.grams(800)),
                    makeProduct("Grokking Algorithms", Money.of(150), Weight.grams(700))
            );

            Money deliveryCost = order.calculateDelivery(new SedexDeliveryStrategy());

            Money baseCost = Money.of(46.50);
            Money additionalCost = Money.of(1.50 * 5);

            assertEquals(baseCost.add(additionalCost), deliveryCost);
        }
    }

    private Order makeOrder(final Product... products) {
        return new Order(products);
    }

    private Product makeProduct(final String name, final Money price, final Weight weight) {
        return new Product(name, price, weight);
    }

    private static Weight anyWeight() {
        return Weight.grams(1000);
    }
}
