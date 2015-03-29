package com.gurps.cmdsocial.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Post {

	private String message;
	private LocalDateTime creationDateTime;
}
