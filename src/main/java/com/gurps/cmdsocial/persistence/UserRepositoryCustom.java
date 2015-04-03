package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;

/**
 * Custom functionality other than that provided by sprint repositories
 * @author gurpiarbassi
 *
 */
public interface UserRepositoryCustom {

	Collection<Post> showWall(User user);
}
