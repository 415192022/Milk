package com.yundong.milk.model;

import java.util.List;

/**
 * Created by MingweiLi on 2017/3/17.
 */

public class PingPayBean {


    /**
     * id : ch_Hm5uTSifDOuTy9iLeLPSurrD
     * object : charge
     * created : 1410778843
     * livemode : true
     * paid : false
     * refunded : false
     * app : app_1Gqj58ynP0mHeX1q
     * channel : upacp
     * order_no : 123456789
     * client_ip : 127.0.0.1
     * amount : 100
     * amount_settle : 100
     * currency : cny
     * subject : Your Subject
     * body : Your Body
     * extra : {}
     * time_paid : null
     * time_expire : 1410782443
     * time_settle : null
     * transaction_no : null
     * refunds : {"object":"list","url":"/v1/charges/ch_Hm5uTSifDOuTy9iLeLPSurrD/refunds","has_more":false,"data":[]}
     * amount_refunded : 0
     * failure_code : null
     * failure_msg : null
     * credential : {"object":"credential","upacp":{"tn":"201409151900430000000","mode":"01"}}
     * description : null
     */

    private String id;
    private String object;
    private int created;
    private boolean livemode;
    private boolean paid;
    private boolean refunded;
    private String app;
    private String channel;
    private String order_no;
    private String client_ip;
    private int amount;
    private int amount_settle;
    private String currency;
    private String subject;
    private String body;
    private ExtraBean extra;
    private Object time_paid;
    private int time_expire;
    private Object time_settle;
    private Object transaction_no;
    private RefundsBean refunds;
    private int amount_refunded;
    private Object failure_code;
    private Object failure_msg;
    private CredentialBean credential;
    private MetadataBean metadataBean;
    private Object description;

    @Override
    public String toString() {
        return "PingPayBean{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", created=" + created +
                ", livemode=" + livemode +
                ", paid=" + paid +
                ", refunded=" + refunded +
                ", app='" + app + '\'' +
                ", channel='" + channel + '\'' +
                ", order_no='" + order_no + '\'' +
                ", client_ip='" + client_ip + '\'' +
                ", amount=" + amount +
                ", amount_settle=" + amount_settle +
                ", currency='" + currency + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", extra=" + extra +
                ", time_paid=" + time_paid +
                ", time_expire=" + time_expire +
                ", time_settle=" + time_settle +
                ", transaction_no=" + transaction_no +
                ", refunds=" + refunds +
                ", amount_refunded=" + amount_refunded +
                ", failure_code=" + failure_code +
                ", failure_msg=" + failure_msg +
                ", credential=" + credential +
                ", metadataBean=" + metadataBean +
                ", description=" + description +
                '}';
    }

    public void setMetadataBean(MetadataBean metadataBean) {
        this.metadataBean = metadataBean;
    }

    public MetadataBean getMetadataBean() {
        return metadataBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_settle() {
        return amount_settle;
    }

    public void setAmount_settle(int amount_settle) {
        this.amount_settle = amount_settle;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public Object getTime_paid() {
        return time_paid;
    }

    public void setTime_paid(Object time_paid) {
        this.time_paid = time_paid;
    }

    public int getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(int time_expire) {
        this.time_expire = time_expire;
    }

    public Object getTime_settle() {
        return time_settle;
    }

    public void setTime_settle(Object time_settle) {
        this.time_settle = time_settle;
    }

    public Object getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(Object transaction_no) {
        this.transaction_no = transaction_no;
    }

    public RefundsBean getRefunds() {
        return refunds;
    }

    public void setRefunds(RefundsBean refunds) {
        this.refunds = refunds;
    }

    public int getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(int amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public Object getFailure_code() {
        return failure_code;
    }

    public void setFailure_code(Object failure_code) {
        this.failure_code = failure_code;
    }

    public Object getFailure_msg() {
        return failure_msg;
    }

    public void setFailure_msg(Object failure_msg) {
        this.failure_msg = failure_msg;
    }

    public CredentialBean getCredential() {
        return credential;
    }

    public void setCredential(CredentialBean credential) {
        this.credential = credential;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public static class ExtraBean {
    }

    public static class MetadataBean {
    }

    public static class RefundsBean {
        /**
         * object : list
         * url : /v1/charges/ch_Hm5uTSifDOuTy9iLeLPSurrD/refunds
         * has_more : false
         * data : []
         */

        private String object;
        private String url;
        private boolean has_more;
        private List<?> data;

        @Override
        public String toString() {
            return "RefundsBean{" +
                    "object='" + object + '\'' +
                    ", url='" + url + '\'' +
                    ", has_more=" + has_more +
                    ", data=" + data +
                    '}';
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }
    }

    public static class CredentialBean {
        /**
         * object : credential
         * upacp : {"tn":"201409151900430000000","mode":"01"}
         */

        private String object;
        private AlipayBean alipay;
        private WxBean wx;

        @Override
        public String toString() {
            return "CredentialBean{" +
                    "object='" + object + '\'' +
                    ", alipay=" + alipay +
                    ", wx=" + wx +
                    '}';
        }

        public void setObject(String object) {
            this.object = object;
        }

        public void setAlipay(AlipayBean alipay) {
            this.alipay = alipay;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public String getObject() {
            return object;
        }

        public AlipayBean getAlipay() {
            return alipay;
        }

        public WxBean getWx() {
            return wx;
        }

        public static class AlipayBean {
            private String orderInfo;

            @Override
            public String toString() {
                return "AlipayBean{" +
                        "orderInfo='" + orderInfo + '\'' +
                        '}';
            }

            public void setOrderInfo(String orderInfo) {
                this.orderInfo = orderInfo;
            }

            public String getOrderInfo() {
                return orderInfo;
            }
        }

        public static class WxBean {
            private String appId;
            private String partnerId;
            private String prepayId;
            private String nonceStr;
            private String timeStamp;
            private String packageValue;
            private String sign;

            @Override
            public String toString() {
                return "WxBean{" +
                        "appId='" + appId + '\'' +
                        ", partnerId='" + partnerId + '\'' +
                        ", prepayId='" + prepayId + '\'' +
                        ", nonceStr='" + nonceStr + '\'' +
                        ", timeStamp='" + timeStamp + '\'' +
                        ", packageValue='" + packageValue + '\'' +
                        ", sign='" + sign + '\'' +
                        '}';
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public void setPartnerId(String partnerId) {
                this.partnerId = partnerId;
            }

            public void setPrepayId(String prepayId) {
                this.prepayId = prepayId;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public void setTimeStamp(String timeStamp) {
                this.timeStamp = timeStamp;
            }

            public void setPackageValue(String packageValue) {
                this.packageValue = packageValue;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getAppId() {
                return appId;
            }

            public String getPartnerId() {
                return partnerId;
            }

            public String getPrepayId() {
                return prepayId;
            }

            public String getNonceStr() {
                return nonceStr;
            }

            public String getTimeStamp() {
                return timeStamp;
            }

            public String getPackageValue() {
                return packageValue;
            }

            public String getSign() {
                return sign;
            }
        }
    }
}
