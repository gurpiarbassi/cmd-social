package convertors;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Custom convertor to convert Legacy Date object to LocalDateTime object
 * 
 * @author gurpiarbassi
 *
 */
@Component
public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

	@Override
	public LocalDateTime convert(Date source) {
		return source == null ? null : LocalDateTime.ofInstant(source.toInstant(),
				ZoneId.systemDefault());
	}

}
