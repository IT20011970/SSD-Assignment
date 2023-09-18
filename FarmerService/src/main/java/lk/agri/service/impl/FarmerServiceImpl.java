package lk.agri.service.impl;

import lk.agri.dto.ItemDTO;
import lk.agri.dto.CartDTO;
import lk.agri.entity.Chat;
import lk.agri.entity.Item;
import lk.agri.entity.CartDetail;
import lk.agri.repository.ChatReporitory;
import lk.agri.repository.ItemRepository;
import lk.agri.repository.CartRepository;
import lk.agri.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ChatReporitory chatReporitory;
    @Value("${image.path}")
    private String filePath;

    @Override
    public ItemDTO addItem(Item item, MultipartFile file) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        item.setItemId("I" + dateTime);
        addImage(item, file);
        return new ItemDTO(itemRepository.save(item));
    }

    @Override
    public List<ItemDTO> getItems(String email) {
        List<Item> items = itemRepository.findAllByUserAccountEmail(email);
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item));
        }
        return itemDTOS;
    }

    @Override
    public ItemDTO updateItem(String id, Item item, MultipartFile file) {
        Optional<Item> cardOptional = itemRepository.findById(id);
        if (cardOptional.isPresent()) {
            Item itemObj = cardOptional.get();
            itemObj.setDescription(item.getDescription());
            itemObj.setPrice(item.getPrice());
            itemObj.setQty(item.getQty());
            if (file != null) {
                addImage(item, file);
            }
            itemObj.setImgName(item.getImgName());
            itemObj.setImgType(item.getImgType());
            return new ItemDTO(itemRepository.save(itemObj));
        }
        return null;
    }

    @Override
    public Chat addChat(Chat chat) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        chat.setChatId("chat" + dateTime);
        return chatReporitory.save(chat) ;
    }

    @Override
    public List<Chat> getChat() {
        return chatReporitory.findAll();
    }

    private void addImage(Item item, MultipartFile file) {
        try {
            if (file != null) {
                String filePathCur = filePath + "\\items";
                Path root = Paths.get(filePathCur);
                if (!Files.exists(root)) {
                    Files.createDirectories(Paths.get(filePathCur));
                }
                try {
                    Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
                } catch (FileAlreadyExistsException e) {
                    e.printStackTrace();
                }
                item.setImgName("items/" + StringUtils.cleanPath(file.getOriginalFilename()));
                item.setImgType(file.getContentType());
            } else {
                item.setImgName(null);
                item.setImgType(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean removeItem(String id) {
        itemRepository.deleteById(id);
        return true;
    }


}
