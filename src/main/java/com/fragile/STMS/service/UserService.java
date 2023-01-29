package com.fragile.STMS.service;

import com.fragile.STMS.dto.ResponseDto;
import com.fragile.STMS.entity.User;

public interface UserService {

   ResponseDto signUp(User user);

   ResponseDto signIn(User user);


}
