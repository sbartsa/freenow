package com.freenow.test.helper.dto

import org.json.JSONObject

class User {

    int id
    String username
    String email
    JSONObject address
    String phone
    String website
    JSONObject company


    static User mapJsonToUser(JSONObject jsonObject) {
        User user = new User()
        user.setId((Integer) jsonObject.get("id"))
        user.setEmail(jsonObject.get("email").toString())
        user.setUsername(jsonObject.get("username").toString())
        // TODO map the rest of the resources
        return user
    }
}
