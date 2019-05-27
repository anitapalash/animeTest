package com.koposovo.animeTest.Service.Concrete;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import com.koposovo.animeTest.Service.Abstract.IUserService;
import com.koposovo.animeTest.api.IUserRepository;
import com.koposovo.animeTest.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private IUserRepository IuserRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Iterator<User> iterator = IuserRepository.findAll().iterator();
        while (iterator.hasNext()) {
            users.add(iterator.next());
        }

        return users;
    }

    @Override
    public User getUser(Long userId) {
        return IuserRepository.findById(userId).get();
    }

    @Override
    public User createUser(User user) {
        return IuserRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return IuserRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        IuserRepository.deleteById(userId);
    }


}
