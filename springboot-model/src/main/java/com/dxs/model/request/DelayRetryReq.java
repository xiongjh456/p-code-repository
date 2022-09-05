package com.dxs.model.request;

record  DelayRetryReq(String name, String password) {
    /** 信息*/
    private static String message;
    /** 备注*/
    private static String remark;

    public static void main(String[] args) {
        DelayRetryReq delayRetryReq = new DelayRetryReq("123", "456");
        System.out.println(delayRetryReq);
        System.out.println(delayRetryReq.name);
    }

}
