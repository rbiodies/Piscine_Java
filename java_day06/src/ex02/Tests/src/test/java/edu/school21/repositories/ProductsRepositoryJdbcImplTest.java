package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    ProductsRepositoryJdbcImpl repo;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Lenovo laptop MSCHSD-81273678", 555888L),
            new Product(1L, "HP Dominator NaGiBaToR", 15000L),
            new Product(2L, "MacBook Pro, 1gb RAM, 1GHZ, 13inches", 999999999L),
            new Product(3L, "Huawei TotallyNotACloneOfIphone", 2000L),
            new Product(4L, "SberPortal", 15999L)
    );
    final Product   EXPECTED_FIND_BY_ID_PRODUCT = new Product(4L, "SberPortal", 15999L);
    final Product   EXPECTED_UPDATED_PRODUCT = new Product(2L, "MacBook Pro, 1gb RAM, 1GHZ, 13inches", 999999999L);
    final Product   EXPECTED_SAVED_PRODUCT = new Product(5L, "Xiaomi", 750L);

    @BeforeEach
    void init() throws SQLException {
        Connection connection = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build().getConnection();
        repo = new ProductsRepositoryJdbcImpl(connection);
    }

    @Test
    public void testSave() {
        repo.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(repo.findById(5L).get(), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    public void testUpdate() {
        repo.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(repo.findById(2L).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    public void testDelete() {
        repo.delete(1L);
        Assertions.assertFalse(repo.findById(1L).isPresent());
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(repo.findById(4L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    public void testFindAll() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repo.findAll());
    }
}
