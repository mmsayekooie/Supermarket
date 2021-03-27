package com.example.supermarket.core.date;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sp_date")
public class DDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date date;
    @Column(name = "dayOfWeek")
    private String dayOfWeek;
}
