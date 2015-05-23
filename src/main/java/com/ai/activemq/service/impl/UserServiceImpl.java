/**
 * 
 */
package com.ai.activemq.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.ai.activemq.model.User;
import com.ai.activemq.service.UserService;

/**
 * Class Name		: UserServiceImpl<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

    /**
     * Method Name		: getUserByName<br>
     * 
     * Description		: 这里记述method说明<br>
     *  
     * @param name
     * @return
     * @since
     * 
     */
    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setId(Calendar.getInstance().getTimeInMillis());
        user.setUserName(name);
        user.setAddress("china");
        user.setEmail(name + "@hoo.com");
        return user;
    }

    /**
     * Method Name		: setUser<br>
     * 
     * Description		: 这里记述method说明<br>
     *  
     * @param user
     * @since
     * 
     */
    @Override
    public void setUser(User user) {
        System.out.println("############Server setUser###########");
        System.out.println("setUser:" + user);
    }
}
