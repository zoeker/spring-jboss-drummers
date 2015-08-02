package com.moderndrummer.searchdomain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/***
 * 
 * 
 * 
 * @author conpem
 *
 */
public class SearchModel implements Serializable {
    
    @DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
    private Date fromDate;
    
    @DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
    private Date toDate;
    
    private String name;
    
    private String password;
    
    
    public Date getToDate() {
        return toDate;
    }
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
    public Date getFromDate() {
        return fromDate;
    }
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    public String getUserName() {
      return name;
    }
    public void setUserName(String userName) {
      this.name = userName;
    }
    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
    }
    

}
