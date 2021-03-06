package bank.gateway;

import bank.model.BankInterestReply;
import bank.model.BankInterestRequest;
import jmsmessaging.MessageReceiverGateway;
import jmsmessaging.MessageSenderGateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class LoanBrokerAppGateway {
    private BankSerializer bankSerializer;
    private BankRequestListener bankRequestListener;
    private MessageReceiverGateway messageReceiverGateway;
    private MessageSenderGateway messageSenderGateway;
    private int aggregationId;

    public LoanBrokerAppGateway(String receiverQueueName, String senderQueueName) {
        bankSerializer = new BankSerializer();
        messageReceiverGateway = new MessageReceiverGateway(receiverQueueName);
        messageSenderGateway = new MessageSenderGateway(senderQueueName);
        messageReceiverGateway.openConnection();
        messageSenderGateway.openConnection();
        aggregationId = 0;

        messageReceiverGateway.setListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    aggregationId = message.getIntProperty("aggregationId");
                    BankInterestRequest bankInterestRequest = bankSerializer.deserializeBankInterestRequest(((TextMessage)message).getText());
                    bankRequestListener.onRequestReceived(bankInterestRequest);
                } catch (JMSException exc) {
                    exc.printStackTrace();
                }
            }
        });
    }

    public void sendBankInterestReply(BankInterestReply bankInterestReply) throws JMSException {
        String serializedMessage = bankSerializer.serializeBankInterestReply(bankInterestReply);
        Message message = messageSenderGateway.createTextMessage(serializedMessage);
        message.setIntProperty("aggregationId", aggregationId);
        messageSenderGateway.send(message);
    }

    public void setRequestListener(BankRequestListener bankRequestListener) {
        this.bankRequestListener = bankRequestListener;
    }
}
