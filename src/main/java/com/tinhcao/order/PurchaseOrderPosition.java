package com.tinhcao.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PurchaseOrderPosition {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private Long bookId;

    private int quantity;

    @ManyToOne
    @JsonIgnore
    private PurchaseOrder order;
}