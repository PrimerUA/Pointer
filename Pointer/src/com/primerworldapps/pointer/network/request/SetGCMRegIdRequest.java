package com.primerworldapps.pointer.network.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.primerworldapps.pointer.network.Urls;
import com.primerworldapps.pointer.network.response.SetGCMRegIdResponse;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: roman
 * Date: 2/13/14
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class SetGCMRegIdRequest extends JsonRequest<SetGCMRegIdResponse> {
    private static Gson gson = new Gson();

    public SetGCMRegIdRequest(long id, String regId, Response.Listener<SetGCMRegIdResponse> listener,
                                   Response.ErrorListener errorListener) {
        super(Method.POST, Urls.getSetGCMRegIdUlr(), createRequestBody(id, regId), listener, errorListener);
    }

    private static String createRequestBody(long id, String regId) {
        RequestBody requestBody = new RequestBody();
        requestBody.userID = id;
        requestBody.regID = regId;

        return gson.toJson(requestBody);
    }

    @Override
    protected Response<SetGCMRegIdResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            //get string response
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            SetGCMRegIdResponse setGCMRegIdResponse = gson.fromJson(json, SetGCMRegIdResponse.class);
            return Response.success(setGCMRegIdResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    private static class RequestBody {
        public long userID;
        public String regID;
    }
}
