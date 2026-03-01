package ru.netology.java;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // Метод для поиска товара по ID
    public Product findById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == id) {
                return products[i];
            }
        }
        return null;
    }

    // Метод удаления товара
    public void remove(int id) {
        // Проверяем, есть ли товар с таким id
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        // Если товар есть - удаляем его
        Product[] tmp = new Product[products.length - 1];
        int index = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() != id) {
                tmp[index] = products[i];
                index++;
            }
        }
        products = tmp;
    }
}