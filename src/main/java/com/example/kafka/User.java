package com.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private String nationality;
    private String no;
}
