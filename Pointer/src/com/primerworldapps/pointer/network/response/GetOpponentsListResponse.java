package com.primerworldapps.pointer.network.response;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 12.02.14
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class GetOpponentsListResponse extends BaseResponse {
    public List<Proposal> proposals;

    public static class Proposal {
        public int userId;
        public String photo;
        public String name;
        public int gender;
        public String greeting;
        public int age;
    }
}
