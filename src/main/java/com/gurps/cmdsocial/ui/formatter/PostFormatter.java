package com.gurps.cmdsocial.ui.formatter;

import com.gurps.cmdsocial.model.Post;

/**
 * Responsible for formating posts for output display
 * @author gurpiarbassi
 *
 */
public interface PostFormatter {

	String formatPost(Post post, boolean isUserAware);
}
