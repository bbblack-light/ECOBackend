package ECOBackend.config;

import ECOBackend.controllers.utils.Converters;
import ECOBackend.dto.*;
import ECOBackend.model.*;
import ECOBackend.model.user.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfiguration {

    private Converter<Date, LocalDate> dateToLocalDate = c -> {
        if(c.getSource() == null) return null;
        return Converters.convertToLocalDateViaInstant(c.getSource());
    };
    private Converter<LocalDate, Date> localDateToDate = c -> {
        if(c.getSource() == null) return null;
        return Converters.convertToDateViaInstant(c.getSource());
    };


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        configureUserMapper(modelMapper);
        configurePlaceMapper(modelMapper);
        configurePublicationMapper(modelMapper);
        return modelMapper;
    }

    private void configureUserMapper(ModelMapper modelMapper) {
        modelMapper.typeMap(User.class, UserDto.class).addMappings((mapper) -> {
            Converter<Organization, Long> organization = c -> {
                if(c.getSource() == null) return null;
                return c.getSource().getId();
            };

            Converter<String, String> password  = c -> "";
            mapper.using(organization).map(User::getOrganization, UserDto::setOrganizationId);
            mapper.using(password).map(User::getPassword, UserDto::setPassword);
        });
    }

    private void configurePublicationMapper(ModelMapper modelMapper) {
        modelMapper.typeMap(Publication.class, PublicationDto.class).addMappings((mapper) -> {

            Converter<List<TagsPublication>, List<TagDto>> tagsConverter = c -> {
                if(c.getSource() == null) return new ArrayList<>();
                return c.getSource().stream()
                        .map(tag -> modelMapper.map(tag.getTag(), TagDto.class))
                        .collect(Collectors.toList());
            };

            Converter<List<Favorite>, Long> favorites = c -> {
                if(c.getSource() == null) return 0L;
                return (long) c.getSource().size();
            };

            mapper.using(favorites).map(Publication::getFavorites, PublicationDto::setCountInFavorites);
            mapper.using(tagsConverter).map(Publication::getTags, PublicationDto::setTags);

        });
    }

    private void configurePlaceMapper(ModelMapper modelMapper) {
        modelMapper.typeMap(Place.class, PlaceDto.class).addMappings((mapper) -> {
            Converter<List<TagsPlace>, List<TagDto>> tagsConverter = c -> {
                if(c.getSource() == null) return new ArrayList<>();
                return c.getSource().stream()
                        .map(t -> modelMapper.map(t.getTag(), TagDto.class))
                        .collect(Collectors.toList());
            };

            mapper.using(tagsConverter).map(Place::getTags, PlaceDto::setTags);
        });
    }
}
