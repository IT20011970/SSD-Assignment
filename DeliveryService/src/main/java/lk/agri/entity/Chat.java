package lk.agri.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chat {
    @Id
    private String chatId;
    private String chatterName;
    private String msg;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatterName() {
        return chatterName;
    }

    public void setChatterName(String chatterName) {
        this.chatterName = chatterName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
