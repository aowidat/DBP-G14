package com.dpb.store.repos;

import com.dpb.store.entites.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepo extends JpaRepository<Offer, Integer> {
}
