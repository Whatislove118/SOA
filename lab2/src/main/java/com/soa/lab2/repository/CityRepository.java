package com.soa.lab2.repository;

import com.soa.lab2.beans.City;
import com.soa.lab2.beans.Climate;
import com.soa.lab2.beans.Government;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
//    Page<City> findAll(Pageable pageable);

    List<City> findAll(Sort sort);

    List<City> findAll();

    Optional<City> findById(Long aLong);
    @Transactional
    void deleteAllByClimate(Climate climate);

    @Transactional
    @Query("select c from City c where c.government > :government")
    List<City> getAllByHigherGovernment(Government government);

    @Transactional
    @Query("select c from City c where c.government < :government")
    List<City> getAllByLowerGovernment(Government government);
}
