package lk.agri.security;

import lk.agri.dto.UserAccountDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class JwtUtil {

//    private String secret = "AGGBXYUSBDUY";
//    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 18; //18 hours

    public String generate(UserAccountDTO user,String accType) {
        JSONObject obj = new JSONObject();

        obj.put("username", Encryption.decrypt(user.getEmail()));
        obj.put("accType", accType);
        obj.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        return Encryption.encrypt(obj.toString());
    }

    public String decode(String token) {
        token = Encryption.decrypt(token);
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(token);
            return json.get("accType").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
