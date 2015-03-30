package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import com.gurps.cmdsocial.model.Post;

/**
 * Custom functionality other than that provided by sprint repositories
 * @author gurpiarbassi
 *
 */
public interface UserRepositoryCustom {

	Collection<Post> showWall(String username);
}
