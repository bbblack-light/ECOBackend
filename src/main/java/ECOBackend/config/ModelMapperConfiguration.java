package ECOBackend.config;

import ECOBackend.controllers.utils.Converters;
import ECOBackend.dto.*;
import ECOBackend.model.*;
import ECOBackend.repo.UserRepo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        return modelMapper;
    }
}
