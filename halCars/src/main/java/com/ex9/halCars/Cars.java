package com.ex9.halCars;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String modelName;

    @NonNull
    private String serialNumber;

    private Integer price;

}
