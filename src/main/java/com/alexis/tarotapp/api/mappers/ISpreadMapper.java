package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.SpreadDto;
import com.alexis.tarotapp.api.dto.listing.SpreadListingResultDto;
import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.repository.listing.SpreadListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;

@Mapper
public interface ISpreadMapper {
    Spread dtoToEntity(Session session, SpreadDto spreadDto);
    SpreadDto entityToDto(Spread spread);

    SpreadListingResultDto entityToDto(SpreadListingResult spreadListingResult);
}
