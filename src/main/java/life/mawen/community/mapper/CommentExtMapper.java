package life.mawen.community.mapper;

import life.mawen.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}