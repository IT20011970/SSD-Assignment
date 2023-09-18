package lk.agri.service;

import lk.agri.dto.CartDTO;
import lk.agri.dto.ItemDTO;
import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.Cart;
import lk.agri.entity.CartDetail;
import lk.agri.entity.Item;
import lk.agri.entity.UserAccount;

import java.util.List;

public interface BuyerService {

    List<ItemDTO> getItems(String txt);

    boolean addToCart(CartDetail cart);

    CartDTO getCart(String email);

    boolean addCart(Cart cart);

    boolean removeCartDetail(String id);

//    UserAccountDTO updateTrader(String aplicationID, UserAccount userAccount);
//
//    public UserAccountDTO addAccount(UserAccount userAccount);
//
//    public ItemDTO addItem(Item item);
//
//    public List<ItemDTO> getAllCards(String nic);
//
//    public ItemDTO updateItem(String ID, Item item);
}
