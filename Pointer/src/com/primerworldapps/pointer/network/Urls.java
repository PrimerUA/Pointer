package com.primerworldapps.pointer.network;

/**
 * Created with IntelliJ IDEA.
 * User: Kvest
 * Date: 12.02.14
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class Urls {
    private static final String GET_OPPONENTS_LIST_URL = "http://tq.svit.co/pointer/list.php";
    private static final String SET_GCM_REGID_URL = "http://tq.svit.co/pointer/regid.php";
    private static final String ID_PARAM = "id=";
    private static final String COUNT_PARAM = "count=";

    public static String getOpponentsListUrl(long id, int count) {
        return GET_OPPONENTS_LIST_URL + "?" + ID_PARAM  + id + "&" + COUNT_PARAM + count;
    }

    public static String getSetGCMRegIdUlr() {
        return SET_GCM_REGID_URL;
    }
}
