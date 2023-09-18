package lk.agri.service.impl;

import lk.agri.dto.CartDTO;
import lk.agri.dto.CartDetailDTO;
import lk.agri.dto.ItemDTO;
import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.Cart;
import lk.agri.entity.CartDetail;
import lk.agri.entity.Item;
import lk.agri.entity.UserAccount;
import lk.agri.repository.CartDetailRepository;
import lk.agri.repository.CartRepository;
import lk.agri.repository.ItemRepository;
import lk.agri.repository.UserAccountRepository;
import lk.agri.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<ItemDTO> getItems(String txt) {
        List<Item> items;
        if (txt.equals("undefined")) {
            items = itemRepository.findAll();
        } else {
            items = itemRepository.findAllByDescription(txt);
        }
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item));
        }
        return itemDTOS;
    }

    @Override
    public boolean addToCart(CartDetail cartDetail) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Optional<Cart> cartPrev = cartRepository.findByPurchasedAndUserAccountEmail(false, cartDetail.getUserAccount().getEmail());
        Cart cartObj;
        if (!cartPrev.isPresent()) {
            Cart cart = new Cart();
            cart.setCartId("C" + format);
            cart.setUserAccount(cartDetail.getUserAccount());
            cartObj = cartRepository.save(cart);
        } else {
            cartObj = cartPrev.get();
        }
        Optional<Item> itemOptional = itemRepository.findById(cartDetail.getItem().getItemId());
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setQty(item.getQty() - cartDetail.getQuantity());
            itemRepository.save(item);
        }
        cartDetail.setCartDetailId("CD" + format);
        cartDetail.setCart(cartObj);
        cartDetailRepository.save(cartDetail);
        return true;
    }

    @Override
    public CartDTO getCart(String email) {
        Optional<Cart> cartOpt = cartRepository.getCart(email);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            List<CartDetailDTO> cartDetailDTOS = new ArrayList<>();
            for (CartDetail cartDetail : cart.getCartDetails()) {
                cartDetailDTOS.add(new CartDetailDTO(cartDetail));
            }
            CartDTO cartDTO = new CartDTO(cart);
            cartDTO.setCartDetails(cartDetailDTOS);
            return cartDTO;
        }
        return null;
    }

    @Override
    public boolean addCart(Cart cart) {
        Optional<Cart> cartPrev = cartRepository.findByPurchasedAndUserAccountEmail(false, cart.getUserAccount().getEmail());
        if (cartPrev.isPresent()) {
            Cart cartObj = cartPrev.get();
            cartObj.setDelivery(cart.getDelivery());
            cartObj.setPurchasedAt(LocalDate.now());
            cartObj.setPurchased(true);
            cartObj.setPayId(cart.getPayId());
            cartRepository.save(cartObj);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCartDetail(String id) {
        cartDetailRepository.deleteById(id);
        return true;
    }

//    @Override
//    public UserAccountDTO updateTrader(String nic, UserAccount userAccount) {
//        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(nic);
//        if (userAccountOptional.isPresent()) {
//            UserAccount userAccountobj = userAccountOptional.get();
//            userAccountobj.setPassword(userAccount.getPassword());
//            userAccountobj.setAddress(userAccount.getAddress());
//            userAccountobj.setName(userAccount.getName());
//            userAccountobj.setEmail(userAccount.getEmail());
//            userAccountobj.setContactNo(userAccount.getContactNo());
//            return new UserAccountDTO(userAccountRepository.save(userAccountobj));
//        }
//        return null;
//    }
//
//    @Override
//    public List<ItemDTO> getAllCards(String nic) {
//        List<Item> items = itemRepository.findAllByUserAccountEmail(nic);
//        List<ItemDTO> cardDTOS = new ArrayList<>();
//
//        for (Item item : items) {
//            cardDTOS.add(new ItemDTO(item));
//        }
//        return cardDTOS;
//    }
//
//    @Override
//    public ItemDTO updateItem(String ID, Item item) {
//        Optional<Item> cardOptional = itemRepository.findById(ID);
//        if (cardOptional.isPresent()) {
//            Item itemObj = cardOptional.get();
//            itemObj.setDescription(item.getDescription());
////            itemObj.setImage(item.getImage());
//            itemObj.setPrice(item.getPrice());
//            return new ItemDTO(itemRepository.save(itemObj));
//        }
//        return null;
//    }
//
//    @Override
//    public UserAccountDTO addAccount(UserAccount userAccount) {
//        return new UserAccountDTO(userAccountRepository.save(userAccount));
//    }
//
//    @Override
//    public ItemDTO addItem(Item item) {
//        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
//        item.setItemId("Item" + dateTime);
//        item.setUserAccount(item.getUserAccount());
//
//        return new ItemDTO(itemRepository.save(item));
//    }

}
