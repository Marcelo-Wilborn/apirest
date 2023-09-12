package com.dev.apirest.config;

import com.dev.apirest.domain.*;
import com.dev.apirest.enums.OrderStatus;
import com.dev.apirest.enums.PaymentMethod;
import com.dev.apirest.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@Profile("test") //Nome do profile definido no arquivo application.properties
public class TestConfig implements CommandLineRunner {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    
   @Autowired
   CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
        Category cat1 = new Category("Books");
        Category cat2 = new Category("Electronics");
        Category cat3 = new Category("Computers");
        
        Product p1 = Product.builder()
                .name("The Lord of the Rings")
                .description("Lorem ipsum dolor sit amet, consectetur.")
                .price(90.5)
                .available(true)
                .categories(new HashSet<>(List.of(cat1)))
                .build();
        
        Product p2 = Product.builder()
                .name("Smart TV")
                .description("Nulla eu imperdiet purus. Maecenas ante.")
                .price(2190.0)
                .available(true)
                .categories(new HashSet<>(Arrays.asList(cat2, cat3)))
                .build();
        
        Product p3 = Product.builder()
                .name("Macbook Pro")
                .description("Nam eleifend maximus tortor, at mollis.")
                .price(1250.0)
                .available(true)
                .categories(new HashSet<>(List.of(cat3)))
                .build();
        
        Product p4 = Product.builder()
                .name("PC Gamer")
                .description("Donec aliquet odio ac rhoncus cursus.")
                .price(1200.0)
                .available(true)
                .categories(new HashSet<>(List.of(cat3)))
                .build();
        
        Product p5 = Product.builder()
                .name("Rails for Dummies")
                .description("Cras fringilla convallis sem vel faucibus.")
                .price(100.99)
                .available(true).categories(new HashSet<>(List.of(cat1)))
                .build();
        
        
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
        
        
        
        User u1 = User.builder().name("Maria").email("maria@gmail.com").phone("99999999").password("12345").build();
        User u2 = User.builder().name("Alex").email("Alex@gmail.com").phone("98888888").password("qwerty").build();
        
        Order o1 = Order.builder()
                .moment(Instant.parse("2023-06-20T19:53:07Z"))
                .orderStatus(OrderStatus.PAID)
                .client(u1)
                .build();
        Order o2 = Order.builder()
                .moment(Instant.parse("2023-07-21T03:42:10Z"))
                .orderStatus(OrderStatus.WAITING_PAYMENT)
                .client(u2)
                .build();
        
        Order o3 = Order.builder()
                .moment(Instant.parse("2023-07-22T15:21:22Z"))
                .orderStatus(OrderStatus.WAITING_PAYMENT)
                .client(u1)
                .build();
        
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2,o3));
        
        OrderItem oi1 = new OrderItem(o1, p1, 2,p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1,p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2,p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2,p5.getPrice());
        
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
        
        Payment pay1 = new Payment();
        pay1.setMoment(Instant.parse("2023-06-20T21:53:07Z"));
        pay1.setOrder(o1);
        pay1.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        
        o1.setPayment(pay1);
        orderRepository.save(o1);
        
    }
}