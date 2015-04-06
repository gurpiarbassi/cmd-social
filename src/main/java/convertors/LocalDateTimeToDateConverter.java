package convertors;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Custom convertor to convert LocalDateTime to legacy Date object
 * 
 * @author gurpiarbassi
 *
 */
@Component
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {

	@Override
	public Date convert(LocalDateTime source) {
		return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
	}
}
