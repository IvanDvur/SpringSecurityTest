package com.example.httpbasic;

import com.example.httpbasic.models.Client;
import com.example.httpbasic.repos.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserPrincipalDetailsService implements UserDetailsService {

    private ClientRepository clientRepository;

    public UserPrincipalDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = this.clientRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(client);

        return userPrincipal;
    }
}
