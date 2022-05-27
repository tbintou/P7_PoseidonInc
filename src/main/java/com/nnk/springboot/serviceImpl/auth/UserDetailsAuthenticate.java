package com.nnk.springboot.serviceImpl.auth;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.dto.UserRegistrationDTO;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsAuthenticate implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username" + username);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthorities(user)).build();

        return userDetails;
    }

   /* public User save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setFullname(userRegistrationDTO.getFullname());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setRole(userRegistrationDTO.getRole());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        return userRepository.save(user);
    }*/

    private Collection<GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(2);
        if (user.getRole().equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else if (user.getRole().equals("USER")) {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        return authorities;
    }
}
