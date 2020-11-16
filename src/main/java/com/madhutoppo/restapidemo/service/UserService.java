package com.madhutoppo.restapidemo.service;

import com.madhutoppo.restapidemo.entity.User;

public interface UserService {
    User getUserById(Integer userId);

    User saveUser(User user);
}
