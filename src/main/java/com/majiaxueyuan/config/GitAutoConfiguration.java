package com.majiaxueyuan.config;

import com.alibaba.fastjson.JSON;
import com.majiaxueyuan.constans.CdnType;
import com.majiaxueyuan.core.CdnServer;
import com.majiaxueyuan.core.GiteeCdnServer;
import com.majiaxueyuan.core.GithubCdnServer;
import com.majiaxueyuan.properties.GitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liao
 */
@Configuration
@EnableConfigurationProperties(GitProperties.class)
@ConditionalOnProperty(value = "mj.cdn.enabled", havingValue = "true", matchIfMissing = false)
public class GitAutoConfiguration {

    private final GitProperties gitProperties;

    public GitAutoConfiguration(GitProperties gitProperties) {
        System.out.println("配置属性" + JSON.toJSONString(gitProperties));
        if (gitProperties.getDefaultBranch() == null || "".equals(gitProperties.getDefaultBranch())) {
            throw new IllegalArgumentException("必须配置参数 mj.cdn.default-branch 用于没有的文件映射分支存储");
        }
        if (!Enum.class.isAssignableFrom(gitProperties.getMappingClass())) {
            throw new IllegalArgumentException(gitProperties.getMappingClass().getName() + "必须是一个枚举类,并且实现接口：com.majiaxueyuan.constans.BranchFileType");
        }
        this.gitProperties = gitProperties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "cdnServer")
    @ConditionalOnExpression("${mj.cdn.enabled:false}")
    public CdnServer buildGitee() {
        CdnServer cdnServer;
        if (gitProperties.getType().equals(CdnType.GITEE)) {
            cdnServer = new GiteeCdnServer(gitProperties.getAccessToken(), gitProperties.getUserId(), gitProperties.getRepos(), gitProperties.getDefaultBranch(), gitProperties.getMappingClass());
        } else if (gitProperties.getType().equals(CdnType.GITHUB)) {
            cdnServer = new GithubCdnServer(gitProperties.getAccessToken(), gitProperties.getUserId(), gitProperties.getRepos(), gitProperties.getDefaultBranch(), gitProperties.getMappingClass());
        } else {
            throw new IllegalArgumentException("配置mj.cdn.type必须是枚举com.majiaxueyuan.constans.CdnType中的类型");
        }
        return cdnServer;
    }
}