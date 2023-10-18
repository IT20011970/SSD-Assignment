package lk.agri.service.impl;

import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.UserAccount;
import lk.agri.repository.UserAccountRepository;
import lk.agri.security.Encryption;
import lk.agri.security.JwtUtil;
import lk.agri.service.UserAccountService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserAccountDTO login(UserAccount userAccount) {
        userAccount.setEmail(Encryption.encrypt(userAccount.getEmail()));
        userAccount.setPassword(Encryption.encrypt(userAccount.getPassword()));
//        UserAccount userAccountObj = userAccountRepository.findByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword());
        Optional<UserAccount> userUpdate = Optional.ofNullable(userAccountRepository.findByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword()));
        if (userUpdate != null) {
            UserAccount userObj = userUpdate.get();
            String token = (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1);
            userObj.setToken(token);
            UserAccount userAccountObj = userAccountRepository.save(userObj);

            UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountObj);
            userAccountDTO.setUserToken(jwtUtil.generate(userAccountDTO, jwtUtil.decode(userAccountDTO.getAccountType())));
            userAccountDTO.setAccountType(jwtUtil.decode(userAccountObj.getAccountType()));
            return userAccountDTO;
        }
        return null;
    }

    @Override
    public UserAccountDTO loggedUser(String email) {
        email = Encryption.encrypt(email);
        Optional<UserAccount> userUpdate = Optional.ofNullable(userAccountRepository.findByEmail(email));
        if (userUpdate != null) {
            UserAccount userObj = userUpdate.get();
            String token = (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1);
            userObj.setToken(token);
            userAccountRepository.save(userObj);
        }
        UserAccount userAccountObj = userAccountRepository.findByEmail(email);
        UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountObj);
        userAccountDTO.setUserToken(jwtUtil.generate(userAccountDTO, jwtUtil.decode(userAccountDTO.getAccountType())));
        userAccountDTO.setAccountType(jwtUtil.decode(userAccountObj.getAccountType()));
        return userAccountDTO;
    }

    @Override
    public UserAccountDTO signUp(UserAccount userAccount) {
        userAccount.setEmail(Encryption.encrypt(userAccount.getEmail()));
        userAccount.setAccountType(jwtUtil.generate(new UserAccountDTO(userAccount), userAccount.getAccountType()));
        UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountRepository.save(userAccount));
        userAccountDTO.setPassword(null);
        return userAccountDTO;
    }

    @Override
    public UserAccountDTO loginGoogle(UserAccount userAccount) {
        userAccount.setEmail(Encryption.encrypt(userAccount.getEmail()));
        Optional<UserAccount> userUpdate = Optional.ofNullable(userAccountRepository.findByEmail(userAccount.getEmail()));
        if (userUpdate != null) {
            UserAccount userObj = userUpdate.get();
            String token = (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1) + "-" + (new Random().nextInt(10000) + 1);
            userObj.setToken(token);
            UserAccount userAccountObj = userAccountRepository.save(userObj);

            UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountObj);
            userAccountDTO.setUserToken(jwtUtil.generate(userAccountDTO, jwtUtil.decode(userAccountDTO.getAccountType())));
            userAccountDTO.setAccountType(jwtUtil.decode(userAccountObj.getAccountType()));
            return userAccountDTO;
        }
        return null;
    }
}
