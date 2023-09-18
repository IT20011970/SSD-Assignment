package lk.agri.service;

public interface SendEmailSMTP {

    void sendEmail(String emailTo, String emailSubject, String emailText);

}
