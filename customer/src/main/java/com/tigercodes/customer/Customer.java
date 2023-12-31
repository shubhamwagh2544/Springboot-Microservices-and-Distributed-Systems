package com.tigercodes.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
