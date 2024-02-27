package io.elice.shoppingmall.domain.item.entity;

import io.elice.shoppingmall.domain.common.BassEntity;
import io.elice.shoppingmall.domain.category.entity.SecondCategory;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Item extends BassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SecondCategory secondCategory;

    private String name;

    private Integer price;

    private Integer stock;

    private Integer sellCount;

    private Integer discountPer;

}
