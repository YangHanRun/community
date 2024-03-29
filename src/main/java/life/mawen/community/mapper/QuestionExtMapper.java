package life.mawen.community.mapper;



import life.mawen.community.dto.QuestionQueryDTO;
import life.mawen.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    /* 根据标签查询帖子 */
    List<Question> selectRelated(Question question);


    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);

}