package com.majiaxueyuan.constans;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Liao
 */

public enum BranchFileTypeImpl implements BranchFileType {
    /**
     * 分支和文件类型
     */
    BRANCH_PIC("pic", new String[]{
            "jpg", "png", "jpeg", "gif", "svg", "webp"
    }),
    BRANCH_DOC("doc", new String[]{
            "pdf", "doc", "docx", "ppt", "pptx", "xls", "xlsx"
    });


    private final String branch;
    private final List<String> fileTypes;

    BranchFileTypeImpl(String branch, String[] fileTypes) {
        this.branch = branch;
        this.fileTypes = Arrays.asList(fileTypes);
    }

    public String getBranch() {
        return branch;
    }

    public List<String> getFileTypes() {
        return fileTypes;
    }

    @Override
    public String getBranchName() {
        return getBranch();
    }

    @Override
    public List<String> getFileTypesByBranch(String branch) {
        for (BranchFileTypeImpl b : BranchFileTypeImpl.values()) {
            if (b.getBranchName().equals(branch)) {
                return b.getFileTypes();
            }
        }
        return new ArrayList<>();
    }
}