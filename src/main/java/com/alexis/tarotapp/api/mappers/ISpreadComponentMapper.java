package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.SpreadComponentDto;
import com.alexis.tarotapp.api.dto.listing.SpreadComponentListingResultDto;
import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.repository.listing.SpreadComponentListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class ISpreadComponentMapper {
    @Mapping(target = "spread", expression = "java(fetchSpread(session, spreadComponentDto.getSpread().getId()))")
    public abstract SpreadComponent dtoToEntity(Session session, SpreadComponentDto spreadComponentDto);

    public abstract SpreadComponentDto entityToDto(SpreadComponent spreadComponent);

    Spread fetchSpread(Session session, int spreadId) {
        return (Spread) session.get(Spread.class, spreadId);
    }

    public abstract SpreadComponentListingResultDto entityToDto(SpreadComponentListingResult spreadComponentListingResult);
}
