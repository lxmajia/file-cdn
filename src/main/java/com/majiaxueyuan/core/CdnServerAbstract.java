package com.majiaxueyuan.core;

import com.alibaba.fastjson.JSONObject;
import com.majiaxueyuan.vo.Resp;
import okhttp3.*;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class CdnServerAbstract implements CdnServer {

    abstract String getPrefixUrl();

    String getReqUrl(String fileName, String fileType) {
        String fileFullName = fileName + "." + fileType;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH) + 1;//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        String suffix = year + "/" + month + "/" + day + "/" + fileFullName;
        return getPrefixUrl() + suffix;
    }

    String getFileBase64(File file) throws IOException {
        return DatatypeConverter.printBase64Binary(Files.readAllBytes(file.toPath()));
    }

    abstract Resp invokeUrl(String url, String content);

}
