package com.majiaxueyuan.properties;

import com.majiaxueyuan.constans.BranchFileType;
import com.majiaxueyuan.constans.CdnType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 开启配置属性
 *
 * @author Liao
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mj.cdn")
public class GitProperties {

    private Boolean enabled;
    private CdnType type;
    private String accessToken;
    private String userId;
    private String repos;
    private String defaultBranch;
    private Class<? extends BranchFileType> mappingClass;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public CdnType getType() {
        return type;
    }

    public void setType(CdnType type) {
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public Class<? extends BranchFileType> getMappingClass() {
        return mappingClass;
    }

    public void setMappingClass(Class<? extends BranchFileType> mappingClass) {
        this.mappingClass = mappingClass;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }
}