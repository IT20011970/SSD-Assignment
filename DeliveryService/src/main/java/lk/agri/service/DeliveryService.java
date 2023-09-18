package lk.agri.service;

import lk.agri.dto.CartDTO;
import lk.agri.dto.ItemDTO;
import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.*;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getDeliveries();
}
