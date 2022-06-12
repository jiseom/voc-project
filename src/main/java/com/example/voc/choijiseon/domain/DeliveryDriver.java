package com.example.voc.choijiseon.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DeliveryDriver {

    //운송기사 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //운송기사 성함
    private String tel; //연락처
    private int salary; //월급

    @ManyToOne
    private TransportCompany transportCompany;
}
