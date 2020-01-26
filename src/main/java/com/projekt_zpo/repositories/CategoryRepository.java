package com.projekt_zpo.repositories;

import com.projekt_zpo.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends CrudRepository<Category, Integer> {

    @Override
    List<Category> findAll();

    Category findByName(@Param("categoryName") String name);
}
