package com.sahan.netent.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "previous_result", catalog = "simple_game_db")
public class PreviousResult implements Serializable {
    private int id;
    private int uniqueId;
    private double amount;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "unique_id", unique = true, nullable = false)
    @JsonProperty("uniqueId")
    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Column(name = "amount")
    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}