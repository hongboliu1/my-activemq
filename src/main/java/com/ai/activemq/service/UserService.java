/**
 * 
 */
package com.ai.activemq.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.ai.activemq.model.User;

/**
 * Class Name		: UserService<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public interface UserService {

    public User getUserByName(String name);
    
    public void setUser(User user);
}
