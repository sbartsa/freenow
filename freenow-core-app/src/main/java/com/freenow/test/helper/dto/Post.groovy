package com.freenow.test.helper.dto

import org.json.JSONObject

class Post {

    int userId
    int id
    String title
    String body

    static Post mapJsonToPost(JSONObject jsonObject) {
        Post post = new Post()
        post.setUserId((Integer) jsonObject.get("userId"))
        post.setId((Integer) jsonObject.get("id"))
        post.setTitle(jsonObject.get("title").toString())
        post.setBody(jsonObject.get("body").toString())
        return post
    }
}
