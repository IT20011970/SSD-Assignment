package lk.agri.service;

import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.UserAccount;

public interface UserAccountService {
    UserAccountDTO login(UserAccount userAccount);
    UserAccount loggedUser(String email);
    UserAccountDTO signUp(UserAccount userAccount);

}
