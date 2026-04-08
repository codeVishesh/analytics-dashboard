
package com.example.model;

import org.springframework.data.annotation.Id;

public class Metric {
    @Id
    public String id;
    public String name;
    public double value;
    public long timestamp;

    public Metric(){}
    public Metric(String name, double value){
        this.name = name;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
}
