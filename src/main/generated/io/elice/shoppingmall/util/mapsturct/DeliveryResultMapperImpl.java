package io.elice.shoppingmall.util.mapsturct;

import io.elice.shoppingmall.domain.delivery.dto.result.DeliveryResult;
import io.elice.shoppingmall.domain.delivery.entity.Delivery;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-04T17:40:33+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class DeliveryResultMapperImpl implements DeliveryResultMapper {

    @Override
    public DeliveryResult toDto(Delivery entity) {
        if ( entity == null ) {
            return null;
        }

        DeliveryResult deliveryResult = new DeliveryResult();

        deliveryResult.setId( entity.getId() );
        deliveryResult.setAddress( entity.getAddress() );

        return deliveryResult;
    }

    @Override
    public List<DeliveryResult> toDtoList(List<Delivery> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DeliveryResult> list = new ArrayList<DeliveryResult>( entities.size() );
        for ( Delivery delivery : entities ) {
            list.add( toDto( delivery ) );
        }

        return list;
    }
}
