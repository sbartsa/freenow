package com.freenow.test.helper.dto

import org.json.JSONObject

class Comment {

    int postId
    int id
    String name
    String email
    String body

    static Comment mapJsonToComment(JSONObject jsonObject) {
        Comment comment = new Comment()
        comment.setPostId((Integer) jsonObject.get("postId"))
        comment.setId((Integer) jsonObject.get("id"))
        comment.setName(jsonObject.get("name").toString())
        comment.setEmail(jsonObject.get("email").toString())
        comment.setBody(jsonObject.get("body").toString())
        return comment
    }

}
