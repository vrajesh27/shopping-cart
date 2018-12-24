package com.shopping.cart.management;

import com.shopping.cart.management.cart.Cart;
import com.shopping.cart.management.exception.ShoppingCartException;
import com.shopping.cart.management.product.Product;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

public class CartSystemManagerImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    /**
     * Given:
     * An empty shopping cart
     * And a product, Dove Soap with a unit price of 39.99
     *
     * When:
     * The user adds 5 Dove Soaps to the shopping cart
     *
     * Then:
     * The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
     * And the shopping cart’s total price should equal 199.95
     */
    @Test
    public void testAddProductsInOneStepToEmptyCart_ItemsAddedToCart() throws ShoppingCartException {
        // Arrange
        Product doveSoap = Product.Builder.create()
                            .withId(1).withDisplay("Dove Soap").withPrice(39.99).withQuantity(5).build();

        // Act
        Cart newCart = new Cart(doveSoap);

        // Assert
        Assert.assertEquals(1, newCart.getProducts().size());
        newCart.getProducts().stream().forEach(product -> {
            Assert.assertEquals(5, product.getQuantity());
            Assert.assertEquals(product, doveSoap);
        });
        Assert.assertEquals(199.95, newCart.getPrice(), 000000000002);
    }

    /**
     * Given:
     * An empty shopping cart
     * And a product, Dove Soap with a unit price of 39.99
     *
     * When:
     * The user adds 5 Dove Soaps to the shopping cart
     *
     * Then:
     * The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
     * And the shopping cart’s total price should equal 199.95
     */
    @Test
    public void testAddProductsInMultipleStepsToEmptyCart_ItemsAddedToCart() throws ShoppingCartException {
        // Arrange
        Product doveSoap = Product.Builder.create()
                .withId(1).withDisplay("Dove Soap").withPrice(39.99).withQuantity(5).build();

        // Act
        Cart newCart = new Cart(doveSoap);

        Product againDoveSoap = Product.Builder.create()
                .withId(1).withDisplay("Dove Soap").withPrice(39.99).withQuantity(3).build();

        newCart.addProduct(againDoveSoap);

        Set<Product> addedProducts = new HashSet<>();
        addedProducts.add(doveSoap);
        addedProducts.add(againDoveSoap);

        // Assert
        Assert.assertEquals(1, newCart.getProducts().size());
        newCart.getProducts().stream().forEach(product -> {
            Assert.assertEquals(8, product.getQuantity());
            Assert.assertEquals(product, doveSoap);
        });
        Assert.assertEquals(319.92, newCart.getPrice(), 000000000002);
    }

    /**
     * Given:
     * An empty shopping cart
     * And a product, Dove Soap with a unit price of 39.99
     * And another product, Axe Deo with a unit price of 99.99
     * And a sales order rate of 12.5%
     *
     * When:
     * The user adds 2 Dove Soaps to the shopping cart
     * And then adds 2 Axe Deo’s to the shopping cart
     *
     * Then:
     * The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
     * And the shopping cart should contain 2 Axe Deo’s each with a unit price of 99.99
     * And the total sales order amount for the shopping cart should equal 35.00
     * And the shopping cart’s total price should equal 314.96
     */
    @Test
    public void testAddProductsAndCalculateTaxInMultipleStepsToEmptyCart_ItemsAddedToCart() throws ShoppingCartException {
        // Arrange
        Product doveSoap = Product.Builder.create()
                .withId(1).withDisplay("Dove Soap").withPrice(39.99).withQuantity(2).build();

        Product axeDeo = Product.Builder.create()
                .withId(2).withDisplay("Axe Deo").withPrice(99.99).withQuantity(2).build();

        Set<Product> addedProducts = new HashSet<>();
        addedProducts.add(doveSoap);
        addedProducts.add(axeDeo);

        // Act
        Cart newCart = new Cart(doveSoap);
        newCart = newCart.addProduct(axeDeo);

        // Assert
        Assert.assertEquals(2, newCart.getProducts().size());
        Assert.assertTrue(newCart.getProducts().containsAll(addedProducts));
        Assert.assertEquals(35, newCart.getSalesTaxPrice(), 000000000002);
        Assert.assertEquals(314.96, newCart.getPrice(), 000000000002);
    }
}
