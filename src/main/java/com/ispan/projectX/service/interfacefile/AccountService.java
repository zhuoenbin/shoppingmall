package com.ispan.projectX.service.interfacefile;

import com.ispan.projectX.dto.Passport;
import com.ispan.projectX.entity.Users;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AccountService {

    public boolean checkEmailIsEmpty(String email);

    public void register(Users user);

    public Passport getPassportFromFormLogin(String username);

    public Passport getPassportFromOauth2Login(OAuth2User oAuth2User);

    public String encodePassword(String password);

    public void sendCodeForResetPassword(String email);

    public boolean verifyCodeForResetPassword(String email, String code);
}
