package io.elice.shoppingmall.domain.item.repository;

import io.elice.shoppingmall.domain.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllBySecondCategoryId(Long secondCategoryId);

    Page<Item> findAllBySecondCategoryId(Long secondCategoryId, Pageable pageable);

    Page<Item> findAllByNameContaining(String name, Pageable pageable);
}
