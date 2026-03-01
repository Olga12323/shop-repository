package ru.netology.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    // Тест на успешное удаление товара
    @Test
    public void testRemoveExistingProduct() {
        // Создаем репозиторий
        ShopRepository repo = new ShopRepository();

        // Создаем товары
        Product product1 = new Product(1, "Книга", 500);
        Product product2 = new Product(2, "Ручка", 50);
        Product product3 = new Product(3, "Тетрадь", 100);

        // Добавляем товары в репозиторий
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        // Удаляем товар с id = 2
        repo.remove(2);

        // Проверяем, что остались только товары с id 1 и 3
        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на попытку удалить несуществующий товар
    @Test
    public void testRemoveNonExistingProduct() {
        // Создаем репозиторий
        ShopRepository repo = new ShopRepository();

        // Создаем товары
        Product product1 = new Product(1, "Книга", 500);
        Product product2 = new Product(2, "Ручка", 50);

        // Добавляем товары
        repo.add(product1);
        repo.add(product2);

        // Пытаемся удалить товар с id = 999 (которого нет)
        // Проверяем, что будет выброшено исключение NotFoundException
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(999);
        });
    }

    // Тест на проверку сообщения исключения
    @Test
    public void testExceptionMessage() {
        // Создаем репозиторий
        ShopRepository repo = new ShopRepository();

        // Добавляем один товар
        repo.add(new Product(1, "Книга", 500));

        // Пытаемся удалить несуществующий товар и ловим исключение
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(777);
        });

        // Проверяем, что сообщение исключения правильное
        Assertions.assertEquals("Element with id: 777 not found", exception.getMessage());
    }

    // Тест на удаление последнего товара
    @Test
    public void testRemoveLastProduct() {
        // Создаем репозиторий
        ShopRepository repo = new ShopRepository();

        // Создаем товар
        Product product = new Product(1, "Книга", 500);

        // Добавляем товар
        repo.add(product);

        // Удаляем товар
        repo.remove(1);

        // Проверяем, что массив пустой
        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на удаление первого товара
    @Test
    public void testRemoveFirstProduct() {
        // Создаем репозиторий
        ShopRepository repo = new ShopRepository();

        // Создаем товары
        Product product1 = new Product(1, "Книга", 500);
        Product product2 = new Product(2, "Ручка", 50);
        Product product3 = new Product(3, "Тетрадь", 100);

        // Добавляем товары
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        // Удаляем первый товар (id = 1)
        repo.remove(1);

        // Проверяем, что остались товары 2 и 3
        Product[] expected = {product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}