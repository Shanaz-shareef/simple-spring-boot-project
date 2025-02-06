package com.springboot.insurance_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="policy_id")
    private int policyId;
    @Column(name="policy_name")
    private String policyName;
    @Column(name="policy_type")
    private String policyType;
    @Column(name="premium_amount")
    private double premiumAmount;
    @Column(name="coverage_amount")
    private double coverageAmount;
    @Column(name="start_date")
    private LocalDate startDate;

    private LocalDate endDate;

    //many-to-many mapping with customer
    @ManyToMany(mappedBy = "policies")
    private Set<Customer> customers=new HashSet<>();

    //one-to-many mapping with Claims
    @OneToMany(mappedBy="policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Claim>claims;

}
