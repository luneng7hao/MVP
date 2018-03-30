package com.mvp.model.entity;


import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Asion on 2017/12/24.
 */

public class BaseBean implements Serializable {
    private Integer result;				// 0：成功
    // 1：系统异常
    // 2：参数配置错误

    private String msg;					// 返回信息

    private List<Object> root;

    public List<Object> getRoot() {
        return root;
    }

    public void setRoot(List<Object> root) {
        this.root = root;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
