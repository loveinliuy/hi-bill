package loveinliuy.bill.service;

import loveinliuy.bill.model.User;
import loveinliuy.bill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/15].
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username [" + username + "] not found");
        }
        return user;
    }
}
