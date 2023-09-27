package lk.agri.service.impl;

import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.UserAccount;
import lk.agri.repository.UserAccountRepository;
import lk.agri.service.UserAccountService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);


    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccountDTO login(UserAccount userAccount) {
        byte[] encodedBytes = Base64.encodeBase64(userAccount.getPassword().getBytes());
        userAccount.setPassword(new String(encodedBytes));
        UserAccount userAccountObj = userAccountRepository.findByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword());
        Optional<UserAccount> userUpdate= Optional.ofNullable(userAccountRepository.findByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword()));
        if (userUpdate !=null) {
            UserAccount userObj = userUpdate.get();
            String token=(new Random().nextInt(10000)+1)+"-"+(new Random().nextInt(10000)+1)+"-"+(new Random().nextInt(10000)+1)+"-"+(new Random().nextInt(10000)+1);
            userObj.setToken(token);
            userAccountRepository.save(userObj);
        }
        return new UserAccountDTO(userAccountObj);
    }

    @Override
    public UserAccountDTO signUp(UserAccount userAccount) {
        try {
            // Specify the character encoding (e.g., UTF-8)
            String encoding = "UTF-8";

            byte[] encodedBytes = Base64.encodeBase64(userAccount.getPassword().getBytes(encoding));
            userAccount.setPassword(new String(encodedBytes, encoding));

            UserAccountDTO userAccountDTO = new UserAccountDTO(userAccountRepository.save(userAccount));
            userAccountDTO.setPassword(null);
            return userAccountDTO;
        } catch (UnsupportedEncodingException e) {
            // Handle the exception appropriately (e.g., log an error)
            logger.error("An error occurred:", e);
            return null; // or throw an exception
        }
    }
}
