package com.codesnip.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codesnip.app.entity.Pricing;

//@Repository
public interface PricingRepository extends CrudRepository<Pricing, Integer> {

}
