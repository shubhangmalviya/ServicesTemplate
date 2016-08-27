package com.daffodilsw.servicestemplate.post.raw;

public class RawRequest {

    // Body
    private ReqRawData mReqBody;

    // path endpoint
    private String mPath;


    public RawRequest(ReqRawData reqBody, String path) {
        mReqBody = reqBody;
        mPath = path;
    }

    public ReqRawData getReqBody() {
        return mReqBody;
    }

    public String getPath() {
        return mPath;
    }
}
