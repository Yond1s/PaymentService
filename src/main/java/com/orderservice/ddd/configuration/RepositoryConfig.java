// package com.orderservice.ddd.configuration;

// import java.util.UUID;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.orderservice.ddd.domain.model.Order;
// import com.orderservice.ddd.domain.repository.OrderRepository;
// import com.orderservice.ddd.infrastructure.database.DataBaseinterface;
// import com.orderservice.ddd.infrastructure.database.InMemoryDatabase;
// import com.orderservice.ddd.infrastructure.repository.InMemoryOrderRepository;

// @Configuration
// public class RepositoryConfig {

//     @Bean
//     public DataBaseinterface<UUID, Order> database() {
//         return new InMemoryDatabase<>();
//     }

//     @Bean
//     public OrderRepository orderRepository(DataBaseinterface<UUID, Order> db) {
//         return new InMemoryOrderRepository(db);
//     }
// }
