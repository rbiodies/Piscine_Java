package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    EmbeddedDatabase ds;
    ProductsRepositoryJdbcImpl repo;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Lenovo laptop MSCHART-81273678", 555888L),
            new Product(1L, "HP Dominator NaGiBaToR", 15000L),
            new Product(2L, "MacBook Pro, 1gb RAM, 1GHZ, 13inches", 999999999L),
            new Product(3L, "Huawei TotallyNotACloneOfIphone", 2000L),
            new Product(4L, "SaberPortal", 15999L)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L, "Huawei TotallyNotACloneOfIphone", 2000L);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(4L, "Another Yandex IoT Device", 999999999L);
    final Product EXPECTED_SAVED_PRODUCT = new Product(5L, "Xiaomi", 750L);

    @BeforeEach
    void init() {
        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        repo = new ProductsRepositoryJdbcImpl(ds);
    }

    @Test
    public void testFindAll() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repo.findAll());
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repo.findById(3L).get());
    }

    @Test
    public void testUpdate() {
        repo.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repo.findById(4L).get());
    }

    @Test
    public void testSave() {
        repo.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repo.findById(5L).get());
    }

    @Test
    public void testDelete() {
        repo.delete(1L);
        Assertions.assertFalse(repo.findById(1L).isPresent());
    }

    @AfterEach
    void close() {
        ds.shutdown();
    }
}
