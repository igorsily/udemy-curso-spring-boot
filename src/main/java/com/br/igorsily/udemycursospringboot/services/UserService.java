package com.br.igorsily.udemycursospringboot.services;

import com.br.igorsily.udemycursospringboot.entitys.User;

public interface UserService {

    User findByUsername(String username);
}
