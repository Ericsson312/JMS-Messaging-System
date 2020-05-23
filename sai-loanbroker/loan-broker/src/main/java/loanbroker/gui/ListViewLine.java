package loanbroker.gui;

import loanbroker.model.Agency;
import loanclient.model.LoanReply;
import loanclient.model.LoanRequest;

public class ListViewLine {

    private LoanRequest loanRequest;
    private LoanReply loanReply;
    private Agency agency;

    public ListViewLine(LoanRequest loanRequest) {
        setLoanRequest(loanRequest);
        setLoanReply(null);
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    private void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }


    public void setLoanReply(LoanReply loanReply) {
        this.loanReply = loanReply;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }


    /**
     * This method defines how one line is shown in the ListViewLine.
     * @return
     *  a) if BankInterestReply is null, then this item will be shown as "loanRequest.toString ---> waiting for loan reply..."
     *  b) if BankInterestReply is not null, then this item will be shown as "loanRequest.toString ---> loanReply.toString"
     */
    @Override
    public String toString() {
        return loanRequest.toString() + " " + ((agency != null) ? agency.toString() : "") + "  --->  " + ((loanReply != null) ? loanReply.toString() : "waiting for reply...");
    }

}
