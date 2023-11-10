// package com.seta.remote.interview.models.entity;

// import lombok.*;

// import javax.persistence.*;

// @Builder
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table(name = "order_product_relationship")
// public class OrderProductRelationship {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "order_id")
//     @ToString.Exclude
//     @EqualsAndHashCode.Exclude
//     private Order order;

//     @ManyToOne
//     @JoinColumn(name = "product_id")
//     @ToString.Exclude
//     @EqualsAndHashCode.Exclude
//     private Product product;
// }
