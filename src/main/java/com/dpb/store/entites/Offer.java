package com.dpb.store.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double price;
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @Override
    public String toString() {
        return "Offer{" +
                "price=" + price +
                ", product=" + product +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (Double.compare(offer.getPrice(), getPrice()) != 0) return false;
        if (!getStatus().equals(offer.getStatus())) return false;
        return getStore() != null ? getStore().equals(offer.getStore()) : offer.getStore() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getPrice());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + (getStore() != null ? getStore().hashCode() : 0);
        return result;
    }
}
