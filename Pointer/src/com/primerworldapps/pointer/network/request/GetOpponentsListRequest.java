package com.primerworldapps.pointer.network.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.primerworldapps.pointer.network.Urls;
import com.primerworldapps.pointer.network.response.GetOpponentsListResponse;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 12.02.14
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
public class GetOpponentsListRequest extends JsonRequest<GetOpponentsListResponse> {
    private static Gson gson = new Gson();

    public GetOpponentsListRequest(long id, int count, Response.Listener<GetOpponentsListResponse> listener,
                                    Response.ErrorListener errorListener) {
        super(Method.GET, Urls.getOpponentsListUrl(id, count), null, listener, errorListener);
    }

    @Override
    protected Response<GetOpponentsListResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            //get string response
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            GetOpponentsListResponse getOpponentsListResponse = gson.fromJson(json, GetOpponentsListResponse.class);
            return Response.success(getOpponentsListResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}
