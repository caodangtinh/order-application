package com.tinhcao.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String customerName;

    private Double amount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<PurchaseOrderPosition> positions = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private PurchaseOrderState state;

}