package com.springboot.insurance_management.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="customer_id")
    private int customerId;
    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique = true, name="user_name")
    private String userName;

    @Column(nullable=false, unique = true)
    private String email;
      @Column(name="phone_number")
    private int phoneNumber;
    @Column(nullable = false, name="date_of_birth")
    private LocalDate dateOfBirth;

    //many-to-many mapping with policy
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="customer_policy",
            joinColumns=@JoinColumn(name="customer_id"),
            inverseJoinColumns =@JoinColumn(name="policy_id")

    )
    private Set<Policy> policies;

    //one-to-many with claims
    @OneToMany(mappedBy = "customer" ,cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Claim>claims;
}
