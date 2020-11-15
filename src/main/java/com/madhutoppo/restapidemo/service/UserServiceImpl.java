package com.madhutoppo.restapidemo.service;

import com.madhutoppo.restapidemo.entity.User;
import com.madhutoppo.restapidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer id) {
        return null;
    }
}
