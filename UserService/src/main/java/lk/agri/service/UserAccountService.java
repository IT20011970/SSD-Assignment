package lk.agri.service;

import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.UserAccount;

public interface UserAccountService {
    UserAccountDTO login(UserAccount userAccount);
    UserAccountDTO loggedUser(String email);
    UserAccountDTO signUp(UserAccount userAccount);
    UserAccountDTO loginGoogle(UserAccount userAccount);
}
