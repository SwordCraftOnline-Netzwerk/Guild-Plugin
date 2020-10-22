package de.fynn.guild;

public class Result{

    private boolean success;
    private String msg;

    public Result(boolean value, String msg) {
        success = value;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

}
