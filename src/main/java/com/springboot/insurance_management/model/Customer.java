package com.springboot.insurance_management.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
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
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable( joinColumns=@JoinColumn(name="customer_id"),
            inverseJoinColumns =@JoinColumn(name="policy_id")

    )
    private Set<Policy> policies;
    @OneToMany(mappedBy = "customers")
    private List<Claim>claims;
}
