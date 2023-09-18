package lk.agri.service;

import lk.agri.dto.PurchaseDTO;
import lk.agri.entity.Purchase;

public interface BankService {

    public Purchase addPayment(Purchase purchase);
}
