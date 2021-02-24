package com.majiaxueyuan.core;

import com.majiaxueyuan.vo.Resp;

import java.io.File;

/**
 * @author Liao
 */
public interface CdnServer {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 结果
     */
    Resp uploadFile(File file);

    /**
     * 上传Base64
     *
     * @param base64   文件的Base64
     * @param fileType 文件类型（如jpg）
     * @return 结果
     */
    Resp uploadFileBase64(String base64, String fileType);
}
