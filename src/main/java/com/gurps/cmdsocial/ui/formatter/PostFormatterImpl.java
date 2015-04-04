package com.gurps.cmdsocial.ui.formatter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.stereotype.Component;

import com.gurps.cmdsocial.model.Post;

@Component(value = "basicFormatter")
public class PostFormatterImpl implements PostFormatter {

	@Override
	public String formatPost(final Post post, final boolean isUserAware) {
		StringBuilder builder = new StringBuilder();
		if (isUserAware) {
			builder.append(post.getUserId() + " - " + " ");
		}
		builder.append(post.getMessage() + " " + getAge(post.getPostDate()));
		return builder.toString();
	}

	/**
	 * Every post has a age in brackets when it is output. So we calculate this
	 * based on the insertion date/time of the post and the current date/time.
	 * 
	 * @param postDateTime
	 * @return A String to display as the age component of the post.
	 */
	private String getAge(final LocalDateTime postDateTime) {

		final LocalDateTime nowDateTime = LocalDateTime.now();
		final LocalDate nowDate = nowDateTime.toLocalDate();

		final Period periodBetween = Period.between(postDateTime.toLocalDate(), nowDate);
		final int yearsBetween = periodBetween.getYears();
		final int monthsBetween = periodBetween.getMonths();
		final int daysBetween = periodBetween.getDays();

		String formattedElapsedValue = "";

		if (yearsBetween > 0) {
			formattedElapsedValue = "(" + String.valueOf(yearsBetween) + " years ago)";
		} else if (monthsBetween > 0) {
			formattedElapsedValue = "(" + String.valueOf(monthsBetween) + "months ago)";
		} else if (daysBetween > 0) {
			formattedElapsedValue = "(" + String.valueOf(daysBetween) + " days ago)";
		} else {
			// time based calculation required.
			final long secondsBetween = Duration.between(postDateTime, nowDateTime).getSeconds();
			final long seconds = secondsBetween % 60;
			final long totalMinutes = secondsBetween / 60;
			final long minutes = totalMinutes % 60;
			final long hours = totalMinutes / 60;

			if (hours > 0) {
				formattedElapsedValue = "(" + String.valueOf(hours) + " hours ago)";
			} else if (minutes > 0) {
				formattedElapsedValue = "(" + String.valueOf(minutes) + " minutes ago)";
			} else {
				formattedElapsedValue = "(" + String.valueOf(seconds) + " seconds ago)";
			}
		}

		return formattedElapsedValue;
	}

}
