package com.projekt_zpo.repositories;

import com.projekt_zpo.entities.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Integer> {

    @Override
    List<Item> findAll();

    List<Item> findByCategoryId(@Param("categoryId") String name);


}
