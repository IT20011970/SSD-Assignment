package lk.agri.service.impl;

import lk.agri.entity.Purchase;
import lk.agri.repository.PurchaseRepository;
import lk.agri.service.BankService;
import lk.agri.service.SendEmailSMTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BankServiceimpl implements BankService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SendEmailSMTP sendEmailSMTP;

    @Override
    public Purchase addPayment(Purchase purchase) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        purchase.setTransactionID("T" + dateTime);
        Purchase purchaseObj = purchaseRepository.save(purchase);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendEmailSMTP.sendEmail(purchaseObj.getEmail(), "Payment Success",
                        "You are successfully paid for the cart. Your payment ID is " + purchase.getTransactionID());
            }
        }).start();
        return purchase;

    }
}
