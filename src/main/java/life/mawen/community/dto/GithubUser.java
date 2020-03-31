package life.mawen.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    // 头像
    private String avatar_url;

}
