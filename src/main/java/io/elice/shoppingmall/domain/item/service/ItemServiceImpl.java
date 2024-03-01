package io.elice.shoppingmall.domain.item.service;

import io.elice.shoppingmall.domain.category.entity.SecondCategory;
import io.elice.shoppingmall.domain.category.repository.SecondCategoryRepository;
import io.elice.shoppingmall.domain.item.dto.payload.ItemCreatePayload;
import io.elice.shoppingmall.domain.item.dto.payload.ItemUpdatePayload;
import io.elice.shoppingmall.domain.item.dto.result.ItemDetailResult;
import io.elice.shoppingmall.domain.item.dto.result.ItemResult;
import io.elice.shoppingmall.domain.item.entity.Item;
import io.elice.shoppingmall.domain.item.repository.ItemRepository;
import io.elice.shoppingmall.util.mapsturct.item.ItemDetailResultMapper;
import io.elice.shoppingmall.util.mapsturct.item.ItemResultMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final SecondCategoryRepository secondCategoryRepository;
    private final ItemResultMapper itemResultMapper;
    private final ItemDetailResultMapper itemDetailResultMapper;

    @Transactional
    @Override
    public Long saveItem(ItemCreatePayload payload) {
        SecondCategory secondCategory = secondCategoryRepository.findById(payload.getMiddleCategoryId()).orElseThrow();
        Item save = itemRepository.save(
                Item.builder()
                        .name(payload.getName())
                        .price(payload.getPrice())
                        .stock(payload.getStock())
                        .sellCount(payload.getSellCount())
                        .discountPer(payload.getDiscountPer())
                        .secondCategory(secondCategory)
                        .build());
        return save.getId();
    }

    // 유저가 세컨드 카테고리를 통해서 아이템리스트을 조회시 사용
    @Override
    public List<ItemResult> findItems(Long secondCategoryId) {
        List<Item> itemList = itemRepository.findAllBySecondCategoryId(secondCategoryId);
        List<ItemResult> dtoList = itemResultMapper.toDtoList(itemList);
        return dtoList;
    }
    // 유저가 아이템을 단건 조회시 사용
    @Override
    public ItemResult findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        ItemResult dto = itemResultMapper.toDto(item);
        return dto;
    }
    // 관리자가 아이템리스트를 조회시 사용
    @Override
    public List<ItemDetailResult> findAdminItems(Long secondCategoryId) {
        List<Item> itemList = itemRepository.findAllBySecondCategoryId(secondCategoryId);
        List<ItemDetailResult> dtoList = itemDetailResultMapper.toDtoList(itemList);
        return dtoList;
    }
    // 관리자가 아이템을 단건 조회시 사용
    @Override
    public ItemDetailResult findAdminItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        ItemDetailResult dto = itemDetailResultMapper.toDto(item);
        return dto;
    }


    @Transactional
    @Override
    public Long updateItem(Long id, ItemUpdatePayload payload) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.updateItem(payload.getName(), payload.getPrice(), payload.getStock(), payload.getSellCount(), payload.getDiscountPer());
        return item.getId();
    }

    @Transactional
    @Override
    public Long deleteItem(Long id) {
        itemRepository.deleteById(id);
        return id;
    }
}