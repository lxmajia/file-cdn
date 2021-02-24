package com.majiaxueyuan.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.majiaxueyuan.constans.BranchFileType;
import com.majiaxueyuan.vo.Req;
import com.majiaxueyuan.vo.Resp;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Liao
 */
public class GiteeCdnServer extends CdnServerAbstract {


    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build();

    private static final Map<String, String> FILE_TYPE_BRANCH_MAPPING = new HashMap<>();

    private final String accessToken;
    private final String giteeName;
    private final String repos;
    private final String defaultBranch;

    public GiteeCdnServer(String token, String userId, String repos, String defaultBranch, Class<? extends BranchFileType> mappingClass) {
        this.accessToken = token;
        this.giteeName = userId;
        this.repos = repos;
        this.defaultBranch = defaultBranch;

        Enum[] enumConstants = ((Class<? extends Enum>) mappingClass).getEnumConstants();
        Arrays.stream(enumConstants).forEach(enumConstant -> {
            BranchFileType enumConstant1 = (BranchFileType) enumConstant;
            String branchName = enumConstant1.getBranchName();
            List<String> fileTypesByBranch = enumConstant1.getFileTypesByBranch(branchName);
            for (String typesByBranch : fileTypesByBranch) {
                FILE_TYPE_BRANCH_MAPPING.put(typesByBranch.toLowerCase(), branchName);
            }
        });
    }

    @Override
    String getPrefixUrl() {
        return "https://gitee.com/api/v5/repos/" + giteeName + "/" + repos + "/contents/";
    }

    @Override
    Resp invokeUrl(String url, String content) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response execute = OK_HTTP_CLIENT.newCall(request).execute();
            String string = Objects.requireNonNull(execute.body()).string();
            // 状态码201才是成功
            if (execute.code() == 201) {
                Resp resp = JSONObject.parseObject(string, Resp.class);
                resp.setSuccess(true);
                resp.setMsg("SUCCESS");
                return resp;
            } else {
                String message = JSONObject.parseObject(string).getString("message");
                Resp resp = new Resp();
                resp.setSuccess(false);
                resp.setMsg(message);
                return resp;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp uploadFile(File file) {
        String base64;
        try {
            base64 = getFileBase64(file);
        } catch (IOException e) {
            Resp resp = new Resp();
            resp.setSuccess(false);
            resp.setMsg(e.getMessage());
            return resp;
        }
        String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        return uploadFileBase64(base64, fileType);
    }

    @Override
    public Resp uploadFileBase64(String base64, String fileType) {
        String reqUrl = getReqUrl(UUID.randomUUID().toString(), fileType);
        String branchByFileType = FILE_TYPE_BRANCH_MAPPING.get(fileType.toLowerCase());
        if (branchByFileType == null || "".equals(branchByFileType)) {
            branchByFileType = defaultBranch;
        }
        Req req = new Req();
        req.setAccess_token(accessToken);
        req.setBranch(branchByFileType);
        req.setContent(base64);
        req.setMessage(reqUrl);
        return invokeUrl(reqUrl, JSON.toJSONString(req));
    }
}
