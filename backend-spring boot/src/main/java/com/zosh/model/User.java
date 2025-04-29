package com.zosh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zosh.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    private String mobile;

    private USER_ROLE role;

    @OneToMany
    private Set<Address> addresses=new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_coupons",
            inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
    private Set<Coupon> usedCoupons=new HashSet<>();

}
