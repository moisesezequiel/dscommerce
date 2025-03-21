package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant monent;

    @OneToOne
    @MapsId //dependente do pedido
    private Order order;

    public Payment() {

    }

    public Payment(Long id, Instant monent, Order order) {
        this.id = id;
        this.monent = monent;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMonent() {
        return monent;
    }

    public void setMonent(Instant monent) {
        this.monent = monent;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
