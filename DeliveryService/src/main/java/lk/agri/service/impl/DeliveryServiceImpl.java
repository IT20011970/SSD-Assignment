package lk.agri.service.impl;

import lk.agri.dto.CartDTO;
import lk.agri.dto.CartDetailDTO;
import lk.agri.dto.ItemDTO;
import lk.agri.dto.UserAccountDTO;
import lk.agri.entity.*;
import lk.agri.repository.DeliveryRepository;
import lk.agri.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }
}
