package com.homework;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Shop {

    public void start() {

        List<Customer> customers = createCustomerList();

        //Задание 1.
        // Получите список продуктов из категории "Books" с ценой более 100.
        List<Product> listProductsBooks = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream().filter(x -> x.getCategory().equals("Books") && x.priceMoreThanValue(100)))
                .distinct()
                .toList();
        System.out.println("Задание 1");
        for (Product product :
                listProductsBooks) {
            System.out.println(product);
        }


//        Залание 2.
//        Получите список заказов с продуктами из категории "Children's products".
        List<Order> orderListChildrensProducts = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getProducts().stream().anyMatch(x -> x.getCategory().equals("Children's products")))
                .distinct()
                .toList();
        System.out.println("--------------------------");
        System.out.println("Задание 2");
        for (Order order :
                orderListChildrensProducts) {
            System.out.println(order);
        }


//        Задание 3.
//        Получите список продуктов из категории "Toys" и примените скидку 10% и получите сумму всех
//        продуктов.
        BigDecimal productListToysWithDiscount = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream().filter(x -> x.getCategory().equals("Toys")))
                .map(x -> x.getPriceWithDiscount(10))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("--------------------------");
        System.out.println("Задание 3");
        System.out.println(productListToysWithDiscount);


//        Задание 4.
//        Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.
        List<Product> productsListBetweenDates = customers.stream()
                .filter(x -> x.getLevel() == 2L)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(x -> x.getOrderDate().isAfter(LocalDate.of(2025, 2, 1))
                        && x.getOrderDate().isBefore(LocalDate.of(2025, 4, 1)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();
        System.out.println("--------------------------");
        System.out.println("Задание 4");
        for (Product product :
                productsListBetweenDates) {
            System.out.println(product);
        }

//        Задание 5.
//        Получите топ 2 самые дешевые продукты из категории "Books".
        List<Product> productsListCheapBooks = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream().filter(x -> x.getCategory().equals("Books")))
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .toList();
        System.out.println("--------------------------");
        System.out.println("Задание 5");
        for (Product product :
                productsListCheapBooks) {
            System.out.println(product);
        }

//        Задание 6.
//        Получите 3 самых последних сделанных заказа.
        List<Order> lastOrdersList = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(x -> x.getStatus().equals("Доставлено"))
                .distinct()
                .sorted(Comparator.comparing(Order::getDeliveryDate).reversed())
                .limit(3)
                .toList();
        System.out.println("--------------------------");
        System.out.println("Задание 6");
        for (Order order :
                lastOrdersList) {
            System.out.println(order);
        }

//        Задание 7.
//        Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните
//        список их продуктов.
        List<Product> productsOnDate = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(x -> x.getDeliveryDate().equals(LocalDate.of(2025, 3, 14)))
                .peek(System.out::println)
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();
        System.out.println("--------------------------");
        System.out.println("Задание 7");
        for (Product product :
                productsOnDate) {
            System.out.println(product);
        }

//        Задание 8.
//        Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.
        BigDecimal totalSumPerMonth = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(x -> x.getDeliveryDate().isAfter(LocalDate.of(2025, 1, 31))
                        && x.getDeliveryDate().isBefore(LocalDate.of(2025, 3, 1)))
                .flatMap(x -> x.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("--------------------------");
        System.out.println("Задание 8");
        System.out.println(totalSumPerMonth);


//        Задание 9.
//        Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.
        OptionalDouble averagePaymentForOrdersOnDate = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(x -> x.getDeliveryDate().equals(LocalDate.of(2025, 3, 14)))

                .map(Order::getSummaAsBigDecimal)
                .mapToDouble(BigDecimal::doubleValue)
                .average();
        System.out.println("--------------------------");
        System.out.println("Задание 9");
        System.out.println(averagePaymentForOrdersOnDate);

//        Задание 10.
//        Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех
//        продуктов категории "Книги".
        List<Product> productsList =
                customers.stream()
                        .flatMap(customer -> customer.getOrders().stream())
                        .flatMap(order -> order.getProducts().stream()
                                .filter(x -> x.getCategory().equals("Books")))
                        .distinct().toList();

        BigDecimal sum = productsList.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = productsList.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(productsList.size()));
        BigDecimal min = productsList.stream().map(Product::getPrice).reduce(BigDecimal::min).get();
        BigDecimal max = productsList.stream().map(Product::getPrice).reduce(BigDecimal::max).get();
        int count = productsList.size();
        System.out.println("--------------------------");
        System.out.println("Задание 10");
        System.out.println("sum - " + sum);
        System.out.println("average - " + average);
        System.out.println("min - " + min);
        System.out.println("max - " + max);
        System.out.println("count - " + count);


//        Задание 11.
//        Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе
        Map<Long, Long> ordersAndQuantityGoods = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(
                        Collectors.groupingBy(Order::getId, Collectors.mapping(Order::getProducts, Collectors.counting())));
        System.out.println("--------------------------");
        System.out.println("Задание 11");
        for (Map.Entry<Long, Long> entry : ordersAndQuantityGoods.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

//        Задание 12.
//        Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов
        Map<Customer, List<Order>> customersAndOrders = customers.stream()
                .collect(Collectors.toMap(Function.identity(),
                        customer -> customer.getOrders().stream().toList()));
        System.out.println("--------------------------");
        System.out.println("Задание 12");
        for (Map.Entry<Customer, List<Order>> entry : customersAndOrders.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Order order :
                    entry.getValue()) {
                System.out.println(order);
            }
        }

//        Задание 13.
//        Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.
        Map<Order, Double> ordersAndAmounts = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.toMap(Function.identity(),
                        Order::getSummaAsDouble));
        System.out.println("--------------------------");
        System.out.println("Задание 13");
        for (Map.Entry<Order, Double> entry : ordersAndAmounts.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }


//        Задание 14.
//        Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории
        Map<String, List<String>> categoriesAnsGoods = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::toString, Collectors.toList())));

        System.out.println("--------------------------");
        System.out.println("Задание 14");
        for (Map.Entry<String, List<String>> entry : categoriesAnsGoods.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String e :
                    entry.getValue()) {
                System.out.println(e);
            }
        }

//        Задание 15.
//        Получите Map<String, Product> → самый дорогой продукт по каждой категории.
        Map<String, Optional<Product>> categoriesAndExpensiveProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.maxBy(Comparator.comparing(Product::getPrice))));
        System.out.println("--------------------------");
        System.out.println("Задание 15");
        for (Map.Entry<String, Optional<Product>> entry : categoriesAndExpensiveProduct.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }


    public List<Customer> createCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        // Создайте набор разных продуктов Product , из которых будут наполняться заказы Order

        //фрукты
        Product orange = new Product(10L, "апельсин", "Fruits", new BigDecimal(100));
        Product apple = new Product(11L, "яблоко", "Fruits", new BigDecimal(110));
        Product lemon = new Product(12L, "лимон", "Fruits", new BigDecimal(120));
        Product banana = new Product(13L, "банан", "Fruits", new BigDecimal(130));
        Product peach = new Product(14L, "персик", "Fruits", new BigDecimal(140));

        //овощи
        Product potato = new Product(20L, "картошка", "Vegetables", new BigDecimal(210));
        Product tomato = new Product(21L, "томаты", "Vegetables", new BigDecimal(220));
        Product pumpkin = new Product(22L, "тыква", "Vegetables", new BigDecimal(230));
        Product cabbage = new Product(23L, "капуста", "Vegetables", new BigDecimal(240));
        Product cucumber = new Product(24L, "огурец", "Vegetables", new BigDecimal(250));

        //книги
        Product book1 = new Product(30L, "Война и мир", "Books", new BigDecimal(50));
        Product book2 = new Product(31L, "Овод", "Books", new BigDecimal(75));
        Product book3 = new Product(32L, "Тихий Дон", "Books", new BigDecimal(100));
        Product book4 = new Product(33L, "Анна Каренина", "Books", new BigDecimal(120));
        Product book5 = new Product(34L, "Чебурашка", "Books", new BigDecimal(130));

        //детские продукты
        Product chockolate = new Product(40L, "Шоколадка", "Children's products", new BigDecimal(50));
        Product sweet = new Product(41L, "Карамельки", "Children's products", new BigDecimal(60));
        Product milk = new Product(42L, "Молоко", "Children's products", new BigDecimal(70));
        Product jam = new Product(43L, "Варенье", "Children's products", new BigDecimal(80));
        Product bisquit = new Product(44L, "Печеньки", "Children's products", new BigDecimal(90));

        //игрушки
        Product car = new Product(50L, "Машинка", "Toys", new BigDecimal(300));
        Product doll = new Product(51L, "Кукла", "Toys", new BigDecimal(400));
        Product robot = new Product(52L, "Робот", "Toys", new BigDecimal(500));
        Product puzzle = new Product(53L, "Пазлы", "Toys", new BigDecimal(600));
        Product ball = new Product(54L, "Мячик", "Toys", new BigDecimal(700));


        //Покупатель 1
        Set<Product> productSet1 = newProductSet(orange, apple, lemon, banana, peach);
        Order order1 = new Order(1L, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 1), "Доставлено", productSet1);

        Set<Product> productSet2 = newProductSet(book1, book2, book3, book4, book5);
        Order order2 = new Order(2L, LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 1), "Доставлено", productSet2);

        Set<Product> productSet3 = newProductSet(chockolate, milk, jam, bisquit, sweet);
        Order order3 = new Order(3L, LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 1), "Доставлено", productSet3);

        Set<Product> productSet4 = newProductSet(car, doll, robot, puzzle, ball);
        Order order4 = new Order(4L, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 1), "Доставлено", productSet4);

        Set<Product> productSet5 = newProductSet(tomato, potato, cucumber, pumpkin, cabbage);
        Order order5 = new Order(5L, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 1), "Доставлено", productSet5);

        Set<Order> orderSet1 = new HashSet<>();
        orderSet1.add(order1);
        orderSet1.add(order2);
        orderSet1.add(order3);
        orderSet1.add(order4);
        orderSet1.add(order5);
        Customer customer1 = new Customer(1L, "Nik", 1L, orderSet1);


        //Покупатель 2
        Set<Product> productSet11 = newProductSet(orange, apple, book1, book2, peach);
        Order order11 = new Order(1L, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 1), "Доставлено", productSet11);

        Set<Product> productSet12 = newProductSet(orange, apple, book2, book3, peach);
        Order order12 = new Order(2L, LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 14), "Доставлено", productSet12);

        Set<Product> productSet13 = newProductSet(orange, apple, book3, book4, peach);
        Order order13 = new Order(3L, LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 10), "Доставлено", productSet13);

        Set<Product> productSet14 = newProductSet(orange, apple, book4, book5, peach);
        Order order14 = new Order(4L, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 10), "Доставлено", productSet14);

        Set<Product> productSet15 = newProductSet(orange, apple, lemon, tomato, peach);
        Order order15 = new Order(5L, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 10), "Доставлено", productSet15);

        Set<Order> orderSet2 = new HashSet<>();
        orderSet2.add(order11);
        orderSet2.add(order12);
        orderSet2.add(order13);
        orderSet2.add(order14);
        orderSet2.add(order15);
        Customer customer2 = new Customer(2L, "Tom", 2L, orderSet2);

        //Покупатель 3
        Set<Product> productSet21 = newProductSet(chockolate, milk, jam, bisquit, book1);
        Order order21 = new Order(1L, LocalDate.of(2025, 2, 15), LocalDate.of(2025, 2, 15), "Доставлено", productSet21);

        Set<Product> productSet22 = newProductSet(chockolate, milk, jam, bisquit, book2);
        Order order22 = new Order(2L, LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 14), "Доставлено", productSet22);

        Set<Product> productSet23 = newProductSet(chockolate, milk, jam, bisquit, book3);
        Order order23 = new Order(3L, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 4, 15), "Доставлено", productSet23);

        Set<Product> productSet24 = newProductSet(chockolate, milk, jam, bisquit, book4);
        Order order24 = new Order(4L, LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 15), "Доставлено", productSet24);

        Set<Product> productSet25 = newProductSet(chockolate, milk, jam, bisquit, book5);
        Order order25 = new Order(5L, LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 15), "Доставлено", productSet25);

        Set<Order> orderSet3 = new HashSet<>();
        orderSet3.add(order21);
        orderSet3.add(order22);
        orderSet3.add(order23);
        orderSet3.add(order24);
        orderSet3.add(order25);
        Customer customer3 = new Customer(3L, "Sam", 1L, orderSet3);

        //Покупатель 4
        Set<Product> productSet31 = newProductSet(car, doll, robot, puzzle, book1);
        Order order31 = new Order(1L, LocalDate.of(2025, 2, 20), LocalDate.of(2025, 2, 20), "Доставлено", productSet31);

        Set<Product> productSet32 = newProductSet(car, doll, robot, puzzle, book2);
        Order order32 = new Order(2L, LocalDate.of(2025, 3, 20), LocalDate.of(2025, 3, 20), "Доставлено", productSet32);

        Set<Product> productSet33 = newProductSet(car, doll, robot, puzzle, book3);
        Order order33 = new Order(3L, LocalDate.of(2025, 4, 20), LocalDate.of(2025, 4, 20), "Доставлено", productSet33);

        Set<Product> productSet34 = newProductSet(car, doll, robot, puzzle, book4);
        Order order34 = new Order(4L, LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 20), "Доставлено", productSet34);

        Set<Product> productSet35 = newProductSet(car, doll, robot, puzzle, book5);
        Order order35 = new Order(5L, LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 20), "Доставлено", productSet35);

        Set<Order> orderSet4 = new HashSet<>();
        orderSet4.add(order31);
        orderSet4.add(order32);
        orderSet4.add(order33);
        orderSet4.add(order34);
        orderSet4.add(order35);
        Customer customer4 = new Customer(4L, "Mike", 1L, orderSet4);

        //Покупатель 5
        Set<Product> productSet41 = newProductSet(tomato, potato, cucumber, pumpkin, book1);
        Order order41 = new Order(1L, LocalDate.of(2025, 2, 25), LocalDate.of(2025, 2, 25), "Доставлено", productSet41);

        Set<Product> productSet42 = newProductSet(tomato, potato, cucumber, pumpkin, book2);
        Order order42 = new Order(2L, LocalDate.of(2025, 3, 25), LocalDate.of(2025, 3, 25), "Доставлено", productSet42);

        Set<Product> productSet43 = newProductSet(tomato, potato, cucumber, pumpkin, book3);
        Order order43 = new Order(3L, LocalDate.of(2025, 4, 25), LocalDate.of(2025, 4, 25), "Доставлено", productSet43);

        Set<Product> productSet44 = newProductSet(tomato, potato, cucumber, pumpkin, book4);
        Order order44 = new Order(4L, LocalDate.of(2025, 5, 25), LocalDate.of(2025, 5, 25), "Доставлено", productSet44);

        Set<Product> productSet45 = newProductSet(tomato, potato, cucumber, pumpkin, book5);
        Order order45 = new Order(5L, LocalDate.of(2025, 6, 25), LocalDate.of(2025, 6, 25), "Доставлено", productSet45);

        Set<Order> orderSet5 = new HashSet<>();
        orderSet5.add(order41);
        orderSet5.add(order42);
        orderSet5.add(order43);
        orderSet5.add(order44);
        orderSet5.add(order45);
        Customer customer5 = new Customer(5L, "Oliver", 1L, orderSet5);

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerList.add(customer5);

        return customerList;
    }

    public Set<Product> newProductSet(Product product1, Product product2,
                                      Product product3, Product product4,
                                      Product product5) {
        Set<Product> productSet = new HashSet<>();
        productSet.add(product1);
        productSet.add(product2);
        productSet.add(product3);
        productSet.add(product4);
        productSet.add(product5);

        return productSet;
    }
}
