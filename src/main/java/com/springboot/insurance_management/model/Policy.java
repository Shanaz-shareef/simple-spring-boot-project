package com.springboot.insurance_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @ManyToMany(mappedBy = "policies")
    private Set<Customer>customers;
    @OneToMany(mappedBy="policy", cascade = CascadeType.ALL)
    private Set<Claim>claims;

}
