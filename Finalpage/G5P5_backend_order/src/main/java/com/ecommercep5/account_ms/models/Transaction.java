package com.ecommercep5.account_ms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Transaction {
    @Id
    private String  id;
    private String  usernameOrigin;
    private String  usernameDestiny;
    private Integer value;
    private String  note;
    private Date    date;

    public Transaction(String id, String usernameOrigin, String usernameDestiny, Integer value,
                       String note, Date date){
        this.id              = id;
        this.usernameOrigin  = usernameOrigin;
        this.usernameDestiny = usernameDestiny;
        this.value           = value;
        this.note            = note;
        this.date            = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameOrigin() {
        return usernameOrigin;
    }

    public void setUsernameOrigin(String usernameOrigin) {
        this.usernameOrigin = usernameOrigin;
    }

    public String getUsernameDestiny() {
        return usernameDestiny;
    }

    public void setUsernameDestiny(String usernameDestiny) {
        this.usernameDestiny = usernameDestiny;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
