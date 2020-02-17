package com.projekt_zpo.repositories;

import com.projekt_zpo.entities.Item;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Integer> {

    @Override
    List<Item> findAll();

    List<Item> findByCategoryId(@Param("categoryId") Integer categoryId);

    Item findItemById(@Param("itemId") Integer id);

    List<Item> findAllByUserId(@Param("userId") Integer userId);

    List<Item> findAllByDescriptionContainsOrNameContains(String keyword, String keyword2);

    List<Item> findAllByDescriptionContainsOrNameContainsAndCategoryId(@Param("keyword") String keyword,
                                                                       @Param("keyword2") String keyword2,
                                                                       @Param("categoryId")Integer categoryId);
   /*
    @Query("SELECT i.* FROM item WHERE i.category_id = :categoryId AND (i.name LIKE %:keyword% OR i.description LIKE %:keyword%)")
    List<Item> findAllByCategoryIdAndDescriptionContainsOrNameContains(@Param("categoryId")Integer categoryId,@Param("keyword") String keyword);
*/


}
