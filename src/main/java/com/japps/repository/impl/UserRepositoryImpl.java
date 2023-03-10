package com.japps.repository.impl;

import com.japps.constants.FileConstant;
import com.japps.model.dto.UserDto;
import com.japps.model.entity.User;
import com.japps.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_USER = "user";

    @Override
    public User findUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class, COLLECTION_USER);
    }

    @Override
    public long updateRecentLogUpdateTime(String username, String time) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set("recent_log_update_time", time);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class, COLLECTION_USER);
        return updateResult.getMatchedCount();
    }

    @Override
    public String insertOneUser(UserDto userDto) {
        User user = new User(userDto);
        user.setRecent_log_update_time("0");
        user.setPa_threshold(FileConstant.DEFAULT_PA_THRESHOLD);
        User result = mongoTemplate.insert(user, COLLECTION_USER);
        return result == null ? null : result.get_id();
    }

    @Override
    public List<User> findAllUser() {
        return mongoTemplate.findAll(User.class, COLLECTION_USER);
    }

    @Override
    public long updateInfoFileUpdateTime(String username, String time) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set("info_file_update_time", time);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class, COLLECTION_USER);
        return updateResult.getMatchedCount();
    }
}
