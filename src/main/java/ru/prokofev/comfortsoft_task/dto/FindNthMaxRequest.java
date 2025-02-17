package ru.prokofev.comfortsoft_task.dto;

public class FindNthMaxRequest {

    private String filePath;
    private Integer n;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
