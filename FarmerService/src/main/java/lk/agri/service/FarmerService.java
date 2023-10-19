package lk.agri.service;

import lk.agri.dto.ItemDTO;
import lk.agri.dto.CartDTO;
import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.Chat;
import lk.agri.entity.Item;
import lk.agri.entity.CartDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FarmerService {

    ItemDTO addItem(Item item, MultipartFile file);

    List<ItemDTO> getItems(String email);

    ItemDTO updateItem(String ID, Item item, MultipartFile file);

    public Chat addChat(Chat chat);
    public List<Chat> getChat();
    boolean removeItem(String id);
    UserAccountDTO loggedUser(String email);
}
