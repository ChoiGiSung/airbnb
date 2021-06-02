package com.codesquad.coco.utils.sql;


const val INSERT_USER: String = """

insert into users(github_id,login,email,profile_image_url,access_token)
values (:github_id,:login,:email,:profile_image_url,:access_token)

"""

const val UPDATE_USER_BY_GITHUB_ID: String = """

update users set  login = :login ,email = :email ,profile_image_url = :profile_image_url ,access_token = :access_token 
where github_id = :github_id 

"""

const val FIND_USER_COUNT_BY_GITHUB_ID: String = """

select count(github_id)
from users
where github_id = :github_id

"""


const val FIND_BY_GITHUB_ID: String = """

select id,github_id,login,email,profile_image_url
from users
where github_id = :github_id

"""