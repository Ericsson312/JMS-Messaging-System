package loanbroker.model;

import java.io.Serializable;

public class ClientCreditHistory implements Serializable {
    private int creditScore;
    private int history;

    public ClientCreditHistory() {
        super();
        creditScore = 0;
        history = 0;
    }

    public ClientCreditHistory(int creditScore, int history) {
        super();
        this.creditScore = creditScore;
        this.history = history;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "score=" + creditScore + " history=" + history;
    }
}
