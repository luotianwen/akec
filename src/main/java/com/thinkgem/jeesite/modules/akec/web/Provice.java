package com.thinkgem.jeesite.modules.akec.web;

import java.io.Serializable;
import java.util.List;

public class Provice implements Serializable {

    /**
     * value : 110000
     * label : 北京市
     * children : [{"value":"110100","label":"北京城区"}]
     */

    private String value;
    private String label;
    private List<ChildrenBean> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean implements Serializable{
        /**
         * value : 110100
         * label : 北京城区
         */

        private String value;
        private String label;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
