package com.majiaxueyuan.vo;


/**
 * git接口上传反馈规范
 */
public class RespContent {
    // 文件名
    private String name;
    // git路径
    private String path;
    // 文件字节长度
    private Long size;
    // 文件sha码
    private String sha;
    // 上传API地址
    private String url;
    // html打开地址
    private String html_url;
    // 下载地址
    private String download_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
