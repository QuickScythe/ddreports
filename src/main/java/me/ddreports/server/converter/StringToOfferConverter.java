package me.ddreports.server.converter;

import me.ddreports.data.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOfferConverter implements Converter<String, Offer> {
    private static final Logger logger = LoggerFactory.getLogger(StringToOfferConverter.class);

    @Override
    public Offer convert(String source) {
        logger.info(source);
        Offer offer = new Offer();
        // Set properties of offer based on the source string
        return offer;
    }
}