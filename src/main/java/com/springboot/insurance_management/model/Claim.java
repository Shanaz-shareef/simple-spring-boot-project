package com.springboot.insurance_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="claim")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="claim_id")
    private int claimId;
    @Column(name="claim_amount")
    private double claimAmount;

    private String claimNumber;

    private String claimStatus;

    private LocalDate claimDate;

    //many to one mapping with customer
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    //many-to-one mapping with policy
    @ManyToOne
    @JoinColumn(name="policy_id", nullable = false)
    private Policy policy;
}
