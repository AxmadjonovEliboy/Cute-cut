package uz.pdp.cutecutapp.dto.auth;

import java.util.List;

public class RegisterResponse {

    public String id;
    public String href;
    public String direction;
    public String type;
    public String originator;
    public String body;
    public String reference;
    public String validity;
    public String gateway;
    public String datacoding;
    public String mclass;
    public String createdDatetime;

    public List<Recipient> recipients;


    public static class Recipient {
        public Integer totalCount;
        public Integer totalSentCount;
        public Integer totalDeliveredCount;
        public Integer totalDeliveryFailedCount;

        public List<Item> items;

    }

    public static class Item {
        public String recipient;
        public String status;
        public String statusDatetime;
        public Integer messagePartCount;
    }
}
