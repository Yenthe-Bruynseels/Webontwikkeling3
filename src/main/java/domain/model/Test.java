package domain.model;

import java.sql.Date;

public class Test {
    private String userid;
    private Date date;

    public Test(){};

    public Test(String userid, Date date) {
        setUserid(userid);
        setDate(date);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid == null) throw new DomainException("You have to log in first."); //userid wordt uit user in Session gehaald, user moet ingelogd zijn om een test te kunnen registreren. Technisch gezien zou door authorisatie deze error nooit aan bod mogen komen
        this.userid = userid;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        if (date == null) throw new DomainException("Date can't be null");
        this.date = date;
    }
}
