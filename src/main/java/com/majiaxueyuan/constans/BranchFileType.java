package com.majiaxueyuan.constans;

import java.util.List;

/**
 * @author Liao
 */
public interface BranchFileType {

    /**
     * 通过文件类型获取到对应的分支
     */
    String getBranchName();

    /**
     * 通过分支拿到对应存储的文件类型
     *
     * @param branch 分支
     * @return 文件类型列表
     */
    List<String> getFileTypesByBranch(String branch);

}
