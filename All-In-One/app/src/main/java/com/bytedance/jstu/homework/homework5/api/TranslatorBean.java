package com.bytedance.jstu.homework.homework5.api;

import java.util.List;

public class TranslatorBean {
    private String input;
    private Web_trans web_trans;
    public class Web_trans {
        private List<Webtranslation> webtranslation;
        public class Webtranslation {
            private String key;
            private List<Trans> trans;
            public class Trans {
                private String value;

                public void setValue(String value) {
                    this.value = value;
                }
                public String getValue() {
                    return value;
                }
            }

            public void setKey(String key) {
                this.key = key;
            }
            public String getKey() {
                return key;
            }
            public void setTrans(List<Trans> trans) {
                this.trans = trans;
            }
            public List<Trans> getTrans() {
                return trans;
            }

        }

        public void setWebtranslation(List<Webtranslation> webtranslation) { this.webtranslation = webtranslation;}
        public List<Webtranslation> getWebtranslation() {
            return webtranslation;
        }
    }

    public void setInput(String input) { this.input = input; }
    public String getInput() { return input; }

    public void setWeb_trans(Web_trans web_trans) { this.web_trans = web_trans; }
    public Web_trans getWeb_trans() { return web_trans; }
}


