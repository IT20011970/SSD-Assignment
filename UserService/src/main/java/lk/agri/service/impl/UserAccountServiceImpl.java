package lk.agri.service.impl;

import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.UserAccount;
import lk.agri.repository.UserAccountRepository;
import lk.agri.service.UserAccountService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccountDTO login(UserAccount userAccount) {
        byte[] encodedBytes = Base64.encodeBase64(userAccount.getPassword().getBytes());
        userAccount.setPassword(new String(encodedBytes));
        UserAccount userAccountObj = userAccountRepository.findByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword());
        return new UserAccountDTO(userAccountObj);
    }

    @Override
    public UserAccountDTO signUp(UserAccount userAccount) {
        byte[] encodedBytes = Base64.encodeBase64(userAccount.getPassword().getBytes());
        userAccount.setPassword(new String(encodedBytes));
        UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountRepository.save(userAccount));
        userAccountDTO.setPassword(null);
        return userAccountDTO;
    }
}
