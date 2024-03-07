package com.advex2.interceptor2.months;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@EntityScan
@AllArgsConstructor
@NoArgsConstructor
public class Month {

    private Integer monthNumber;

    private String englishName;

    private String italianName;

    private String germanName;

}
