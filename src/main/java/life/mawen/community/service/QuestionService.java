package life.mawen.community.service;

import life.mawen.community.dto.PaginationDTO;
import life.mawen.community.dto.QuestionDTO;
import life.mawen.community.mapper.QuestionMapper;
import life.mawen.community.mapper.UserMapper;
import life.mawen.community.model.Question;
import life.mawen.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();


        // 总页数
        if (totalCount % size == 0){
            totalPage =totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        if (page < 1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }


        Integer offset = size * (page -1);
        List<Question> questions= questionMapper.list(offset,size);
        // 查询所有数据
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 将question对象的属性值复制到questionDTO对象中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 分页
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        // 总页数
        if (totalCount % size == 0){
            totalPage =totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        if (page < 1){
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }


        Integer offset = size * (page -1);
        List<Question> questions= questionMapper.listByUserId(userId,offset,size);
        // 查询所有数据
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 将question对象的属性值复制到questionDTO对象中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        // 分页
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void CreateOrUpdate(Question question) {

        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
