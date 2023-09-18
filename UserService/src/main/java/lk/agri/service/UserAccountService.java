package lk.agri.service;

import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.UserAccount;

public interface UserAccountService {
    UserAccountDTO login(UserAccount userAccount);

    UserAccountDTO signUp(UserAccount userAccount);

}
