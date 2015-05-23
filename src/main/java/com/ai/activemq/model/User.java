/**
 * 
 */
package com.ai.activemq.model;

import java.io.Serializable;

/**
 * Class Name		: User<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = 6324161229278542742L;

    private Long Id;
    private String userName;
    
    private String address;
    
    private String email; 

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
