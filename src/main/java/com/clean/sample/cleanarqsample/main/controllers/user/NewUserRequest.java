package com.clean.sample.cleanarqsample.main.controllers.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewUserRequest {
    @NotBlank
    @NotNull
	public String name;
    @NotBlank
    @NotNull
	public String password;
}
